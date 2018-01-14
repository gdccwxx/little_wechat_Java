package com.lendbook.wechat_program.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "EmailTemplate")
public class EmailBodyProperties {
    private String virTitle;
    private String virBody;
    private String appointTitle;

    public String getVirTitle() {
        return virTitle;
    }

    public void setVirTitle(String virTitle) {
        this.virTitle = virTitle;
    }

    public String getVirBody() {
        return virBody;
    }

    public void setVirBody(String virBody) {
        this.virBody = virBody;
    }

    public String getAppointTitle() {
        return appointTitle;
    }

    public void setAppointTitle(String appointTitle) {
        this.appointTitle = appointTitle;
    }
}
