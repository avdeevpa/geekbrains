package com.geekbrains.learning.lesson02;

public class TaskTracker {
    private final int TASK_LIMIT = 10;
    private Task[] tasks = new Task[TASK_LIMIT];

    public void addTask(Task task) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                return;
            }
        }
        System.out.println("Список задач заполнен");
    }

    public void printTasks() {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] != null) {
                System.out.println(tasks[i].toString());
            }
        }
    }

    public void deleteTask(int id) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] != null) {
                if (tasks[i].getId() == id) {
                    tasks[i] = null;
                }
            }
        }
    }

    public void deleteTask(String caption) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] != null) {
                if (tasks[i].getCaption().equals(caption)) {
                    tasks[i] = null;
                }
            }
        }
    }

}
