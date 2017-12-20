package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.model.Book;
import com.lendbook.wechat_program.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLendBook {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping(value = "/book/all")
    public List<Book> getAllBook(){
        return bookRepo.findAll();
    }
}
