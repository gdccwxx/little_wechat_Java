package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepo  extends JpaRepository<BookTag, String> {
}
