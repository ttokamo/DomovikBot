package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BotBaraholkaMenu {

    //    Кнопка барахолки
    public static InlineKeyboardMarkup sendBaraholka() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Добавить объявление");
        inlineKeyboardButton1.setCallbackData("add advert");

        inlineKeyboardButton2.setText("Показать объявления");
        inlineKeyboardButton2.setCallbackData("show add");

        List<InlineKeyboardButton> inlineKeyboardButtonsList = new ArrayList<>();
        inlineKeyboardButtonsList.add(inlineKeyboardButton1);
        inlineKeyboardButtonsList.add(inlineKeyboardButton2);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(inlineKeyboardButtonsList);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }


}
