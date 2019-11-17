package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskListRepository implements TaskInterface, Serializable {
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
    public List<Task> getTasks() {
        return taskList;
    }

    @Override
    public void deleteTask(Long id) throws TTStorageException {
        long deletes = 0L;
        for (Iterator<Task> iterator = taskList.iterator(); iterator.hasNext(); ) {
            Task task = iterator.next();
            if (task.getId().equals(id)) {
                iterator.remove(); // taskList.remove(task); -- получим ConcurrentModificationException
                deletes++;
            }
        }
        if (deletes == 0L) {
            throw new TTStorageException(String.format("Задача с id=%d не найдена", id));
        }
    }

    @Override
    public void deleteTask(String caption) throws TTStorageException {
        long deletes = 0L;
        Iterator<Task> iterator = taskList.iterator();
        while(iterator.hasNext()){
            Task task = iterator.next();
            if (task.getCaption().equals(caption)){
                iterator.remove(); // taskList.remove(task); -- непонятно что удалили
                deletes++;
            }
        }
        if (deletes == 0L) {
            throw new TTStorageException(String.format("Задача с Названием = \"%s\" не найдена", caption));
        }
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
