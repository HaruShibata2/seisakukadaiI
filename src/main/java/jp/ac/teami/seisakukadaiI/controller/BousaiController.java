package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller

public class BousaiController {
    
    @GetMapping("/bousaigamen")
    public String bousaigamen() {
        return "main/bousai/bousaigamen";
    }
    @GetMapping("/bousaijouhou")
    public String bousaijouhou() {
        return "main/bousai/bousaijouhou";
    }
    @GetMapping("/bousaiMap")
    public String bousaiMap() {
        return "main/bousai/bousaiMap";
    }
    @GetMapping("/kishoujouhou")
    public String kishoujouhou() {
        return "main/bousai/kishoujouhou";
    }
}
