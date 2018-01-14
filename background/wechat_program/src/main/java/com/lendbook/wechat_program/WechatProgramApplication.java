package com.lendbook.wechat_program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WechatProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatProgramApplication.class, args);
	}
}
