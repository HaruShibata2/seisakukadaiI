package jp.ac.teami.seisakukadaiI.controller;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.repository.SafetyCheckRepository;
import jp.ac.teami.seisakukadaiI.service.SafetyCheckService;
import jp.ac.teami.seisakukadaiI.service.UserService;

@Controller
@RequestMapping("/safety")
public class SafetyCheckController {

    @Autowired
    private SafetyCheckService safetyCheckService;

    @Autowired
    private UserService userService;

    @Autowired
    private SafetyCheckRepository postRepository;

    @GetMapping
    public String showSafetyCheckForm(Model model) {
        // 認証情報から現在のユーザーのusernameを取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserModel) {
            UserModel user = (UserModel) principal;
            String username = user.getUserId();  // ユーザーIDを取得
            List<SafetyCheck> check = postRepository.findAll();

            // モデルにユーザー情報を追加して、ビューに渡す
            model.addAttribute("check", check);
            model.addAttribute("user", user);
        } else {
            // エラーハンドリング（必要に応じて）
            model.addAttribute("message", "User not found");
            return "error";
        }

        return "check"; // check.html に渡す
    }

    @PostMapping
    public String submitSafetyCheck(@ModelAttribute SafetyCheck status, Model model) {
        // 認証情報から現在のユーザーのusernameを取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserModel user = (UserModel) principal;
        status.setUser(user);  // ユーザー情報を設定
        status.setCheckedAt(LocalDateTime.now());  // 現在の日時を設定
        postRepository.save(status);  // 保存
        List<SafetyCheck> check = postRepository.findAll();
        model.addAttribute("check", check);
        return "status"; // 更新された情報を表示
        }
        }

    

