package com.mj.design.pattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class SingletonTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final AtomicInteger atomicInteger = new AtomicInteger(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Singleton singleton = null;
                    /**
                     * 线程不安全的1和2，很明显可以看到加了 synchronized 之后并发性能变差
                     */
//                  singleton = Singleton.getInstance_线程不安全1();
//                    singleton = Singleton.getInstance_线程不安全2();
//                    singleton = Singleton.getInstance_线程安全_双重检查();
//                    singleton = Singleton.getInstance_线程安全_性能差();

//                    System.out.println(singleton.getValue());
                    atomicInteger.decrementAndGet();
                }
            }).start();
        }
        while (atomicInteger.get() > 0) {

        }
        long time = System.currentTimeMillis() - start;
        System.out.println("执行时间:" + time);
    }
}
