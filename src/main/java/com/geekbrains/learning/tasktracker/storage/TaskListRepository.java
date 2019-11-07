package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import java.util.ArrayList;

public class TaskListRepository implements TaskInterface {
    private ArrayList<Task> taskList;

    public TaskListRepository() {
        taskList = new ArrayList<>();
    }

    @Override
    public Task addEdtTask(Task task) {
        if (task.getId() == null) {
            task.setId(getNextId());
            taskList.add(task);
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                if(taskList.get(i).getId().equals(task.getId())) {
                    taskList.set(i, task);
                }
            }
        }
        return task;
    }

    @Override
    public Task getTask(Long id) throws TTStorageException {
        for (Task task : taskList) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        throw new TTStorageException(String.format("Задача с id=%d не найдена", id));
    }

    @Override
    public Task[] getTasks() {
        return taskList.toArray(Task[]::new);
    }

    @Override
    public void deleteTask(Long id) throws TTStorageException {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId().equals(id)) {
                taskList.remove(i);
                return;
            }
        }
        throw new TTStorageException(String.format("Задача с id=%d не найдена", id));
    }

    @Override
    public void deleteTask(String caption) throws TTStorageException {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getCaption().equals(caption)) {
                taskList.remove(i);
                return;
            }
        }
        throw new TTStorageException(String.format("Задача с Названием = \"%s\" не найдена", caption));
    }

    private Long getNextId() {
        Long maxId = 0L;
        for (Task tsk: taskList) {
            if(tsk.getId() > maxId) {
                maxId = tsk.getId();
            }
        }
        return maxId + 1;
    }
}
