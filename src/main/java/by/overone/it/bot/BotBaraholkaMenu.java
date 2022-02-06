package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class BotBaraholkaMenu extends KeyboardConstructor {

    //    Кнопка барахолки
    public static InlineKeyboardMarkup sendBaraholka() {

       return createMarkup(createRow(createButton("Добавить объявление", "add advert"),
                        createButton("Показать объявления", "show add")));

    }


}
