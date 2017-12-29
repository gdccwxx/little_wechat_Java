package com.lendbook.wechat_program.component;

import com.lendbook.wechat_program.Tools.SendMail;
import com.lendbook.wechat_program.model.ApponintBook;
import com.lendbook.wechat_program.repository.ApponintBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailTellSchedule {
    @Autowired
    private ApponintBookRepo apponintBookRepo;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private EmailBodyProperties emailBodyProperties;
    @Scheduled(cron="0 0/60 3-4 * * ?")
    public void EmailTellMan(){
        System.out.println("hello world!");
        List<ApponintBook> lists = apponintBookRepo.findByDistincTell();
        for(ApponintBook man: lists){
            sendMail.sendHtmlMail(man.getEmail(), emailBodyProperties.getAppointTitle(), "您订阅的书籍${man.getTitle()}可以被借阅了！");
            man.setDistincTell(false);
        }
        apponintBookRepo.save(lists);
    }
}
