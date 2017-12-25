package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;
@Entity
public class LendBook {
    @Id
    private String wechat;
    private String isbn;
    private Calendar lendTime;
    private Calendar returnTime;
    private Boolean distincReturn;

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setLendTime(Calendar lendTime) {
        this.lendTime = lendTime;
    }

    public void setReturnTime(Calendar returnTime) {
        this.returnTime = returnTime;
    }

    public void setDistincReturn(Boolean distincReturn) {
        this.distincReturn = distincReturn;
    }
}
