package jp.ac.teami.seisakukadaiI.controller;

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
    private SafetyCheckRepository safetyCheckRepository;

    @GetMapping
    public String showSafetyCheckForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserModel) {
            UserModel user = (UserModel) principal;
            List<SafetyCheck> checks = safetyCheckRepository.findAll();

            model.addAttribute("checks", checks);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("message", "User not found");
            return "error";
        }
        return "safety/check"; // check.html に渡す
    }

    @PostMapping
    public String submitSafetyCheck(@ModelAttribute SafetyCheck status, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserModel user = (UserModel) principal;

        // デフォルト値として「安全」に設定（選択されていない場合）
        if (status.getStatus() == null) {
            status.setStatus(SafetyCheck.Status.SAFE);
        }

        status.setUser(user);  // ユーザー情報を設定
        status.setCheckedAt(LocalDateTime.now());  // 現在の日時を設定
        safetyCheckRepository.save(status);  // 保存
        List<SafetyCheck> checks = safetyCheckRepository.findAll();
        model.addAttribute("checks", checks);
        return "safety/status"; // 結果表示ページ
    }
}
	