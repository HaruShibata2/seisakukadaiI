package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository; // ユーザモデルのRepository

    /**
     
ユーザの検索を行う*/
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Searching for username: " + username);
//        UserModel user = this.userRepository.findByEmail(username); // ユーザをメールアドレスで検索
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                user.getRole().getAuthorities() // ユーザのロールから権限を取得
//        );
//    }
    @Override

        public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
            System.out.println("serach user_id : " + user_id);
            UserModel user = this.userRepository.findByUserId(user_id); // emailで検索するので「EmailEquals」としている
            //System.out.println(teacher.toString());
            return user;
        }
}
