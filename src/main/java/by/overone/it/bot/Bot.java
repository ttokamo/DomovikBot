package by.overone.it.bot;

import by.overone.it.entity.BotStatus;
import by.overone.it.enums.BotStatusEnums;
import by.overone.it.service.BotStatusService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

// update.getMessage() - работа сообщениями
// update.getCallbackQuerry - работа с кнопками

@Component
public class Bot extends TelegramLongPollingBot {

    private static final String botUserName = "Sevatest_bot";
    private static final String token = "5153744354:AAHFFtN-uTwVLZNvM_juacVfxG8Mr4JLn2w";
    private BotStatus botStatus;
    @Autowired
    private BotStatusService botStatusService;
    @Autowired
    private UserService userService;


    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
//        Вводим переменную против NullPointerException
//        Главное меню
        if (update.hasMessage()) {
            String username = update.getMessage().getFrom().getFirstName();
            String start = update.getMessage().getText();
            if (start.startsWith("/start")) {
                execute(SendMessageConstructor.sendMessage("Привет в нашем телеграм-боте, " + username,
                        update.getMessage().getChatId().toString(), true,
                        BotMainMenu.sendMainMenu()));
            } else if (start.startsWith("/stop")) {
                execute(SendMessageConstructor.sendMessage("До свидания",
                        update.getMessage().getChatId().toString(), false, null));
            } else if (botStatusService.getBotStatusByUsername(username) != null && update.getMessage().hasText()) {

            }

        } else if (update.hasCallbackQuery()) {
            String callback = update.getCallbackQuery().getData();
            if (callback.startsWith("Menu")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на меню",
                        update.getCallbackQuery().getMessage().getChatId().toString(), true,
                        BotSecondMenu.sendSecondMenu()));

            } else if (callback.equals("Registration")) {
                String username = update.getCallbackQuery().getFrom().getUserName();
                String status = botStatusService.getBotStatusByUsername(username);
                if (status != null) {
                    botStatusService.deleteBotStatusByUsername(username);
                    userService.deleteUserByUsername(username);
                }
                botStatus = new BotStatus();
                botStatus.setStatus(BotStatusEnums.ASK_ABOUT_SECOND_NAME.name());
                botStatus.setUsername(username);
                botStatusService.saveBotStatus(botStatus);
                execute(SendMessageConstructor.sendMessage("Добро пожаловать в регистрацию.Введите ваше имя: ",
                        update.getCallbackQuery().getMessage().getChatId().toString(), false, null));

            } else if (callback.equals("Exit")) {
                execute(SendMessageConstructor.sendMessage("Если вы уверены что хотите выйти кликните сюда -> /stop",
                        update.getCallbackQuery().getMessage().getChatId().toString(), false, null));

            } else if (callback.equals("Baraholka")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на барахолка",
                        update.getCallbackQuery().getMessage().getChatId().toString(), true,
                        BotBaraholkaMenu.sendBaraholka()));

            } else if (callback.equals("JKH")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на ЖКХ",
                        update.getCallbackQuery().getMessage().getChatId().toString(), true,
                        BotJKHMenu.sendJKHMenu()));

            } else if (callback.equals("add advert")) {
                execute(SendMessageConstructor.sendMessage("Введите описание вашего объявления: ",
                        update.getCallbackQuery().getMessage().getChatId().toString(), false, null));
            }

        }

    }

}