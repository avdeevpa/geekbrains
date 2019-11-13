package com.geekbrains.learning.tasktracker.excersise.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cbReady = new CyclicBarrier(CARS_COUNT);
        CountDownLatch cdRaceStart = new CountDownLatch(CARS_COUNT);
        CountDownLatch cdRaceEnd = new CountDownLatch(CARS_COUNT);
        Semaphore smpTunnel = new Semaphore(Math.round((float) CARS_COUNT / 2));
        CountDownLatch cdRaceWin = new CountDownLatch(1);
        StringBuffer Winner = new StringBuffer("");
        Lock lockWinner = new ReentrantLock();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            cars[i].barrierReady = cbReady;
            cars[i].latchStart = cdRaceStart;
            cars[i].latchEnd = cdRaceEnd;
            cars[i].semaphoreTunnel = smpTunnel;
            cars[i].latchWinner = cdRaceWin;
            cars[i].nameOfWinner = Winner;
            cars[i].lockWin = lockWinner;
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        cdRaceStart.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdRaceWin.await();
        System.out.println(Winner);
        cdRaceEnd.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
