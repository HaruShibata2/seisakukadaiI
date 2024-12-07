package jp.ac.teami.seisakukadaiI.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
        // 現在のユーザー情報を取得
        UserModel existingUser = repository.findById(editUser.getId()).orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));

        // パスワードが空でない場合にのみエンコードを実施
        if (editUser.getPassword() != null && !editUser.getPassword().isEmpty()) {
            // 新しいパスワードが空でない場合のみエンコード
            editUser.setPassword(encodePassword(editUser.getPassword()));
        } else {
            // パスワードが空の場合、既存のパスワードをそのまま使用
            editUser.setPassword(existingUser.getPassword());
        }

        // ユーザー情報を保存
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
    
    
    public class UserSpecification {

        public static Specification<UserModel> usernameContains(String username) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
        }

        public static Specification<UserModel> emailContains(String email) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
        }

        public static Specification<UserModel> departmentContains(String department) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("department"), "%" + department + "%");
        }
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
        // すべての条件がnullまたは空文字列でないかをチェック
        boolean isAnyConditionProvided = 
                (username != null && !username.isBlank()) || 
                (email != null && !email.isBlank()) || 
                (department != null && !department.isBlank());

        if (!isAnyConditionProvided) {
            // 条件が全て空の場合、全ユーザーを返す
            return repository.findAll(sort);
        }

        // 検索条件を動的に組み立てて検索を行う
        Specification<UserModel> specification = Specification.where(null);

        if (username != null && !username.isBlank()) {
            specification = specification.and(UserSpecification.usernameContains(username));
        }

        if (email != null && !email.isBlank()) {
            specification = specification.and(UserSpecification.emailContains(email));
        }

        if (department != null && !department.isBlank()) {
            specification = specification.and(UserSpecification.departmentContains(department));
        }

        // 条件を基にデータベースを検索し、結果をソートして返す
        return repository.findAll(specification, sort);
    }
    
    
    public List<UserModel> search(String username, String email, String department) {
        // 検索条件が空の場合はそのフィルタを無視
        if (username != null && !username.isEmpty() && 
            email != null && !email.isEmpty() && 
            department != null && !department.isEmpty()) {
            // 全ての条件が入力された場合
            return repository.findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndDepartmentContainingIgnoreCase(username, email, department);
        } else if (username != null && !username.isEmpty()) {
            return repository.findByUsernameContainingIgnoreCase(username);
        } else if (email != null && !email.isEmpty()) {
            return repository.findByEmailContainingIgnoreCase(email);
        } else if (department != null && !department.isEmpty()) {
            return repository.findByDepartmentContainingIgnoreCase(department);
        } else {
            // 全部空の場合、全ユーザーを返す
            return repository.findAll();
        }
    

    }
}

