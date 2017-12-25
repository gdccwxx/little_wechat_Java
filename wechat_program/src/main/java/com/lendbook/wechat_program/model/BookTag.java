package com.lendbook.wechat_program.model;

import javax.persistence.*;

// book's tag classes
@Entity
public class BookTag {
    @Id
    private String isbn;
    private String name;
    private Integer count;
    public BookTag (){}

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
