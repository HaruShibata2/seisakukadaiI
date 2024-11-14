package jp.ac.teami.seisakukadaiI.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserId(String user_id); 
    UserModel findByUsername(String username);
  }