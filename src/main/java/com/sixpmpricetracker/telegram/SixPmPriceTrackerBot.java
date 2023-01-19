package com.sixpmpricetracker.telegram;

import com.sixpmpricetracker.parser.PriceParserFactory;
import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import com.sixpmpricetracker.properties.TelegramBotProperties;
import com.sixpmpricetracker.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class SixPmPriceTrackerBot extends TelegramLongPollingBot {
    private final ProductRepository productRepository;
    private final TelegramBotProperties telegramBotProperties;
    private final PriceParserFactory priceParserFactory;

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getName();
    }

    @Override
    public String getBotToken() {
        return telegramBotProperties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String productUrl = update.getMessage().getText();
        ProductParser productParser = priceParserFactory.getProductParser(productUrl);
        Product product = productParser.parse(productUrl);
        Long chatId = update.getMessage().getChatId();
        product.setChatId(chatId);
        productRepository.save(product);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(product.toString());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPriceChangedMassage(String priceChangeAction, Product oldProduct, Product newProduct) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(oldProduct.getChatId());
        String message = "The prise goes " + priceChangeAction + ".\n Old prise = " + oldProduct.getOriginPrice() + ". New price = " + newProduct.getOriginPrice() + "\n" + newProduct;
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
