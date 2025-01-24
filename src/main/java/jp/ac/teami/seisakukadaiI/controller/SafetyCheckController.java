package jp.ac.teami.seisakukadaiI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.teami.seisakukadaiI.model.SafetyCheck;
import jp.ac.teami.seisakukadaiI.service.SafetyCheckService;

@Controller
@RequestMapping("/safety")
public class SafetyCheckController {

    private static final Logger logger = LoggerFactory.getLogger(SafetyCheckController.class);

    @Autowired
    private SafetyCheckService safetyCheckService;

    // 安否確認送信前の確認画面
    @GetMapping("/confirm")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public String confirmSend(Model model) {
        model.addAttribute("message", "地震が発生しました。安否確認を送信しますか？");
        return "safety/confirm";
    }

    // 安否確認を送信
    @PostMapping("/check")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public String sendSafetyCheck(Model model) {
        List<SafetyCheck> checks = safetyCheckService.getAllSafetyChecks();
        model.addAttribute("checks", checks);

        logger.info("安否確認を送信しました。チェック件数: {}", checks.size());
        return "safety/check";
    }

    // 他の人の安否確認状況を表示
    @GetMapping("/status")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LEADER')")
    public String showStatus(Model model) {
        model.addAttribute("checks", safetyCheckService.getAllSafetyChecks());
        return "safety/status";
    }

    @PostMapping("/status")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'LEADER')")
    public String updateStatus(@RequestParam("status") String status) {
        try {
            // 現在のユーザーを取得
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

            // 入力値を SafetyCheck.Status に変換
            SafetyCheck.Status statusEnum = SafetyCheck.Status.valueOf(status.toUpperCase());

            // 現在のユーザーの安否状態を更新
            safetyCheckService.updateSafetyStatusByUsername(currentUsername, statusEnum);

            logger.info("安否状態を更新しました。Username: {}, Status: {}", currentUsername, statusEnum);
        } catch (IllegalArgumentException e) {
            logger.error("無効な安否状態が送信されました: {}", status, e);
            return "redirect:/safety/status?error=invalid_status";
        } catch (RuntimeException e) {
            logger.error("安否確認の更新に失敗しました", e);
            return "redirect:/safety/status?error=update_failed";
        }
        return "redirect:/safety/status";
    }


}

	