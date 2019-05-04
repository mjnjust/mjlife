package com.mj.current.learn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    private static int num = 1;

    private static synchronized int getNum() {
        return num++;
    }

    /**
     * corePoolSize 线程池核心线程数
     * maximumPoolSize 线程池最大线程数 ，当提交的线程数 > corePoolSize+workQueue.size时，会创建非核心线程
     * keepAliveTime 超过核心线程池数量数，非核心线程的最大空闲时间
     * unit keepAliveTime的时间单位
     * workQueue 用来存储的未被执行的队列
     *
     * @param args
     */

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("thread" + getNum());
                return thread;
            }
        });
        for (int i = 0; i < 8; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
