package jp.ac.teami.seisakukadaiI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {

    // Optional を返す
	Optional<UserModel> findByUsername(String username);

    // ユーザーIDで検索 (Optional 型を返す)
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

    // ロールで検索 (例: ADMIN, LEADER, GENERAL)
    List<UserModel> findByRole(UserModel.UserRole role);

    // 部署とロールで検索
    List<UserModel> findByDepartmentContainingIgnoreCaseAndRole(String department, UserModel.UserRole role);

    // 登録日順 (昇順)
    List<UserModel> findAllByOrderByEntryDateAsc();

    // 登録日順 (降順)
    List<UserModel> findAllByOrderByEntryDateDesc();
    List<UserModel> findByDepartment(String department);

}





