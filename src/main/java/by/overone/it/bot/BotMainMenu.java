package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BotMainMenu extends KeyboardConstructor {

    //    Главное меню
    public static InlineKeyboardMarkup sendMainMenu() {

        return createMarkup(createRow(createButton("Меню", "Menu"),
                        createButton("Регистрация", "Registration")),
                createRow(createButton("Выход", "Exit")));

    }

}
