package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @GetMapping("/setting")
    public String settings() {
        return "main/setting/setting";
    }

    @GetMapping("/inquiry_form")
    public String inquiry() {
        return "main/setting/inquiry_form";
    }
    @GetMapping("/oshirase")
    public String notice() {
        return "main/setting/oshirase";
    }


}
