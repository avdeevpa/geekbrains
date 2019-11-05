package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

public class TaskRepository implements TaskInterface {
    private final int TASK_LIMIT = 10;
    private Task[] tasks = new Task[TASK_LIMIT];

    @Override
    public Task addTask(Task task) throws TTStorageException {
        for (int i = 0; i < TASK_LIMIT; i++) {
            if (tasks[i] == null) {
                task.setId((long) i); // Package-private setter
                tasks[i] = task;
                return task;
            }
        }
        throw new TTStorageException("Хранилище задач заполнено");
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
                if (tasks[i].getId().equals(id)) {
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
