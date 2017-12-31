package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String wechat;
    private String email;
    private String vircode;
    private long createVirTime;     // create vircode timestramp;
    private Float Money;
    private Boolean status;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVircode() {
        return vircode;
    }

    public void setVircode(String vircode) {
        this.vircode = vircode;
    }

    public long getCreateVirTime() {
        return createVirTime;
    }

    public void setCreateVirTime(long createVirTime) {
        this.createVirTime = createVirTime;
    }

    public Float getMoney() {
        return Money;
    }

    public void setMoney(Float money) {
        Money = money;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
