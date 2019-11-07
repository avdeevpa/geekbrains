package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import java.util.ArrayList;

public class TaskListRepository implements TaskInterface{
    private ArrayList<Task> taskList;

    public TaskListRepository() {
        taskList = new ArrayList<>();
    }

    @Override
    public Task addTask(Task task) {
        if(task.getId() == null) {
            task.setId((long) taskList.size());
        }
        taskList.add(task);
        return task;
    }

    @Override
    public Task getTask(Long id) {
        for(Task task : taskList) {
            if(task.getId().equals(id)){
                return task;
            }
        }
        throw new RuntimeException("Not found");
    }

    @Override
    public Task[] getTasks() {
        return taskList.toArray(Task[]::new);
    }

    @Override
    public void deleteTask(Long id) {
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getId().equals(id)) {
                taskList.remove(i);
            }
        }
    }

    @Override
    public void deleteTask(String caption) {
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getCaption().equals(caption)) {
                taskList.remove(i);
            }
        }
    }
}
