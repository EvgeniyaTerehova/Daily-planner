package ru.skypro.calendar.model;

import ru.skypro.calendar.TaskType;
import ru.skypro.calendar.exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class OncelyTask extends Task{

    public OncelyTask(String title, TaskType tasktype, LocalDateTime firstDate, String description) throws IncorrectArgumentException {
        super(title, tasktype, firstDate, description);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requesteDate) {
        return getDateTime().toLocalDate().equals(requesteDate.toLocalDate());
    }
}