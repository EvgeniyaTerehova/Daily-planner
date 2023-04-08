package ru.skypro.calendar;

import ru.skypro.calendar.exception.IncorrectArgumentException;
import ru.skypro.calendar.exception.TaskNotFoundException;
import ru.skypro.calendar.model.*;
import ru.skypro.calendar.utils.ValidateUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MyCalendar {
    private static final Map<Integer, Task> actualTasks = new HashMap<>();

    public static void addTask(Scanner scanner) throws IncorrectArgumentException{
        try {
            scanner.nextLine();
            System.out.println(" Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println(" Введите описание задачи: ");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println(" Введите тип задачи: Рабочая - 0, Личная - 1 ");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: Однократная - 0, Ежедневная - 1, Еженедельная - 2, Ежемесячная - 3, Ежегдная - 4 ");
            int occurrence = scanner.nextInt();
            System.out.println("Введите дату в формате: dd.MM.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurrence);
            System.out.println("Для выхода нажмите Enter\n");
            scanner.nextLine();
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void createEvent (Scanner scanner, String title, String description, TaskType taskType, int occurrence){
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task;
                task = createTask(occurrence, title, description,taskType, eventDate);
                System.out.println("Создана задача " + task);
        }catch (DateTimeException | IncorrectArgumentException e){
            System.out.println("Проверьте формат dd. MM. yyyy HH:mm и попробуйте ещё");
            createEvent(scanner, title, description, taskType, occurrence);
        }
    }
    public static void editTask (Scanner scanner){
        try {
            System.out.println("Редактирование задачи: введите id ");
            printActualTask();
            int id = scanner.nextInt();
            if (!actualTasks.containsKey(id)) {
                throw new TaskNotFoundException();
            }
            System.out.println("Редактирование заголовок - 0, описание - 1, тип - 2, дата - 3 ");
            int menuCase = scanner.nextInt();
            switch (menuCase){
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Введите название задачи: ");
                    String title = scanner.nextLine();
                    Task task = actualTasks.get(id);
                    task.setTitle(title);
                }
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Введите описание задачи: ");
                    String discription = scanner.nextLine();
                    Task task = actualTasks.get(id);
                    task.setDescription(discription);
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.println("Введите тип задачи: ");
                    String tasktype = scanner.nextLine();
                    Task task = actualTasks.get(id);
                    task.setTasktype(TaskType.valueOf(tasktype));
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.println("Введите дату задачи: ");
                    String firstDate = scanner.nextLine();
                    Task task = actualTasks.get(id);
                    task.setFirstDate(LocalDateTime.parse(firstDate));
                }
            }
        }catch (TaskNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public static void deleteTask (Scanner scanner){
        int i = scanner.nextInt();
        if (!actualTasks.containsKey(i)){
            throw new TaskNotFoundException();
        }
        actualTasks.remove(i);
        System.out.println("Задача удалена");
    }
    public static void getTaskByDay(Scanner scanner){
        System.out.println("Введите дату в виде dd.MM.yyyy ");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Task> foudEvents = findTaskByDate(requestedDate);
            System.out.println("Событие на " + requestedDate + ":");
            for (Task task : foudEvents) {
                System.out.println(task);
            }
        }catch (DateTimeParseException e){
            System.out.println();
        }
        scanner.nextLine();
        System.out.println("Для выхода нажмите Enter\n");

    }
    private static List<Task> findTaskByDate(LocalDate date){
        List<Task> tasks = new ArrayList<>();
        for (Task task : actualTasks.values()) {
            if (task.checkOccurrence(date.atStartOfDay())){
                tasks.add(task);
            }
        }
        return tasks;
    }
    private static Task createTask(int occurrence, String title, String description, TaskType taskType, LocalDateTime localDateTime) throws IncorrectArgumentException{

        return switch (occurrence){
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, taskType, localDateTime, description);
                actualTasks.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, taskType, localDateTime, description);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, taskType, localDateTime, description);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, taskType, localDateTime, description);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YerlyTask task = new YerlyTask(title, taskType, localDateTime, description);
                actualTasks.put(task.getId(),task);
                yield task;
            }
            default -> null;
        };
    }
    private static void printActualTask(){
        for (Task task : actualTasks.values()){
            System.out.println(task);
        }
    }


}
