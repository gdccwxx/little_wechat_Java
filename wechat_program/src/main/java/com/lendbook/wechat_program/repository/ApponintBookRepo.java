package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.ApponintBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApponintBookRepo extends JpaRepository<ApponintBook, String> {
    @Query(value = "SELECT * FROM  apponint_book  WHERE isbn = ?1 AND distincAppoint = true",nativeQuery = true )
    public List<ApponintBook> findByIsbn(String isbn);
}
