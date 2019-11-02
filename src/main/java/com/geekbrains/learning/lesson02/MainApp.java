package com.geekbrains.learning.lesson02;

public class MainApp {
    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker();
        // Case 01
        for (int i = 1; i <= 10; i++) {
            Task task = new Task(i, "Задача " + i, "Автор", "Описание " + i + "-й задачи");
            task.assign("Исполнитель");
            task.setStatus("В работе");
            tracker.addTask(task);
        }
        // Case 02
        tracker.addTask(new Task(99, "Задача дополнительная", "Тестер", "Переполнение списка"));
        // Case 04
        tracker.deleteTask(3);
        tracker.deleteTask("Задача 10");
        // Case 03
        tracker.printTasks();
    }

}
