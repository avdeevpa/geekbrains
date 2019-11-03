package com.geekbrains.learning.lesson03.service;

public class TaskRepository implements TaskInterface {
    private final int TASK_LIMIT = 10;
    private Task[] tasks = new Task[TASK_LIMIT];

    @Override
    public long addTask(Task task) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] == null) {
                if (task.getId() == null) {
                    task.setId((long) i);
                }
                tasks[i] = task;
                return i;
            }
        }
        return -1;
    }

    @Override
    public Task getTask(Long id) {
        for (Task task : tasks) {
            if (task != null && task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Task[] getTasks() {
        return tasks;
    }

    @Override
    public void deleteTask(Long id) {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] != null) {
                if (tasks[i].getId().equals(id) ) {
                    tasks[i] = null;
                }
            }
        }
    }

    @Override
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
