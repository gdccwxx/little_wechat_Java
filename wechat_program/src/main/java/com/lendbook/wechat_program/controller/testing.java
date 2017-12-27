package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.Tools.SendMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testing {
    @Autowired
    private JavaMailSender javaMailSender;

    private String to = "765553928@qq.com";
    @GetMapping(value = "/testing")
    public void sendEmail()  {
        new SendMail().sendHtmlMail(to, "主题：html邮件", "<h1>today is perfect day right???</h1><h2>i don't konw why it is 554</h2>",javaMailSender);
    }
}
