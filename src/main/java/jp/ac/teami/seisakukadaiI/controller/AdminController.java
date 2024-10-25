package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
	@GetMapping("/admin")
		public String  adminIndex(Model model) {
		return "main/admin/admin";
	}
    @GetMapping("/bousai")
        public String  bousaiIndex(Model model) {
        return "main/admin/bousai";
    }
    @GetMapping("/biciku")
        public String  bicikuIndex(Model model) {
        return "main/admin/biciku";
    }
    @GetMapping("/member")
        public String  memberIndex(Model model) {
        return "main/admin/member";
    }
    @GetMapping("/toukou")
        public String  toukouIndex(Model model) {
        return "main/admin/toukou";
    }
}