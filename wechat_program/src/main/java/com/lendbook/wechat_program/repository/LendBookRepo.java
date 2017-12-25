package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendBookRepo extends JpaRepository<LendBook, String> {
}
