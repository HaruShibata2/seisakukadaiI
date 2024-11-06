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
				.httpBasic(
						(basic) -> basic.disable())
				.authorizeHttpRequests(request -> {
					request

							.requestMatchers("/login").permitAll() // ログインページは全許可
							.requestMatchers("/home1").permitAll() // ログインページは全許可
							// .requestMatchers("/error").permitAll() // ログインページは全許可
							.requestMatchers("/login/syokigamen").permitAll() // ログインページは全許可
							.requestMatchers("/register").permitAll() // 新規登録ページは全許可
							.requestMatchers("/webjars/**").permitAll() // webjarsのパスは全許可
							.requestMatchers("/js/**").permitAll() // JSのstaticファイル
							.requestMatchers("/css/**").permitAll() // CSSのstaticファイル
							.requestMatchers("/images/**").permitAll() // 画像のstaticファイル
							.anyRequest().authenticated(); // それ以外は認証必須
							// .anyRequest().permitAll()
				})
				.formLogin(form -> {
					form
							.loginPage("/login") // ログインページのURI
							.loginProcessingUrl("/login") // ログインを実施するページのURI
							.defaultSuccessUrl("/home") // ログイン完了後の遷移先
							.failureUrl("/login?error=true") // ログインエラーページのURI
							.usernameParameter("email") // ログインユーザのname属性
							.passwordParameter("password"); // ログインパスワードのname属性
				})
				.userDetailsService(this.userService)
				.logout(logout -> {
					logout
							.logoutUrl("/logout")
							.logoutSuccessUrl("/login")
							.deleteCookies("JSESSIONID")
							.invalidateHttpSession(true);
				});
		return http.build();
	}
}