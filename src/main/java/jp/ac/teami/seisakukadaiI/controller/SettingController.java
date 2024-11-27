package jp.ac.teami.seisakukadaiI.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.ac.teami.seisakukadaiI.model.InquiryModel;
import jp.ac.teami.seisakukadaiI.model.UserModel;
import jp.ac.teami.seisakukadaiI.service.InquiryService;
import jp.ac.teami.seisakukadaiI.service.MailService;

@Controller
public class SettingController {

    private static final Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private MailService mailService;

    @GetMapping("/setting")
    public String settings() {
        return "main/setting/setting";
    }

    @GetMapping("/toiawase")
    public String showInquiryForm(Model model) {
        model.addAttribute("inquiry", new InquiryModel());

        // ログイン中のユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserModel) {
            UserModel userModel = (UserModel) authentication.getPrincipal();
            model.addAttribute("userId", userModel.getUserId());
        } else {
            logger.warn("ログインユーザー情報が取得できませんでした");
            model.addAttribute("userId", "ゲスト");
        }

        return "toi";
    }

    @PostMapping("/submitinquiry")
    public String submitInquiry(@ModelAttribute InquiryModel inquiry, Model model) {
        try {
            // ログインユーザー情報の取得
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof UserModel)) {
                throw new IllegalStateException("ログイン情報が無効です。");
            }

            UserModel userModel = (UserModel) authentication.getPrincipal();

            // 問い合わせ情報を設定
            inquiry.setUser(userModel);
            inquiry.setCreatedAt(LocalDateTime.now());

            // 問い合わせ内容を保存
            inquiryService.saveInquiry(inquiry);

            // メール送信
            if (mailService != null) {
            	String subject = "新しいお問い合わせが届きました";
            	String body = "名前: " + userModel.getUsername() + "\n" +
            	              "内容: " + inquiry.getInquiryContent() + "\n" +
            	              "説明: " + inquiry.getInquiryDescription();

                String toEmail = "recipient_outlook_email@example.com";
                mailService.sendInquiryEmail(toEmail, subject, body);
            } else {
                logger.error("MailServiceが初期化されていません。");
            }

            // 成功メッセージを設定してリダイレクト
            model.addAttribute("message", "お問い合わせが送信されました。ありがとうございます！");
            return "redirect:/thank_you";

        } catch (Exception e) {
            // エラーハンドリング
            logger.error("お問い合わせ処理中にエラーが発生しました", e);
            model.addAttribute("message", "お問い合わせの送信に失敗しました。再度お試しください。");
            return "error"; // エラーページを表示
        }
    }

    @GetMapping("/thank_you")
    public String showThankYouPage() {
        return "main/setting/thank_you";
    }

    @GetMapping("/oshirase")
    public String notice() {
        return "main/setting/oshirase";
    }
}
