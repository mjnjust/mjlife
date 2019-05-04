package com.mj.current.learn;

/**
 * synchronized 关键字和  volatile 关键字对比
 */
public class SyncAndVolaTest {
    public static void main(String[] a) {
//        long start = System.currentTimeMillis() ;
//        testNormal();
//        System.out.println("执行时间:"+(System.currentTimeMillis()-start));
//        start = System.currentTimeMillis();
//        testVolatile();
//        System.out.println("执行时间:"+(System.currentTimeMillis()-start));
//        start = System.currentTimeMillis();
//        testSync();
//        System.out.println("执行时间:"+(System.currentTimeMillis()-start));
    }

    public static void testSync(){
        {
            Thread[] t = new Thread[20];
            for (int i = 0; i < 20; i++) {
                t[i] = new Thread(new Runnable() {
                    public void run() {
                        for (int s = 0; s < 1000000; s++) {
                            TestNumberWithSync.incr();
                        }
                    }
                });
            }
            for (int i = 0; i < t.length; i++) {
                t[i].start();
            }
            while (Thread.activeCount()>1){
                Thread.yield();
            }
            System.out.println(TestNumberWithSync.getNum());
        }
    }

    public static void testVolatile(){
        {
            Thread[] t = new Thread[20];
            for (int i = 0; i < 20; i++) {
                t[i] = new Thread(new Runnable() {
                    public void run() {
                        for (int s = 0; s < 1000000; s++) {
                            TestNumberWithVolatile.incr();
                        }
                    }
                });
            }
            for (int i = 0; i < t.length; i++) {
                t[i].start();
            }
            while (Thread.activeCount()>1){
                Thread.yield();
            }
            System.out.println(TestNumberWithVolatile.getNum());
        }
    }

    public static void testNormal(){
        Thread[] t = new Thread[20];
        for (int i = 0; i < 20; i++) {
            t[i] = new Thread(new Runnable() {
                public void run() {
                    for (int s = 0; s < 1000000; s++) {
                        TestNumber.incr();
                    }
                }
            });
        }
        for (int i = 0; i < t.length; i++) {
            t[i].start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(TestNumber.getNum());
    }
}

class TestNumberWithVolatile {
    private static volatile int num = 0;

    public static void incr() {
        num++;
    }

    public static int getNum() {
        return num;
    }
}

class TestNumberWithSync {
    private static int num = 0;

    public synchronized static void incr() {
        num++;
    }

    public synchronized static int getNum() {
        return num;
    }
}

class TestNumber {
    private static int num = 0;

    public static void incr() {
        num++;
    }

    public static int getNum() {
        return num;
    }
}