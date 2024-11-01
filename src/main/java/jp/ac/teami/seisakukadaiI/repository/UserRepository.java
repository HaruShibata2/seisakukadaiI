package jp.ac.teami.seisakukadaiI.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.teami.seisakukadaiI.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
