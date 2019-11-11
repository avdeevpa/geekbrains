package com.geekbrains.learning.tasktracker.excersise.listExcersise;

public class ListExcersise {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>(new Apple(), new Apple(), new Apple());
        System.out.println(appleBox.getBoxList());
        System.out.println("Total weight of Apple Box: " + appleBox.getTotalWeight());

        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange());
        System.out.println(orangeBox.getBoxList());
        System.out.println("Total weight of Apple Box: " + orangeBox.getTotalWeight());

        System.out.println(appleBox.compare(orangeBox));
        System.out.println();

        Box<Apple> newAppleBox = new Box<>(new Apple());
        System.out.println("AppleBox: " + appleBox.getBoxList() + "   New AppleBox: " + newAppleBox.getBoxList());
        appleBox.repackTo(newAppleBox);
        System.out.println("AppleBox: " + appleBox.getBoxList() + "   New AppleBox: " + newAppleBox.getBoxList());
        System.out.println();

        orangeBox.addToBox(new Orange());
        System.out.println(orangeBox.getBoxList());
    }
}
