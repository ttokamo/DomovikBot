package by.overone.it.bot;

import by.overone.it.entity.User;
import by.overone.it.enums.BotStatusEnums;
import by.overone.it.service.BotStatusService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class QuestionsUser {

    @Autowired
    private BotStatusService botStatusService;
    @Autowired
    private UserService userService;

    private SendMessage sendMessage;
    private User user;

    @SneakyThrows
    public SendMessage questions(String username, String chatId, String message) {
        final String secondName = "Введите фамилию:";
        final String street = "Ведите улицу:";
        final String house = "Ведите номер дома:";
        final String porch = "Ведите номер подъезда:";
        final String floor = "Ведите номер этажа:";
        final String flat = "Ведите номер квартиры:";
        final String telephoneNumber = "Ведите номер телефона:";
        final String carNumber = "Ведите регистрационный номер машины:";
        final String end = "Анкета заполнена";

        String status = botStatusService.getBotStatusByUsername(username);
        if (status.equals(BotStatusEnums.ASK_ABOUT_SECOND_NAME.toString())) {
            user = new User();
            sendMessage = SendMessageConstructor.sendMessage(message, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_STREET.toString());
            sendMessage.setText(secondName);
//            bot.execute(SendMessageConstructor.sendMessage(secondName, chatId, false, null));
            user.setUsername(username);
            user.setName(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_STREET.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_HOUSE.toString());
            sendMessage.setText(street);
            user.setStreet(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_HOUSE.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_PORCH.toString());
            sendMessage.setText(house);
            user.setHouse(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_PORCH.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLOOR.toString());
            sendMessage.setText(porch);
            user.setPorchNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLOOR.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLAT.toString());
            sendMessage.setText(floor);
            user.setFloor(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLAT.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString());
            sendMessage.setText(flat);
            user.setFlat(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_CAR.toString());
            sendMessage.setText(telephoneNumber);
            user.setTelephoneNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_CAR_NUMBER.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.FINISH.toString());
            sendMessage.setText(carNumber);
            user.setCarNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.FINISH.toString())) {
            botStatusService.deleteBotStatusByUsername(chatId);
            userService.saveUser(user);
            sendMessage.setText(end);
        }
        return sendMessage;
    }
}
