package jp.ac.teami.seisakukadaiI.controller;

import java.time.LocalDateTime;

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
import jp.ac.teami.seisakukadaiI.repository.InquiryRepository;
import jp.ac.teami.seisakukadaiI.service.InquiryService;
import jp.ac.teami.seisakukadaiI.service.MailService;
@Controller
public class SettingController {
	
	 @Autowired
	    private InquiryService inquiryService;
	 
	 @Autowired
	    private MailService mailService;
	 
	 @Autowired
	 private InquiryRepository inquiryRepository;
	 
    @GetMapping("/setting")
    public String settings() {
        return "main/setting/setting";
    }

    @GetMapping("/toiawase")
    public String showInquiryForm(Model model) {
        model.addAttribute("inquiry", new InquiryModel());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String userId = userModel.getUserId();
        model.addAttribute("userId", userId);
       
        return "toi";
    }

    @PostMapping("/submitinquiry")
    public String submitInquiry(@ModelAttribute InquiryModel inquiry, Model model) {
        try {
            // 現在のログインユーザーの情報を取得
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserModel userModel = (UserModel) authentication.getPrincipal();
           
            // InquiryModelにユーザー情報と作成日時を設定
            inquiry.setUser(userModel);
            inquiry.setCreatedAt(LocalDateTime.now());
 
            // 問い合わせ内容をデータベースに保存
            inquiryService.saveInquiry(inquiry);
 
            // メール送信内容
            String subject = "新しいお問い合わせが届きました";
            String body = "名前: " + inquiry.getUser() + "\n" +
                          "内容: " + inquiry.getInquiryContent() + "\n" +
                          "説明: " + inquiry.getInquiryDescription();
            String toEmail = "recipient_outlook_email@example.com";  // 宛先メールアドレス
 
            // メール送信
            mailService.sendInquiryEmail(toEmail, subject, body);
 
            // 成功した場合はthank_youページへリダイレクト
            model.addAttribute("message", "お問い合わせが送信されました。ありがとうございます！");
            return "redirect:/thank_you";
        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            model.addAttribute("message", "お問い合わせの送信に失敗しました。再度お試しください。");
            return "error"; // エラーページに遷移
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
