package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.service.UserService;



@Controller
public class MemberController {

    @Autowired
    private UserService userService;


	
	@GetMapping("/membermoto")
	public String  membermotoIndex(Model model) {
	return "main/member/membermoto";
	}
	@GetMapping("/kega")
	public String  kegaIndex(Model model) {
	return "main/member/kega";
	}
	

	@GetMapping("/users/same-department")
	public String getUsersFromSameDepartment(Model model) {
	    List<UserModel> users = userService.getUsersFromSameDepartment();
	    model.addAttribute("users", users);  // 取得したユーザーリストをModelに追加
	    return "main/member/memberhyouji";  // users/same-department.htmlというHTMLファイルを返す
	}


}
