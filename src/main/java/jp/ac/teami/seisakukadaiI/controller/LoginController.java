package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //GETメソッド
    @GetMapping("/syokigamen")
    public String getLogin(Model model) {
        
        return "login/syokigamen";
    }

    @GetMapping("/kojintouroku")
    public String index(Model model2) {
    	return "login/kojintouroku";
    }
    

    
}

