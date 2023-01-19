package com.sixpmpricetracker.config;

import com.sixpmpricetracker.telegram.SixPmPriceTrackerBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class TelegramBotConfig {
    private final SixPmPriceTrackerBot sixPmPriceTrackerBot;

    @PostConstruct
    public void registerTelegramBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(sixPmPriceTrackerBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
