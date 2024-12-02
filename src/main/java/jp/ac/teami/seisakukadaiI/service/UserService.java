package jp.ac.teami.seisakukadaiI.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * ユーザー一覧を取得（任意のソート順）
     *
     * @param sort ソート条件
     * @return List<UserModel>
     */
    public List<UserModel> getAllUsersSorted(Sort sort) {
        return repository.findAll(sort);
    }

    /**
     * IDでユーザーを取得
     *
     * @param id ユーザーID
     * @return UserModel
     */
    public UserModel getUserById(@NonNull Long id) {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + id)
        );
    }

    /**
     * ユーザーを保存
     *
     * @param user ユーザーモデル
     */
    public void save(@NonNull UserModel user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(encodePassword(user.getPassword()));
        }
        repository.save(user);
    }

    /**
     * ユーザーを更新
     *
     * @param editUser 更新するユーザーモデル
     */
    public void update(@NonNull UserModel editUser) {
        if (editUser.getPassword() != null && !editUser.getPassword().isEmpty()) {
            editUser.setPassword(encodePassword(editUser.getPassword()));
        }
        repository.save(editUser);
    }

    /**
     * ユーザーを削除
     *
     * @param id ユーザーID
     */
    public void delete(@NonNull Long id) {
        repository.deleteById(id);
    }

    /**
     * Usernameでユーザーを取得
     *
     * @param username ユーザー名
     * @return UserModel
     */
    public UserModel getByUsername(String username) {
        return repository.findByUsername(username);
    }

    /**
     * UserIDでユーザーを取得
     *
     * @param userId ユーザーID
     * @return UserModel
     */
    public UserModel getUserByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    /**
     * 新規ユーザー登録
     *
     * @param name      ユーザー名
     * @param userId    ユーザーID
     * @param password  パスワード
     * @param department 部署
     * @return 登録されたユーザーモデル
     */
    public UserModel registerUser(String name, String userId, String password, String department) {
        if (repository.findByUserId(userId) != null) {
            throw new IllegalArgumentException("User ID already in use");
        }

        UserModel user = new UserModel();
        user.setUsername(name);
        user.setUserId(userId);
        user.setPassword(encodePassword(password));
        user.setEntryDate(new Date(System.currentTimeMillis()));
        user.setDepartment(department);
        user.setRole(UserModel.UserRole.GENERAL);

        return repository.save(user);
    }

    /**
     * パスワードをエンコード
     *
     * @param rawPassword 生パスワード
     * @return エンコードされたパスワード
     */
    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 任意の条件でユーザーを検索
     *
     * @param username   ユーザー名の一部
     * @param email      メールアドレスの一部
     * @param department 部署名の一部
     * @param sort       ソート条件
     * @return List<UserModel>
     */
    public List<UserModel> searchUsers(String username, String email, String department, Sort sort) {
        boolean isAllNullOrEmpty = Stream.of(username, email, department)
                .allMatch(value -> value == null || value.isBlank());

        if (isAllNullOrEmpty) {
            // 条件がすべて空の場合、全ユーザーを返す
            return repository.findAll(sort);
        }

        // 条件別に検索
        List<UserModel> resultByName = repository.findByUsernameContaining(username, sort);
        List<UserModel> resultByEmail = repository.findByEmailContaining(email, sort);
        List<UserModel> resultByDepartment = repository.findByDepartmentContaining(department, sort);

        // 結果を統合して重複を除去
        return Stream.concat(
                Stream.concat(resultByName.stream(), resultByEmail.stream()),
                resultByDepartment.stream()
        ).distinct().toList();
    }
}
