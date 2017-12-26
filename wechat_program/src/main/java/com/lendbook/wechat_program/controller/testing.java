package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.Tools.SendMail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testing {
    @GetMapping(value = "/testing")
    public void test(){
        SendMail sendMail = new SendMail();
        sendMail.sendHtmlMail();
    }
}
