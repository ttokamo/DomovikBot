package by.overone.it.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


public class BotJKHMenu extends KeyboardConstructor {

    public static InlineKeyboardMarkup sendJKHMenu() {

       return createMarkup(createRow(createButton("ЖКХ твоего города", "In your city"),
                       createButton("Расписание уборок", "Timetable")),
               createRow(createButton("Рупор ЖКХ", "Mouthpiece")));
    }

}
