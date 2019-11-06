package com.geekbrains.learning.tasktracker.excersise.listExcersise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> boxList;

    public Box(T... boxList) {
        this.boxList = new ArrayList<>(Arrays.asList(boxList));
    }

    public List<T> getBoxList() {
        return boxList;
    }

    public float getTotalWeight() {
        float totalWeight = 0f;
        for (T contents : boxList) {
            totalWeight += contents.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<? extends Fruit> another) {
        return Math.abs(this.getTotalWeight() - another.getTotalWeight()) < 0.01f;
    }

    public void addToBox(T fruit) {
        boxList.add(fruit);
    }

    public void repackTo(Box<T> newBox) {
        newBox.getBoxList().addAll(boxList);
        boxList.clear();
    }
}
