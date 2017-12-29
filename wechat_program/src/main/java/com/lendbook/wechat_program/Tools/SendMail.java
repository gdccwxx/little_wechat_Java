package com.lendbook.wechat_program.Tools;

import com.lendbook.wechat_program.component.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMail {
    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private  JavaMailSender sender;
    public void sendHtmlMail(String to, String subject, String content){

        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = setInfoByHelper(to, subject, content, message);
            //message.addRecipients(Message.RecipientType.CC, emailProperties.getUsername());
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private MimeMessageHelper setInfoByHelper(String to, String subject, String content, MimeMessage message)
            throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(emailProperties.getUsername());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        return helper;
    }
}
