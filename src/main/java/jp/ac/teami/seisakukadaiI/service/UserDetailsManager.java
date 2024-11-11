//package jp.ac.teami.seisakukadaiI.service;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsManager implements UserDetailsService {
//
//    // ここでは、ユーザー情報をデータベースやリポジトリから取得することが多い
//    // 例として、固定のユーザーを返す実装をしていますが、実際のアプリケーションではデータベースから取得します
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // サンプル: "user" と "admin" のユーザーを作成
//        if ("admin".equals(username)) {
//            return User.withUsername(username)
//                    .password("{noop}adminpassword") // パスワードはエンコードして保存することを推奨
//                    .roles("ADMIN") // 管理者ロール
//                    .build();
//        } else if ("user".equals(username)) {
//            return User.withUsername(username)
//                    .password("{noop}userpassword")
//                    .roles("USER") // ユーザー用ロール
//                    .build();
//        }
//        
//        throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
//    }
//}
