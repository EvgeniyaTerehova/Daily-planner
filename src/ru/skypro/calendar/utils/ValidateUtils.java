package ru.skypro.calendar.utils;

import ru.skypro.calendar.exception.IncorrectArgumentException;

public class ValidateUtils {
    public static String checkString (String str) throws IncorrectArgumentException {
        if (str == null || str.isEmpty() || str.isBlank()){
            throw new IncorrectArgumentException("Не корректный ввод");
        } else {
            return str;
        }
    }
}
