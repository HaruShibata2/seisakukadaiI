package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	@GetMapping("/")
	  public String index(Model model) {
	    model.addAttribute("message", "こんにちは");
	    return "top";
	    }
	@GetMapping("/admin/")
	public String  index2(Model model) {
		
	}
}

