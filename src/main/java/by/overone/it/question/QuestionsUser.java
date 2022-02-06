package by.overone.it.question;

import by.overone.it.bot.KeyboardConstructor;
import by.overone.it.bot.SendMessageConstructor;
import by.overone.it.entity.Market;
import by.overone.it.entity.User;
import by.overone.it.enums.BotStatusEnums;
import by.overone.it.service.BotStatusService;
import by.overone.it.service.MarketService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static by.overone.it.question.Questions.*;

@Component
public class QuestionsUser {

    @Autowired
    private BotStatusService botStatusService;
    @Autowired
    private UserService userService;
    @Autowired
    private MarketService marketService;

    private SendMessage sendMessage;

    private User user;

    @SneakyThrows
    public SendMessage questions(String username, String chatId, String message) {

        String status = botStatusService.getBotStatusByUsername(username);
        if (status.equals(BotStatusEnums.ASK_ABOUT_SECOND_NAME.toString())) {
            user = new User();
            sendMessage = SendMessageConstructor.sendMessage(secondName, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_STREET.toString());
            user.setUsername(username);
            user.setName(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_STREET.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(street, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_HOUSE.toString());
            user.setSecondName(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_HOUSE.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(house, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_PORCH.toString());
            user.setStreet(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_PORCH.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(porch, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_FLOOR.toString());
            user.setHouse(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLOOR.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(floor, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_FLAT.toString());
            user.setPorchNumber(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLAT.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(flat, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString());
            user.setFloor(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(telephoneNumber, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_CAR.toString());
            user.setFlat(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_CAR.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(car, chatId, true, sendCar());
            botStatusService.updateBotStatus(username, BotStatusEnums.ASK_ABOUT_CAR_NUMBER.toString());
            user.setTelephoneNumber(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_CAR_NUMBER.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(carNumber, chatId, false, null);
            botStatusService.updateBotStatus(username, BotStatusEnums.FINISH.toString());
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_MARKET_DESCRIPTION.toString())) {
            Market market = new Market();
            botStatusService.deleteBotStatusByUsername(username);
            market.setDescription(message);
            market.setUsername(username);
            sendMessage = SendMessageConstructor.sendMessage(finishMarket, chatId, false, null);
            marketService.saveAd(market);
        } else if (status.equals(BotStatusEnums.FINISH.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(end, chatId, false, null);
            botStatusService.deleteBotStatusByUsername(username);
            user.setCarNumber(message);
            userService.saveUser(user);
        }
        return sendMessage;

    }

    private static InlineKeyboardMarkup sendCar() {
        return KeyboardConstructor.createMarkup(
                KeyboardConstructor.createRow(KeyboardConstructor.createButton("Да", "Yes"),
                        KeyboardConstructor.createButton("Нет", "No")));
    }
}
