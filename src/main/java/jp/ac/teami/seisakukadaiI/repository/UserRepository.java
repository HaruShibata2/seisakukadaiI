package jp.ac.teami.seisakukadaiI.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    // ユーザー名で検索
    UserModel findByUsername(String username);

    // ユーザーIDで検索
    UserModel findByUserId(String userId);

    // Usernameによる部分一致検索（Sort付き）
    List<UserModel> findByUsernameContaining(String username, Sort sort);

    // Emailによる部分一致検索（Sort付き）
    List<UserModel> findByEmailContaining(String email, Sort sort);

    // Departmentによる部分一致検索（Sort付き）
    List<UserModel> findByDepartmentContaining(String department, Sort sort);

    // ID降順で全ユーザーを取得
    List<UserModel> findAllByOrderByIdDesc();
}
