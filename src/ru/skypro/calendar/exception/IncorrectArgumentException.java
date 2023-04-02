package ru.skypro.calendar.exception;

public class IncorrectArgumentException extends Exception {
    private String argument;

    public IncorrectArgumentException(String argument) {
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return argument + "Не корректный ввод";
    }
}
