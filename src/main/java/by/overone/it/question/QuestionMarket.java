package by.overone.it.question;

import by.overone.it.entity.Market;
import by.overone.it.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class QuestionMarket {

    @Autowired
    private MarketService marketService;

    private Market market;

    public SendMessage sendMessage(String username, String chatId, String message){
        final String description = "Введите описание вашего объявления";
        final String finish = "Ваше объявление успешно зарегистрировано";
        market = new Market();
        SendMessage sendMessage = new SendMessage();
    }
}
