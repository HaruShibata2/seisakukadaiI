package jp.ac.teami.seisakukadaiI.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    // ログインユーザーのプロフィールを表示
    @GetMapping
    public String viewProfile(HttpServletRequest request, Model model) {
        // SecurityContextから現在ログインしているユーザーのusernameを取得
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // ユーザー情報を取得
        Optional<UserModel> user = userService.getByUsername(username);
        
        if (user != null) {
            model.addAttribute("user", user);  // ユーザー情報をModelに追加
            model.addAttribute("requestURI", request.getRequestURI());  // リクエストURIをModelに追加
            return "main/profile";  // mainディレクトリ内のprofile.htmlを表示
        }
        
        return "error";  // ユーザーが見つからない場合のエラーハンドリング
    }
}
