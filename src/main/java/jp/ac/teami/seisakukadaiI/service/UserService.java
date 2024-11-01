package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.User;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        // パスワードをエンコード
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); // ユーザーをリポジトリに保存
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // その他のメソッド（必要に応じて追加）
}
