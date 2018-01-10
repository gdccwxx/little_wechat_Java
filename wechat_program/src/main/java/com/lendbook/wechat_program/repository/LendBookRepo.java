package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LendBookRepo extends JpaRepository<LendBook, String> {

    @Query(value = "SELECT * FROM  lend_book  WHERE isbn = ?2 AND wechat=?1 AND distinc_return = FALSE ",nativeQuery = true)
    public LendBook WetherLendBook(String wechat,String isbn);

    @Query(value = "SELECT * from  lend_book WHERE  wechat=?1 /*AND distinc_history=TRUE*/",nativeQuery = true)
    public List<LendBook> LendHistory(String wechat);
}
