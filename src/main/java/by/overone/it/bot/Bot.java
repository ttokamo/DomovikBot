package by.overone.it.bot;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

// update.getMessage() - работа сообщениями
//    update.getCallbackQuerry - работа с кнопками


@Component
public class Bot extends TelegramLongPollingBot {

    private static final String botUserName = "Sevatest_bot";
    private static final String token = "5222878356:AAH6WN4X1VlZQeyJZKd8q8QdEOJ0PgykEJ0";




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
                execute(sendMessageWithButtons(update.getMessage().getChatId(), sendInlineKeyBoardMessageMainMenu(),
                        "Привет в нашем телеграм-боте " + username));
            } else if (start.startsWith("/stop")) {
                execute(sendMessageWithoutButtons(update.getMessage().getChatId(), "До свидания"));
            }
        } else if (update.hasCallbackQuery()) {
            String callback = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callback.startsWith("Menu")) {
                execute(sendMessageWithButtons(chatId, BotSecondMenu.sendSecondMenu(), "Вы нажали на меню"));

            } else if (callback.equals("Registration")) {
                execute(sendMessageWithoutButtons(chatId, "Добро пожаловать в регистрацию." +
                        " Введите ваше имя: "));

            } else if (callback.equals("Exit")) {
                execute(sendMessageWithButtons(chatId, sendInlineKeyBoardMessageMainMenu(),
                        "Если вы уверены что хотите выйти кликните сюда -> " + "/stop"));

            } else if (callback.equals("Baraholka")) {
                execute(sendMessageWithButtons(chatId, BotBaraholkaMenu.sendBaraholka(), "Вы нажали на барахолка"));

            } else if (callback.equals("JKH")) {
                execute(sendMessageWithButtons(chatId, BotJKHMenu.sendJKHMenu(), "Вы нажали на ЖКХ"));

            } else if (callback.equals("add advert")) {
                execute(sendMessageWithoutButtons(chatId, "Введите ваше имя для, чтобы зарегистрировать Вас"));
            }
        }
    }

    //    Главное меню
    public static InlineKeyboardMarkup sendInlineKeyBoardMessageMainMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Меню");
        inlineKeyboardButton1.setCallbackData("Menu");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Регистрация");
        inlineKeyboardButton2.setCallbackData("Registration");

        List<InlineKeyboardButton> keyboardButtonsRaw1 = new ArrayList<>();
        keyboardButtonsRaw1.add(inlineKeyboardButton1);
        keyboardButtonsRaw1.add(inlineKeyboardButton2);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Выход");
        inlineKeyboardButton3.setCallbackData("Exit");

        List<InlineKeyboardButton> keyboardButtonsRaw2 = new ArrayList<>();
        keyboardButtonsRaw2.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRaw1);
        rowList.add(keyboardButtonsRaw2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }


    //    Отдельный метод сендмесседж с кнопками
    public SendMessage sendMessageWithButtons(long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

//    Отдельный метод сендмесседж без кнопок
    public SendMessage sendMessageWithoutButtons(long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));
        return sendMessage;
    }

}