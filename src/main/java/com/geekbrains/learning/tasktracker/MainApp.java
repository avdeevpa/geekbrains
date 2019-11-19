package com.geekbrains.learning.tasktracker;

import com.geekbrains.learning.tasktracker.storage.*;

public class MainApp {
    static TaskService tracker = new TaskService(new TaskListRepository());;

    public static void main(String[] args) {
        prepareTaskTracker();
        // Object-oriented style:
        tracker.saveRepository("Obj.dat");
        tracker.restoreRepository("Obj.dat");
        // Monster service:
        FileService.exportTasks(tracker.getTasks());
        FileService.importTasks(tracker, true);
        tracker.printTasks();

//        System.out.println("\n");
//        System.out.println(TaskService.listBeautifier(
//                "a. Получение списка задач по выбранному статусу \"" + Task.Status.ASSIGNED.getRussianTitle() + "\"",
//                tracker.getTaskByStatus(Task.Status.ASSIGNED)));
//        System.out.println(
//                "----- b. Проверка наличия задачи с указанным ID -----\n" +
//                tracker.isTaskExists(5L) + "\n");
//        System.out.println(TaskService.listBeautifier(
//                "c. Получение списка задач в отсортированном по статусу виде",
//                tracker.getSortedTaskList()));
//        System.out.println(
//                "----- d. Подсчет количества задач по определенному статусу -----\n" +
//                String.format("Задач в статусе \"%s\" : %d",
//                        Task.Status.ASSIGNED.getRussianTitle(),
//                        tracker.countOfStatus(Task.Status.ASSIGNED)) + "\n" );
    }

    static void prepareTaskTracker(){
        Task task;
        //TaskService tracker = new TaskService(new TaskRepository());
        task = new Task("Задача 1", "Алиса", "Описание");
        tracker.addEdtTasks(task);

        task = new Task("Задача 2", "Боб", "Описание");
        task.setStatus(Task.Status.ASSIGNED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 3", "Чарли", "Описание");
        task.setStatus(Task.Status.ASSIGNED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 4", "Дейв", "Описание");
        task.setStatus(Task.Status.INPROGRESS);
        tracker.addEdtTasks(task);

        task = new Task("Задача 5", "Кэрол", "Описание");
        task.setStatus(Task.Status.COMPLETED);
        tracker.addEdtTasks(task);

        task = new Task("Задача 6", "Чак", "Описание");
        task.setStatus(Task.Status.REJECTED);
        tracker.addEdtTasks(task);

    }
}
