package com.lendbook.wechat_program.Tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMail {
    private String from = "gdccwxx@163.com";

    public void sendHtmlMail(String to, String subject, String content, JavaMailSender sender){

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = setInfoByHelper(to, subject, content, message);

            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private MimeMessageHelper setInfoByHelper(String to, String subject, String content, MimeMessage message)
            throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        return helper;
    }
}
