package com.geekbrains.learning.tasktracker.excersise.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    CyclicBarrier barrierReady;
    CountDownLatch latchStart;
    CountDownLatch latchEnd;
    Semaphore semaphoreTunnel;
    CountDownLatch latchWinner;
    StringBuffer nameOfWinner;
    Lock lockWin;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() { return name; }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrierReady.await();
            latchStart.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }


        lockWin.lock();
        if(nameOfWinner.length() == 0) {
            nameOfWinner.append(getName() + " - WIN");
            latchWinner.countDown();
        }
        lockWin.unlock();

        latchEnd.countDown();
    }
}
