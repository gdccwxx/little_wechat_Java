package com.lendbook.wechat_program.model;

import javax.persistence.*;

// book's tag classes
@Entity
public class BookTag {
    @Id
    @GeneratedValue
    private Integer id;

    private String isbn;
    private String name;
    private Integer count;
    public BookTag(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
