package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApponintBook {
    @Id
    @GeneratedValue
    private int id;
    private String wechat;
    private String isbn;
    private Boolean distincAppoint;

    public ApponintBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getDistincAppoint() {
        return distincAppoint;
    }

    public void setDistincAppoint(Boolean distincAppoint) {
        this.distincAppoint = distincAppoint;
    }
}
