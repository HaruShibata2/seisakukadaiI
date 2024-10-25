package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class BousaiController {
    
    @GetMapping("/bousaigamen")
    public String bousaigamen() {
        return "bousaigamen";
    }

    @GetMapping("/bousaijouhou")
    public String bousaijouhou() {
        return "bousaijouhou";
    }

    @GetMapping("/bousaiMap")
    public String bousaiMap() {
        return "bousaiMap";
    }
}
