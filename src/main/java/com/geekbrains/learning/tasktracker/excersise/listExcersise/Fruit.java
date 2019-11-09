package com.geekbrains.learning.tasktracker.excersise.listExcersise;

public class Fruit {
    float weight;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + weight;
    }
}
