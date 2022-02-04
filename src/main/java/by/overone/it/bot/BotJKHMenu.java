package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class BotJKHMenu {

    public static InlineKeyboardMarkup sendJKHMenu() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("ЖКХ твоего города");
        inlineKeyboardButton1.setCallbackData("In your city");

        inlineKeyboardButton2.setText("Расписание уборок");
        inlineKeyboardButton2.setCallbackData("Timetable");

        inlineKeyboardButton3.setText("Рупор ЖКХ");
        inlineKeyboardButton3.setCallbackData("Mouthpiece");

        List<InlineKeyboardButton> inlineKeyboardButtonsList = new ArrayList<>();
        inlineKeyboardButtonsList.add(inlineKeyboardButton1);
        inlineKeyboardButtonsList.add(inlineKeyboardButton2);
        inlineKeyboardButtonsList.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(inlineKeyboardButtonsList);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

}
