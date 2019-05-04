package com.mj.design.pattern.singleton;

import java.util.Random;

public class Singleton {
    private static Singleton singleton;

    private int value = 0;

    private Singleton() {
        System.out.println("创建单例");
        this.value = new Random().nextInt();
        for (int i = 0; i < 500000; i++) {
            double s = Math.sin(1000) / Math.acos(1000);
        }
    }

    public int getValue() {
        return this.value;
    }


    public static Singleton getInstance_线程不安全1() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public static Singleton getInstance_线程不安全2() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }

    public static Singleton getInstance_线程安全_双重检查() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static synchronized Singleton getInstance_线程安全_性能差() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public static synchronized Singleton getInstance_线程安全_性能差() {
        return Singleton.SingletonC.
    }

    private static class SingletonC {
        public Singleton singleton = new Singleton();
    }
}