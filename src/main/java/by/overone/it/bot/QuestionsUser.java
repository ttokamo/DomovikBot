package by.overone.it.bot;

import by.overone.it.entity.User;
import by.overone.it.enums.BotStatusEnums;
import by.overone.it.service.BotStatusService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionsUser {

    @Autowired
    private BotStatusService botStatusService;
    @Autowired
    private UserService userService;
    @Autowired
    private  Bot bot;
    private User user;

    @SneakyThrows
    public void questions(String username, String chatId, String message) {
        final String secondName = "Введите фамилию:";
        final String street = "Ведите улицу:";
        final String house = "Ведите номер дома:";
        final String porch = "Ведите номер подъезда:";
        final String floor = "Ведите номер этажа:";
        final String flat = "Ведите номер квартиры:";
        final String telephoneNumber = "Ведите номер телефона:";
        final String carNumber = "Ведите регистрационный номер машины:";

        String status = botStatusService.getBotStatusByUsername(username);
        if (status.equals(BotStatusEnums.ASK_ABOUT_SECOND_NAME.toString())) {
            user = new User();
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_STREET.toString());
            bot.execute(SendMessageConstructor.sendMessage(secondName, chatId, false, null));
            user.setUsername(username);
            user.setName(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_STREET.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_HOUSE.toString());
            bot.execute(SendMessageConstructor.sendMessage(street, chatId, false, null));
            user.setStreet(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_HOUSE.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_PORCH.toString());
            bot.execute(SendMessageConstructor.sendMessage(house, chatId, false, null));
            user.setHouse(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_PORCH.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLOOR.toString());
            bot.execute(SendMessageConstructor.sendMessage(porch, chatId, false, null));
            user.setPorchNumber(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLOOR.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_FLAT.toString());
            bot.execute(SendMessageConstructor.sendMessage(floor, chatId, false, null));
            user.setFloor(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_FLAT.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString());
            bot.execute(SendMessageConstructor.sendMessage(flat, chatId, false, null));
            user.setFlat(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_TELEPHONE_NUMBER.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.ASK_ABOUT_CAR.toString());
            bot.execute(SendMessageConstructor.sendMessage(telephoneNumber, chatId, false, null));
            user.setTelephoneNumber(message);
        } else if (status.equals(BotStatusEnums.ASK_ABOUT_CAR_NUMBER.toString())) {
            botStatusService.updateBotStatus(chatId, BotStatusEnums.FINISH.toString());
            bot.execute(SendMessageConstructor.sendMessage(carNumber, chatId, false, null));
            user.setCarNumber(message);
        } else if (status.equals(BotStatusEnums.FINISH.toString())) {
            botStatusService.deleteBotStatusByUsername(chatId);
            userService.saveUser(user);
        }
    }
}
