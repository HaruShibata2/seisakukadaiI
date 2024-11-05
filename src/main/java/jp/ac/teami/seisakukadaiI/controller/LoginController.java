
package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userservice;
	
	//GETメソッド
	@GetMapping("/login/syokigamen")
	public String getLogin(Model model) {
		return "login/syokigamen";
	}
	
	@GetMapping("/login/kojintouroku")
	public String index(Model model) {
		return "login/kojintouroku";
	}
	
	@GetMapping("/login/kaishatouroku")
	public String index4(Model model) {
		return "login/kaishatouroku";
	}
	
	@GetMapping("/login/login")
	public String index2(Model model) {
		return "login/login";
	}
	
//	@GetMapping("/")
//	public String lngs(Model model) {
//		return "login/lng";
//	}
	
	@GetMapping("/login/codenyuryoku")
	public String codeadd(Model model) {
		return "login/codenyuryoku";
	}
	
	@GetMapping("/login/emailadd")
	public String index3(Model model) {
		return "login/emailadd";
	}
	
	@GetMapping("/login/sinki")
	public String emailadd(Model model) {
		return "login/sinki";
	}
	@GetMapping("/register")
	public ModelAndView register(UserModel usermodel, ModelAndView model) {
	      model.addObject("register", usermodel); 
	      model.setViewName("login/register");
	      return model;
	}
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute @NonNull UserModel usermodel, RedirectAttributes result,
		RedirectAttributes redirectAttributes) {
		try {
			this.userservice.save(usermodel);
			System.out.print(12);		
			redirectAttributes.addFlashAttribute("exception", "");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("exception", e.getMessage());
				}
		return "redirect:/";
		}
	
}
