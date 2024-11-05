//package jp.ac.teami.seisakukadaiI.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import jp.ac.teami.seisakukadaiI.model.User;
//import jp.ac.teami.seisakukadaiI.repository.UserRepository;
//
//public class UserDetailsManager {
//
//	@Service
//	public class StudentDetailsServiceImplt implements UserDetailsService {
//	    @Autowired
//	    private UserRepository userRepository; // ユーザモデルのRepository
//	    /**
//	     * ユーザの検索を行う
//	     */
//	    @Override
//	    
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        System.out.println("serach username : " + username);
//	        User teacher = this.userRepository.findByUsername(username); // emailで検索するので「EmailEquals」としている
//	        //System.out.println(teacher.toString());
//	        return teacher;
//	    }
//	}
//}
