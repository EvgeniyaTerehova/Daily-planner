package ru.skypro.calendar.model;

import ru.skypro.calendar.TaskType;
import ru.skypro.calendar.exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.time.Year;

public class YerlyTask extends Task {
    public YerlyTask(String title, TaskType tasktype, LocalDateTime date, String description) throws IncorrectArgumentException {
        super(title, tasktype, date, description);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requesteDate) {
        return (getDateTime().getMonth() == requesteDate.getMonth() && getDateTime().getMonth() == requesteDate.getMonth());
    }


}
