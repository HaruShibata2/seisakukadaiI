package jp.ac.teami.seisakukadaiI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.ac.teami.seisakukadaiI.model.User;
import jp.ac.teami.seisakukadaiI.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login/login"; // login.html
    }

    @GetMapping("/sinki")
    public String sinki() {
        return "login/sinki"; // sinki.html
    }
    
    @GetMapping("/kojintouroku")
    public String kojintouroku() {
        return "login/kojintouroku"; // sinki.html
    }
   
    @GetMapping("/kaishatouroku")
    public String kaishatouroku() {
        return "login/kaishatouroku"; // sinki.html
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login/register"; // register.htmlを返す
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.saveUser(user);
        return "redirect:/login"; // 登録後にログインページにリダイレクト
    }
}
