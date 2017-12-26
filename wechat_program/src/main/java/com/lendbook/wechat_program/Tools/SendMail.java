package com.lendbook.wechat_program.Tools;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

public class SendMail {

    public Boolean sendHtmlMail(String title, ) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.126.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        final String username = "lcdxlh@126.com";
        final String password = "lc1997712";
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }});
        session.setDebug(true);
        // -- Create a new message --
        Message msg = new MimeMessage(session);
        // -- Set the FROM and TO fields --
        String nick="设计甲鱼红豆";
        try {
            nick=javax.mail.internet.MimeUtility.encodeText(nick);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msg.setFrom(new InternetAddress("gdccwxx@163.com"));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to,false));
        msg.setSubject(title);
        msg.setContent(content,"text/html; charset=utf-8");
        msg.setSentDate(new Date());
        Transport.send(msg);

        System.out.println("Message sent.");
        return true;
    }
}
