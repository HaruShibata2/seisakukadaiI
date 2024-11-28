package jp.ac.teami.seisakukadaiI.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // BCryptPasswordEncoderを追加

    /**
     * アドレス帳一覧の取得
     * @return List<UserModel>
     */
    public List<UserModel> getStudentList() {
        return this.repository.findAll();
    }

    /**
     * 詳細データの取得
     * @param @NonNull Long index
     * @return UserModel
     */
    public UserModel get(@NonNull Long index) {
        return this.repository.findById(index).orElse(new UserModel());
    }

    /**
     * ユーザーの保存
     * @param UserModel user
     */
    public void save(@Nonnull UserModel user) {
        // パスワードをBCryptでエンコード
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.repository.save(user);
        System.out.println("Saved user: " + user);
    }

    /**
     * アドレス帳データの削除
     * @param @NonNull Long id
     */
    public void delete(@NonNull Long id) {
        this.repository.deleteById(id);
    }

    public UserModel getOneBook(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    // ユーザーを更新する
    public void update(UserModel editUser) {
        System.out.println("Updating user: " + editUser);
        if (editUser.getPassword() != null && !editUser.getPassword().isEmpty()) {
            editUser.setPassword(passwordEncoder.encode(editUser.getPassword()));
        }
        this.repository.save(editUser);
        System.out.println("Updated user: " + editUser);
    }

    public UserModel getStudent(Long id) {
        return this.repository.findById(id).orElse(null);
    }
    
    public UserModel getUserId(String userId) {
    	return this.repository.findByUserId(userId);
    }

    // usernameでユーザー情報を取得
    public UserModel getByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    // ユーザー登録
    public UserModel registerUser(String name, String user_id, String password, String department) {
        // ユーザーIDの重複チェック
        if (repository.findByUserId(user_id) != null) {
            throw new IllegalArgumentException("User ID already in use");
        }

        UserModel user = new UserModel();
        user.setUsername(name);
        user.setUserId(user_id);
        user.setPassword(passwordEncoder.encode(password));
        user.setEntryDate(new Date(System.currentTimeMillis()));
        user.setDepartment(department);
        // デフォルトロールを設定
        user.setRole(UserModel.UserRole.GENERAL);

        return repository.save(user);
    }

    @Autowired
    private UserRepository userRepository;

    // usernameでユーザーを取得
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
