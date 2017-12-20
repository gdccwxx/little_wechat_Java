package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo  extends JpaRepository<Book, Integer> {

}