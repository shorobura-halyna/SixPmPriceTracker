//package com.sixpmpricetracker.telegram;
//
//import com.sixpmpricetracker.model.Product;
//import com.sixpmpricetracker.parser.ProductParser;
//import com.sixpmpricetracker.properties.TelegramBotProperties;
//import com.sixpmpricetracker.repository.ProductRepository;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//class SixPmPriceTrackerBotTest {
//    private static final long CHAT_ID = 1;
//
//    @Mock
//    private ProductParser parser;
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private TelegramBotProperties telegramBotProperties;
//    @InjectMocks
//    private SixPmPriceTrackerBot sixPmPriceTrackerBot;
//
//    @SneakyThrows
//    @Test
//    void shouldSendPriceChangedMassage() {
//        //given
//        Product oldProduct = Product.builder()
//                .name("Dolce Vita")
//                .trackedFrom(LocalDateTime.now())
//                .url("https://www.6pm.com/p/dolce-vita-peyton-cafe-stella/product/9821950/color/915642")
//                .originPrice(29.60)
//                .chatId(CHAT_ID)
//                .build();
//
//        Product newProduct = Product.builder()
//                .name("Dolce Vita")
//                .trackedFrom(LocalDateTime.now())
//                .url("https://www.6pm.com/p/dolce-vita-peyton-cafe-stella/product/9821950/color/915642")
//                .originPrice(35.90)
//                .build();
//
//        String priceChangeAction = "UP";
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(CHAT_ID);
//        String message = "The prise goes " + priceChangeAction + ".\n Old prise = " + oldProduct.getOriginPrice() + ". New price = " + newProduct.getOriginPrice() + "\n" + newProduct;
//        sendMessage.setText(message);
//
//        //when
//        sixPmPriceTrackerBot.sendPriceChangedMassage(priceChangeAction, oldProduct, newProduct);
//        //then
////        verify(sixPmPriceTrackerBot).execute(sendMessage);
//    }
//
//}
