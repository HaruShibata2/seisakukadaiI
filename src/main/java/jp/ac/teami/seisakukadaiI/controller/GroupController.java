
package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {
	//GETメソッド
	@GetMapping("/group")
	public String index(Model model) {
		
		return "main/group/group";
	}
	@GetMapping("/busyocode")
	public String busyoindex(Model model) {
		
		return "main/group/busyocode";
	}
}a