package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.TaskService;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileService{
    final static String FILE_NAME = "Tasks.json";

    public static boolean exportTasks(List<Task> taskList) {
        List<String> content = new ArrayList<>();
        content.add("{");
        for (Task task: taskList) {
            content.add(taskCastJson(task));
            content.add(",");
        }
        content.remove(content.size() - 1);
        content.add("}");

        fileWrite(FILE_NAME, content);
        return true;
    }

    public static void importTasks(TaskService repo, boolean overrideId) {
        String text = fileRead(FILE_NAME);
        List<Task> taskList = new ArrayList<>();
        List<Map<String, String>> collect = jsonCastTasks(text);
        for (Map<String, String> rawTask: collect) {
            Task task = forceTaskCreate(rawTask.get("caption"), rawTask.get("owner"), rawTask.get("description"), rawTask.get("status"), rawTask.get("assigned"));
            if (overrideId) {
                task = overrideTaskId(task, Long.parseLong(rawTask.get("id")));
            }
            taskList.add(task);
        }
        Field privateField;
        try {
            privateField = repo.getClass().getDeclaredField("storage");
            privateField.setAccessible(true);
            TaskInterface storage = (TaskInterface)privateField.get(repo);

            Field subPrivateField;
            try {
                subPrivateField = storage.getClass().getDeclaredField("taskList");
                subPrivateField.setAccessible(true);
                subPrivateField.set(storage, taskList);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static String fileRead(String fileName) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            content = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    static void fileWrite(String fileName, List<String> content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            content.stream().forEach(str -> {
                try {
                    writer.write(str + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String taskCastJson(Task task){
        String owner = "";
        String assigned = "";
        String description = "";

        Field privateField;
        try {
            privateField = task.getClass().getDeclaredField("owner");
            privateField.setAccessible(true);
            owner = (String) privateField.get(task);

            privateField = task.getClass().getDeclaredField("assigned");
            privateField.setAccessible(true);
            assigned = (String) privateField.get(task);

            privateField = task.getClass().getDeclaredField("description");
            privateField.setAccessible(true);
            description = (String) privateField.get(task);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return String.format(
                "\t\"TASK_%d\":{\n" +
                    "\t\t\"id\":\"%d\",\n" +
                    "\t\t\"caption\":\"%s\",\n" +
                    "\t\t\"status\":\"%s\",\n" +
                    "\t\t\"owner\":\"%s\",\n" +
                    "\t\t\"assigned\":\"%s\",\n" +
                    "\t\t\"description\":\"%s\"\n" +
                "\t}",
                task.getId(), task.getId(), task.getCaption(), task.getStatus(), owner, assigned, description
        );
    }

    static List<Map<String, String>> jsonCastTasks (String text) {
        List<Map<String, String>> collect = new ArrayList<>();

        Pattern jsonObjectPattern = Pattern.compile("\\{(.|\\n)*\\}");
        Matcher matcherObj = jsonObjectPattern.matcher(text);
        if (matcherObj.find()) {
            Pattern taskPattern = Pattern.compile("\\{([^{])*}");
            Matcher matchTask = taskPattern.matcher(
                    text.substring(matcherObj.start() + 1, matcherObj.end() - 1)
            );
            while (matchTask.find()) {
                Pattern keyPattern = Pattern.compile("( *)\"(.*?)\"( *):( *)\"(.*?)\"");
                Matcher keyMatcher = keyPattern.matcher(matchTask.group(0));
                Map<String, String> vals = new HashMap<>();
                while (keyMatcher.find()) {
                    vals.put(keyMatcher.group(2), keyMatcher.group(5));
                }
                collect.add(vals);
            }
        }
        return collect;
    }

    static Task forceTaskCreate (String caption, String owner, String description, String status, String assigned) {
        Task task = new Task(caption, owner, description);
        Field privateField;
        try {
            privateField = task.getClass().getDeclaredField("assigned");
            privateField.setAccessible(true);
            privateField.set(task, assigned);

            privateField = task.getClass().getDeclaredField("status");
            privateField.setAccessible(true);
            privateField.set(task, Task.Status.valueOf(status));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return task;
    }

    static Task overrideTaskId (Task task, Long id) {
        Field privateField;
        try {
            privateField = task.getClass().getDeclaredField("id");
            privateField.setAccessible(true);
            privateField.set(task, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return task;
    }

}