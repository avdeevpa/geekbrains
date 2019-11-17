package com.geekbrains.learning.tasktracker.storage;

import java.io.*;

public class FileSimpleService {
    public static void trackerSave(String fileName, TaskInterface obj) {
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName)) ) {
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TaskListRepository trackerRestore(String fileName) {
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName)) ) {
            return (TaskListRepository) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
