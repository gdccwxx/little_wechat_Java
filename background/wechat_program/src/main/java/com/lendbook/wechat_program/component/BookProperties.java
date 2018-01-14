package com.lendbook.wechat_program.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "doubanUrl")
public class BookProperties {
    private String isbnUrl;

    public String getIsbnUrl() {
        return isbnUrl;
    }

    public void setIsbnUrl(String isbnUrl) {
        this.isbnUrl = isbnUrl;
    }
}
