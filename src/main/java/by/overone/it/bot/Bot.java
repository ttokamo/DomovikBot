package by.overone.it.bot;

import by.overone.it.entity.BotStatus;
import by.overone.it.enums.BotStatusEnums;
import by.overone.it.question.QuestionsUser;
import by.overone.it.service.BotStatusService;
import by.overone.it.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {
    private static final String botUserName = "Sevatest_bot";
    private static final String token = "5153744354:AAHFFtN-uTwVLZNvM_juacVfxG8Mr4JLn2w";

    private BotStatus botStatus;
    @Autowired
    private BotStatusService botStatusService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionsUser questionsUser;

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
            String chatId = update.getMessage().getChatId().toString();
            if (start.startsWith("/start")) {
                execute(SendMessageConstructor.sendMessage("Привет в нашем телеграм-боте, " + username,
                        update.getMessage().getChatId().toString(), true,
                        BotMainMenu.sendMainMenu()));
            } else if (start.startsWith("/stop")) {
                execute(SendMessageConstructor.sendMessage("До свидания",
                        update.getMessage().getChatId().toString(), false, null));
            } else if (botStatusService.getBotStatusByUsername(username) != null && update.getMessage().hasText()) {
                execute(questionsUser.questions(username, chatId, update.getMessage().getText()));
            }
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String callback = update.getCallbackQuery().getData();
            if (callback.startsWith("Menu")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на меню",
                        chatId, true,
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
                        chatId, false, null));

            } else if (callback.equals("Baraholka")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на барахолка",
                        chatId, true,
                        BotBaraholkaMenu.sendBaraholka()));

            } else if (callback.equals("JKH")) {
                execute(SendMessageConstructor.sendMessage("Вы нажали на ЖКХ",
                        chatId, true,
                        BotJKHMenu.sendJKHMenu()));

            } else if (callback.equals("add advert")) {
                String username = update.getCallbackQuery().getFrom().getUserName();
                botStatus = new BotStatus();
                botStatus.setStatus(BotStatusEnums.ASK_ABOUT_MARKET_DESCRIPTION.name());
                botStatus.setUsername(username);
                botStatusService.saveBotStatus(botStatus);
                execute(SendMessageConstructor.sendMessage("Введите описание вашего объявления: ",
                        chatId, false, null));
            } else if (callback.equals("Yes")) {
                botStatusService.updateBotStatus(update.getCallbackQuery().getFrom().getUserName(), BotStatusEnums.ASK_ABOUT_CAR_NUMBER.name());
            } else if (callback.equals("No")) {
                botStatusService.updateBotStatus(update.getCallbackQuery().getFrom().getUserName(), BotStatusEnums.FINISH.name());
            }
        }
    }
}