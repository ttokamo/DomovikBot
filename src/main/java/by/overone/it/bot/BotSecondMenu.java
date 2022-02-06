package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BotSecondMenu extends KeyboardConstructor {

    //    Кнопка меню
    public static InlineKeyboardMarkup sendSecondMenu() {

       return createMarkup(createRow(createButton("Мои соседи", "Neighbor"),
                        createButton("Барахолка", "Baraholka")),
                createRow(createButton("ЖКХ", "JKH"),
                        createButton("Кто подпер?", "Breaker")),
                createRow(createButton("Справка", "Button reference")));

    }

}
