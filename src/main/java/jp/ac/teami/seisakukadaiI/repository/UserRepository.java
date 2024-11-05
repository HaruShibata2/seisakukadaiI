package jp.ac.teami.seisakukadaiI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.teami.seisakukadaiI.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    // メールアドレスでユーザーを検索するメソッド
    UserModel findByEmail(String email);
}
