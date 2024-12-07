package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // 送信元、送信先、件名、本文の設定
            helper.setFrom("haru2005shiba@icloud.com");  // 送信元のメールアドレス
            helper.setTo(toEmail);  // 送信先のメールアドレス
            helper.setSubject(subject);  // 件名
            helper.setText(body);  // 本文

            // メール送信
            mailSender.send(message);

            System.out.println("メールが正常に送信されました！");
        } catch (MailException e) {
            // エラーログを出力
            System.err.println("メール送信に失敗しました: " + e.getMessage());
            throw e;
        }
    }
}
