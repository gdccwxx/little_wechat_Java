package com.lendbook.wechat_program.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class LendBook {
    @Id
    private String wechat;
    private String isbn;
    private Date lendTime;
    private Date returnTime;
    private Boolean distincReturn;
}
