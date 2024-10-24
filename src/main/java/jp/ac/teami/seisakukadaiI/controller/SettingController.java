package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @GetMapping("/setting")
    public String settings() {
        return "setting";
    }
    @GetMapping("/toiawase")
    public String inquiry() {
        return "toiawase";
    }
    @GetMapping("/oshirase")
    public String notice() {
        return "oshirase";
    }
}
