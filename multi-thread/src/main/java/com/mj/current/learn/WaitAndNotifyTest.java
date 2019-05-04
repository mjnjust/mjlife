package com.mj.current.learn;

import java.util.ArrayList;
import java.util.List;

public class WaitAndNotifyTest {
    private static volatile List<Integer> container = new ArrayList<Integer>();

    public static void main(String[] args) {
        Object lock = new Object();
        Producer producer = new Producer(lock, container);
        Consumer consumer1 = new Consumer("consumer1", lock, container);
        Consumer consumer2 = new Consumer("consumer2", lock, container);
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private Object lock;

    private List<Integer> list;

    public Producer(Object lock, List<Integer> list) {
        this.lock = lock;
        this.list = list;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (this.lock) {
                System.out.println("producer:加入一个数据," + i);
                this.list.add(i);
                this.lock.notify();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Object lock;

    private List<Integer> list;

    private String name;

    public Consumer(String name, Object lock, List<Integer> list) {
        this.lock = lock;
        this.list = list;
        this.name = name;
    }

    public void run() {
        while (true) {
            synchronized (this.lock) {
                if (this.list.size() > 0) {
                    //有数据不停的打印
                    int data = this.list.remove(0);
                        System.out.println(this.name + ":消费一个数据," + data);
                        System.out.println(data);
                    } else {
//                没数据 挂起消费者
                        try {
                            this.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
                }
            }
        }
    }
}
