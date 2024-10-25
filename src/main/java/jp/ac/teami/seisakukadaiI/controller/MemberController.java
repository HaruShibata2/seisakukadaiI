package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/membermoto")
	public String  membermotoIndex(Model model) {
	return "main/member/membermoto";
	}

	@GetMapping("/memberhyouji")
	public String  memberhyoujiIndex(Model model) {
	return "main/member/memberhyouji";
	}

}
