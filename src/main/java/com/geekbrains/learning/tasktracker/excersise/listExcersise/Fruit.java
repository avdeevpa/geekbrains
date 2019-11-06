package com.geekbrains.learning.tasktracker.excersise.listExcersise;

public class Fruit {
    private float weight;

    public float getWeight() {
        return weight;
    }

    void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getWeight();
    }
}
