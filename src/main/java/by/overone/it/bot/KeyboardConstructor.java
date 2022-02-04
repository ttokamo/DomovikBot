package by.overone.it.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardConstructor {

    public static InlineKeyboardButton createButton(String buttonText, String callback) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(callback);
        return button;
    }

    @SneakyThrows
    public static List<InlineKeyboardButton> createRow(InlineKeyboardButton ... values) {
        if (values == null) {
            throw new Exception("Метод getRow не может быть с пустым параметром");
        } else if (values.length > 2) {
            throw new Exception("В метод getRow нельзя передавать больше 2-х кнопок");
        } else {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.addAll(Arrays.asList(values));
            return row;
        }
    }

    @SneakyThrows
    public static InlineKeyboardMarkup createMarkup(List<InlineKeyboardButton> ... values) {
        if (values == null) {
            throw new Exception("Метод createMarkup не может быть с пустыми параметрами");
        }
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(values));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttons);
        return markup;
    }
}
