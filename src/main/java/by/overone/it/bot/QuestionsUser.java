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
            sendMessage = SendMessageConstructor.sendMessage(secondName, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_STREET.toString());
            user.setUsername(username);
            user.setName(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_STREET.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(street, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_HOUSE.toString());
            user.setStreet(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_HOUSE.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(house, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_PORCH.toString());
            user.setHouse(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_PORCH.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(porch, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLOOR.toString());
            user.setPorchNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLOOR.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(floor, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLAT.toString());
            user.setFloor(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLAT.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(flat, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString());
            user.setFlat(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(telephoneNumber, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_CAR.toString());
            user.setTelephoneNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_CAR_NUMBER.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(carNumber, chatId, false, null);
            botStatusService.updateBotStatus(chatId, BotStatusEnums.FINISH.toString());
            user.setCarNumber(message);
            return sendMessage;
        } else if (status.equals(BotStatusEnums.FINISH.toString())) {
            sendMessage = SendMessageConstructor.sendMessage(end, chatId, false, null);
            botStatusService.deleteBotStatusByUsername(chatId);
            userService.saveUser(user);
        }
        return sendMessage;
    }
}
