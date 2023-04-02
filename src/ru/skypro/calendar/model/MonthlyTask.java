package ru.skypro.calendar.model;

import ru.skypro.calendar.TaskType;
import ru.skypro.calendar.exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, TaskType tasktype, LocalDateTime firstDate, String description) throws IncorrectArgumentException {
        super(title, tasktype, firstDate, description);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requesteDate) {
        return getDateTime().getDayOfMonth() == (requesteDate.getDayOfMonth());
    }
}
