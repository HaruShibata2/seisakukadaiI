package jp.ac.teami.seisakukadaiI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {

    // ユーザー名で検索
    UserModel findByUsername(String username);

    // ユーザーIDで検索
    UserModel findByUserId(String userId);

    // ID降順で全ユーザーを取得
    List<UserModel> findAllByOrderByIdDesc();
    
    
 // username, email, department に部分一致（大文字・小文字区別なし）するユーザーを検索
    List<UserModel> findByUsernameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
            String username, String email, String department);
    
    // username に部分一致（大文字・小文字区別なし）するユーザーを検索
    List<UserModel> findByUsernameContainingIgnoreCase(String username);

    // email に部分一致（大文字・小文字区別なし）するユーザーを検索
    List<UserModel> findByEmailContainingIgnoreCase(String email);

    // department に部分一致（大文字・小文字区別なし）するユーザーを検索
    List<UserModel> findByDepartmentContainingIgnoreCase(String department);

}

