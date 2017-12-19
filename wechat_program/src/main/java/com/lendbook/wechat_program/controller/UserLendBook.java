package com.lendbook.wechat_program.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLendBook {

    @GetMapping(value = "/book/all")
    public String getAllBook(){
        return "hello";
    }
}
