package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //GETメソッド
    @GetMapping("/syokigamen")
    public String getLogin(Model model) {
        //login.htmlに画面遷移
        return "syokigamen";
    }

    @GetMapping("/kojin")
    public String index(Model model2) {
    	return "kojin";
    }
}

