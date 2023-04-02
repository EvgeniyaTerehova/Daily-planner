package ru.skypro.calendar.model;

import ru.skypro.calendar.TaskType;
import ru.skypro.calendar.exception.IncorrectArgumentException;
import ru.skypro.calendar.utils.ValidateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private TaskType tasktype;
    private LocalDateTime firstDate;
    private String description;
    private static Integer counter =1;
    private final Integer id;

    public Task(String title, TaskType tasktype, LocalDateTime firstDate, String description) throws IncorrectArgumentException {
        this.title = ValidateUtils.checkString(title);
        this.tasktype = tasktype;
        this.firstDate = firstDate;
        this.description = ValidateUtils.checkString(description);
        id = counter++;
    }



    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public TaskType getTasktype() {
        return tasktype;
    }
    public LocalDateTime getDateTime() {
        return firstDate;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTasktype(TaskType tasktype) {
        this.tasktype = tasktype;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public abstract boolean checkOccurrence(LocalDateTime localDateTime);
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) && tasktype == task.tasktype && firstDate.equals(task.firstDate) && description.equals(task.description) && id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, tasktype, firstDate, description, id);
    }

    @Override
    public String toString() {
        return "Task{" + "title='" + title + '\'' +
                ", tasktype=" + tasktype +
                ", firstDate=" + firstDate +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
