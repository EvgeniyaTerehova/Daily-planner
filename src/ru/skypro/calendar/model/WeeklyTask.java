package ru.skypro.calendar.model;

import ru.skypro.calendar.TaskType;
import ru.skypro.calendar.exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, TaskType tasktype, LocalDateTime date, String description) throws IncorrectArgumentException {
        super(title, tasktype, date, description);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requesteDate) {
        return getDateTime().getDayOfWeek().equals(requesteDate.getDayOfWeek());
    }
}
