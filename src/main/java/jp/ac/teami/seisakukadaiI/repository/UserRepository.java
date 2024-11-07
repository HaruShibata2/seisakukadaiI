package jp.ac.teami.seisakukadaiI.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    // user_id ではなく userId を使う
    UserModel findByUserId(String userId);
}
