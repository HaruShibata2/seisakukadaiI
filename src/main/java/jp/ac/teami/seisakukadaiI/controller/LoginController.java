
package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
<<<<<<< HEAD
	
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
	
	
	
=======

    //GETメソッド
    @GetMapping("/login/syokigamen")
    public String getLogin(Model model) {
        
        return "login/syokigamen";
    }

    @GetMapping("/login/kojintouroku")
    public String index(Model model2) {
        return "login/kojintouroku";
    }
    

    
>>>>>>> bc3791c799275321433ce9f84beff05c23744007
}