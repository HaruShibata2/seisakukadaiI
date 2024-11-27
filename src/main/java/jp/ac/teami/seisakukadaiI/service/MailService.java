package jp.ac.teami.seisakukadaiI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInquiryEmail(String toEmail, String subject, String body) throws MailException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, false); // 第2引数を false に設定し、HTMLではなくテキストとして送信

        // 送信者のメールアドレスを設定
        helper.setFrom("fko2347008@stu.o-hara.ac.jp");

        mailSender.send(message);
    }
}
