package com.geekbrains.learning.lesson02;

public class TaskTracker {
    private final int TASK_LIMIT = 10;
    private Task[] tasks = new Task[TASK_LIMIT];

    public void addTask(Task task) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] == task) {
                System.out.println("Task already exists");
                return;
            }
            if (tasks[i] == null) {
                tasks[i] = task;
                return;
            }
        }
        System.out.println("ERROR: Task limit " + TASK_LIMIT + " tasks reached.");
    }

    public void printTasks() {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] != null) {
                System.out.println(tasks[i].toString());
            }
        }
        System.out.println("=== Конец списка ===");
    }

    public void deleteTask(int id){
        for (int i = 0; i < TASK_LIMIT; i++) {
            if(tasks[i] != null) {
                if(tasks[i].getId() == id){
                    tasks[i] = null;
                }
            }
        }
    }

    public void deleteTask(String caption){
        for (int i = 0; i < TASK_LIMIT; i++) {
            if(tasks[i] != null) {
                if(tasks[i].getCaption() == caption){
                    tasks[i] = null;
                }
            }
        }
    }

}
