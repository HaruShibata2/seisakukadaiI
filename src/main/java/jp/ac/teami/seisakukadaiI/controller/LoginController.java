
package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	
	//GETメソッド
	@GetMapping("/login/syokigamen")
	public String getLogin(Model model) {
		return "login/syokigamen";
	}
	
	@GetMapping("/login/kojintouroku")
	public String index(Model model) {
		return "login/kojintouroku";
	}
	
	@GetMapping("/login/login")
	public String index2(Model model) {
		return "login/login";
	}
	
	@GetMapping("/login/lng")
	public String lngs(Model model) {
		return "login/lng";
	}
	
	@GetMapping("/login/codenyuryoku")
	public String codeadd(Model model) {
		return "login/codenyuryoku";
	}
	
	@GetMapping("/login/emailadd")
	public String emailadd(Model model) {
		return "login/emailadd";
	}
	
}
