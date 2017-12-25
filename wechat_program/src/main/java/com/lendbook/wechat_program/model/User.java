package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @NotNull
    private String wechat;
    private String email;
    private String vircode;
    private long createVirTime;     // create vircode timestramp;
}
