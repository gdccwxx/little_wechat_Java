package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
@Entity
public class LendBook {
    @Id
    @GeneratedValue
    private Integer id;
    private String wechat;
    private String isbn;
    private Calendar lendTime;
    private Calendar returnTime;
    private Boolean distincReturn;
    private Boolean distincHistory;

    public LendBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Calendar getLendTime() {
        return lendTime;
    }

    public void setLendTime(Calendar lendTime) {
        this.lendTime = lendTime;
    }

    public Calendar getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Calendar returnTime) {
        this.returnTime = returnTime;
    }

    public Boolean getDistincReturn() {
        return distincReturn;
    }

    public void setDistincReturn(Boolean distincReturn) {
        this.distincReturn = distincReturn;
    }

    public Boolean getDistincHistory() {
        return distincHistory;
    }

    public void setDistincHistory(Boolean distincHistory) {
        this.distincHistory = distincHistory;
    }
}
