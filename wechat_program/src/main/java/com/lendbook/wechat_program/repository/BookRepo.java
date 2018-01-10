package com.lendbook.wechat_program.repository;

import com.lendbook.wechat_program.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo  extends JpaRepository<Book, Integer> {


    //通过ISBN号查询
    public Book findByIsbn13(String isbn13);

    //查询前n本新书
    @Query(value = "SELECT * FROM  Book  ORDER  BY  publish_date DESC  limit ?1",nativeQuery = true )
    public List<Book> findNewBook(Integer num);

    //查询旧书且好评率高的前n本书
    @Query(value = "SELECT * FROM Book WHERE distinc_old_or_new = TRUE ORDER BY rating DESC limit ?1 ",nativeQuery = true)
    public List<Book> findOldBook(Integer num);

    //根据isbn号或者作者查书
    @Query(value = "SELECT * FROM Book WHERE isbn13=?1 /*OR  author LIKE ?1%? */  ",nativeQuery = true)
    public  Book findSearchStr(String searchStr);

    //根据id号查书
    public Book findById(Integer id);
}