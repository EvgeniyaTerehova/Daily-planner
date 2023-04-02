package ru.skypro;

import ru.skypro.calendar.MyCalendar;
import ru.skypro.calendar.exception.IncorrectArgumentException;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
          label:
          while (true){
              System.out.println("Выберите пункт меню:");
              printMenu();
              if (scanner.hasNextInt()) {
                  int menu = scanner.nextInt();
                  switch (menu){
                      case 1:
                          MyCalendar.addTask(scanner);
                          break;
                      case 2:
                          MyCalendar.editTask(scanner);
                          break;
                      case 3:
                          MyCalendar.deleteTask(scanner);
                          break;
                      case 4:
                          MyCalendar.getTaskByDay(scanner);
                          break;
                      case 0:
                          break label;
                  }
              } else {
                  scanner.next();
                  System.out.println(" Выберите пункт меню из списка ");
              }
          }
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    private static void printMenu() {
        System.out.println("""
                1. Добавить задачу
                2. Редактировать задачу
                3. Удалить задачу
                4. Получить задачи на указанный день
                0. Выход """);
    }
}