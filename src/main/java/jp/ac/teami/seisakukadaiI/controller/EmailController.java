package jp.ac.teami.seisakukadaiI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jp.ac.teami.seisakukadaiI.service.EmailService;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    // メール送信フォームの表示
    @GetMapping("/form")
    public String showEmailForm() {
        return "emailForm";  // emailForm.htmlを返す
    }

    // メール送信エンドポイント
    @PostMapping("/send")
    public String sendEmail(@RequestParam String toEmail, 
                            @RequestParam String subject, 
                            @RequestParam String body) throws MessagingException {
        
        // フォームから送信された内容を確認
        System.out.println("送信先: " + toEmail);
        System.out.println("件名: " + subject);
        System.out.println("本文: " + body);

        // メール送信
        emailService.sendEmail(toEmail, subject, body);
        
        return "メール送信が完了しました！";
    }
}
