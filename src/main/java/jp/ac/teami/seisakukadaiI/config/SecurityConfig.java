package jp.ac.teami.seisakukadaiI.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jp.ac.teami.seisakukadaiI.service.UserDetailsServiceImpl;

@Configuration //設定用のクラスであることをSpringに伝える
@EnableWebSecurity //Spring Securityを使うための設定
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsServiceImpl userService;

	// TODO: あとで削除
//    @Autowired
//    private Teacherrepository userRepository; // ユーザモデルのRepository

    @Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager(this.dataSource);

		// TODO: あとで削除
//		this.userRepository.saveAndFlush(this.makeUser("0", "0000", "大原学園", "123"));

		return jdbcManager;
	}

////	// TODO: あとで削除
//	private Teacher makeUser(String teacher_id, String password, String name, String schoolCd) {
//		Teacher record = new Teacher();
//		record.setTeacherId(teacher_id);
//		record.setPassword(this.passwordEncoder().encode(password));
//		record.setName(name);
//		record.setSchoolCd(schoolCd);
//
//		return record;
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	            .httpBasic(basic -> basic.disable())  // Basic 認証を無効化
	            .authorizeHttpRequests(request -> request
	                    .requestMatchers("/login", "/login/syokigamen", "/register", "/webjars/**", "/js/**", "/css/**", "/images/**").permitAll()  // パブリックなURLは全て許可
	                    .requestMatchers("/admin/**").hasRole("ADMIN")  // 管理者専用ページ
	                    .requestMatchers("/leader/**").hasRole("LEADER")
	                    .requestMatchers("/general/**").hasRole("GENERAL")    // ユーザー専用ページ
	                    .anyRequest().authenticated()  // それ以外のページには認証が必要
	            )
	            .formLogin(form -> form
	                    .loginPage("/login/syokigamen")  // ログインページのURI
	                    .loginProcessingUrl("/login2/")  // ログイン処理のURL
	                    .defaultSuccessUrl("/")  // ログイン成功後のリダイレクト先
	                    .failureUrl("/login/?error=true")  // ログイン失敗時のURL
	                    .usernameParameter("user_id")  // ログインフォームのユーザー名
	                    .passwordParameter("password")  // ログインフォームのパスワード
	            )
	            .userDetailsService(this.userService)  // カスタムのUserDetailsService
	            .logout(logout -> logout
	                    .logoutUrl("/logout")
	                    .logoutSuccessUrl("/login")
	                    .deleteCookies("JSESSIONID")
	                    .invalidateHttpSession(true)
	            );
	    http.exceptionHandling(exception -> 
        exception.accessDeniedPage("/error")); // 直接エラーページを設定
	    return http.build();
	}
}