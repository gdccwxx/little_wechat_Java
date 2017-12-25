package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.model.Book;
import com.lendbook.wechat_program.model.LendBook;
import com.lendbook.wechat_program.repository.BookRepo;
import com.lendbook.wechat_program.repository.LendBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.EOFException;
import java.util.*;

@RestController
public class UserLendBook {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private LendBookRepo lendBookRepo;

    @GetMapping(value = "/book/all")
    public List<Book> getAllBook(){
      return bookRepo.findAll();
    }

    @GetMapping(value = "/book/about/{isbn}")
    public Book findByIsbn13(@PathVariable("isbn") String isbn13)
    {
        return bookRepo.findByIsbn13(isbn13);
    }

   @GetMapping(value = "/book/newbook/{num}" )
    public List<Book> getNewBook(@PathVariable("num") Integer num)
    {
         return  bookRepo.findNewBook(num);
    }

   @GetMapping(value = "/book/oldbook/{num}" )
    public List<Book>  getOldBook(@PathVariable("num")Integer num)
    {
        return  bookRepo.findOldBook(num);
    }

   @PostMapping(value = "/book/lend/{isbn}")
    public Map<String,String> lendBook(@PathVariable("isbn") String isbn13)
   {
       Map<String,String> res = new HashMap<String, String>();

        if(bookRepo.findByIsbn13(isbn13)==null)
       {
           res.put("result","未找到此书");
       }
       else {
           Book book = bookRepo.findByIsbn13(isbn13);
           int num1 = book.getStoreNum();
           int num2 = book.getLendNum();
           if (num1 > 0) {
               num1 = num1 - 1;
               num2 = num2 + 1;
               book.setStoreNum(num1);
               book.setLendNum(num2);
               bookRepo.save(book);
               res.put("result", "成功");
           } else {
               res.put("result", "失败");
               System.out.print("库存不足");
           }
       }
       return  res;
   }

    @PostMapping(value = "book/search")
    public Book searchBook(@RequestParam("searchStr") String searchStr)
    {
        return bookRepo.findSearchStr(searchStr);
    }



    @GetMapping(value = "book/id/{id}")
    public  Book findBookById(@PathVariable("id") Integer id)
     {
         return  findBookById(id);
     }

    @GetMapping(value = "book/isbn/{isbn}")
    public Book findBookByIsbn(@PathVariable("isbn") String isbn13)
    {
        return  findByIsbn13(isbn13);
    }

    //发送邮件

    //确认邮件

    @PostMapping(value = "user/lendbook")
    public Map<String,String> userLendBook(@RequestParam("wechat") String wechat, @RequestParam("isbn") String isbn13)
    {
        Map<String,String> res = new HashMap<String, String>();
        if(bookRepo.findByIsbn13(isbn13)== null) {
            res.put("result","没有此书!");
        }
        else{
                Calendar lend = Calendar.getInstance();
                Calendar retu = Calendar.getInstance();
                LendBook lendBook = new LendBook();
                lendBook.setWechat(wechat);
                lendBook.setIsbn(isbn13);
                lendBook.setLendTime(lend);
                retu.add(Calendar.MONTH,2);
                lendBook.setReturnTime(retu);
                lendBook.setDistincReturn(false);
                lendBookRepo.save(lendBook);
                res.put("result","借书成功!");
        }
        return  res;
    }

}

