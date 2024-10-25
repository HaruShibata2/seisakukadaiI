package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @GetMapping("/setting")
    public String settings() {
        return "main/setting/setting";
    }
    @GetMapping("/toiawase")
<<<<<<< HEAD
    public String inquiry() {
        return "main/setting/toiawase";
    }
    @GetMapping("/oshirase")
    public String notice() {
        return "main/setting/oshirase";
=======
    public String inquiry(Model model) {
        return "toiawase";
    }
    @GetMapping("/oshirase")
    public String notice(Model model) {
        return "oshirase";
>>>>>>> 1d56994a295b007d8ed6318c5daf70b6629d435a
    }
}
