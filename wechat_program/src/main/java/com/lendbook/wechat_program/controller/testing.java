package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.Tools.SendMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class testing {
    @Autowired
    private SendMail sendMail;
    @PostMapping(value = "/testing")
    public void sendEmail(@RequestParam("to") String to,@RequestParam("title") String title, @RequestParam("content") String content)  {
        try{
            sendMail.sendHtmlMail(to, title, content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
