package com.sixpmpricetracker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotProperties {
    private String name;
    private String token;
}
