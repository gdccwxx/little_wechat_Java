package com.lendbook.wechat_program.controller;

import com.lendbook.wechat_program.Tools.SendMail;
import com.lendbook.wechat_program.component.EmailBodyProperties;
import com.lendbook.wechat_program.component.EmailProperties;
import com.lendbook.wechat_program.model.User;
import com.lendbook.wechat_program.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class RegisterUser {
    final long time = 10*100*60;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SendMail sendMail;
    @Autowired
    private EmailBodyProperties emailBodyProperties;
    @PostMapping(value = "/register")
    public Map<String, String> register (@RequestParam("wechat") String wechat, @RequestParam("email") String email) {
        Map<String, String> map = new HashMap<>();
            User user = userRepo.findByWechat(wechat);
            if (userRepo.findByWechat(wechat) != null && userRepo.findByWechat(wechat).getStatus() == true) {
                map.put("result", "you already have registe");
            }else {
                if (user == null){
                    user = new User();
                }
                user.setMoney((float)0);
                user.setCreateVirTime(Calendar.getInstance().getTimeInMillis());
                String vircode = getVirCode().toString();
                user.setVircode(vircode);
                user.setWechat(wechat);
                user.setEmail(email);
                user.setStatus(false);
                userRepo.save(user);
                sendMail.sendHtmlMail(email, emailBodyProperties.getVirTitle(), emailBodyProperties.getVirBody() + "<h1>" + vircode + "</h1>");
                map.put("result","please check your email");
            }
        return map;
    }

    @PostMapping(value = "/register/vircode")
    public Map<String,String> confirmS (@RequestParam("wechat") String wechat, @RequestParam("vircode") String vircode) {
        Map<String, String> map = new HashMap<>();
        User user = userRepo.findByWechat(wechat);
        if (user == null) {
            map.put("result","please register first");
        }else{
            if ((user.getCreateVirTime() - Calendar.getInstance().getTimeInMillis()) < time){
                System.out.println(user.getVircode());
                System.out.print(vircode);
                if (vircode.equals(user.getVircode())){
                    map.put("result", "register succfully");
                    user.setStatus(true);
                }else {
                    map.put("result","vircode not confirm");
                }
            }else {
                map.put("result","out of time");
                String code = getVirCode().toString();
                user.setVircode(code);
                sendMail.sendHtmlMail(user.getEmail(), "验证码", emailBodyProperties.getVirBody() + "<h1>" + code + "</h1>");
            }
            userRepo.save(user);
        }
        return map;
    }
    public  String getVirCode(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }
}
