package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.ApponintBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApponintBookRepo extends JpaRepository<ApponintBook, String> {
    @Query(value = "SELECT * FROM  apponint_book  WHERE isbn = ?1 AND distinc_appoint = true",nativeQuery = true )
    public List<ApponintBook> findByIsbn(String isbn);
    @Query(value = "SELECT * FROM  apponint_book  WHERE distinc_tell = true AND distinc_appoint = true",nativeQuery = true )
    public List<ApponintBook> findByDistincTell();
}
