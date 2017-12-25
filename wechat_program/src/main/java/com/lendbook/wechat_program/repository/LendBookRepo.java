package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LendBookRepo extends JpaRepository<LendBook, String> {

    @Query(value = "SELECT * FROM  lend_book  WHERE isbn = ?1 AND wechat=?2",nativeQuery = true)
    public LendBook WetherLendBook(String wechat,String isbn);
}
