package com.mjlife.service.jdk8;

import java.util.ArrayList;
import java.util.List;

public class JDK8NewFeatures {
    public static void main(String[] args) {
//        lambdaTest函数接口();
//        lambdaTest集合遍历();
//        lambdaTest集合筛选();
//        lambdaTest并行stram基本类型();
        lambdaTest并行迭代stram对象类型();
//        lambdaTest并行迭代stram复杂操作();
    }


    /**
     * lambda表达式试用于函数接口
     * 只包含一个方法的接口
     */
    public static void lambdaTest函数接口() {
        System.out.println("函数接口:---------------------------");
        new Thread(() -> {
            System.out.println("new Thread run ......");
        }).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void lambdaTest集合遍历() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println("集合遍历---------------------------");
        list.forEach(data -> {
            System.out.println(data);
        });
    }

    public static void lambdaTest集合筛选() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println("集合筛选---------------------------");
        System.out.println(list.stream().filter(data -> true).count());
        System.out.println(list.stream().filter(data -> {
            return data.equals("2");
        }).count());
    }

    public static void lambdaTest并行stram基本类型() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add("" + i);
        }
        int trunNum = 50;
        long start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            for (String s : list) {
                if (s.equals("9999")) {

                }
            }
        }
        System.out.println("for循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.stream().filter(data -> {
                return data.equals("9999");
            });
        }
        System.out.println("stream循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            int finalI = i;
            list.parallelStream().filter(data -> {
                return data.equals("9999");
            });
        }
        System.out.println("stream并行循环迭代耗时:" + (System.currentTimeMillis() - start));
    }

    public static void lambdaTest并行stram对象类型() {
        List<JDK8TestPO> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            JDK8TestPO po = new JDK8TestPO();
            po.setId(i);
            po.setName(String.valueOf(i));
            list.add(po);
        }
        int trunNum = 50;
        long start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            for (JDK8TestPO s : list) {
                if (s.getName().equals("9999")) {

                }
            }
        }
        System.out.println("for循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.stream().filter(data -> {
                return data.getName().equals("9999");
            });
        }
        System.out.println("stream循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            int finalI = i;
            list.parallelStream().filter(data -> {
                return data.getName().equals("9999");
            });
        }
        System.out.println("stream并行循环迭代耗时:" + (System.currentTimeMillis() - start));
    }

    public static void lambdaTest并行迭代stram对象类型() {
        List<JDK8TestPO> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            JDK8TestPO po = new JDK8TestPO();
            po.setId(i);
            po.setName(String.valueOf(i));
            list.add(po);
        }
        int trunNum = 50;
        long start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            for (JDK8TestPO s : list) {
                if (s.getName().equals("9999") || s.getId() == 9999) {

                }
            }
        }
        System.out.println("for循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.forEach(data -> {
                data.getName().equals("9999");
                if (data.getId() == 9999) {
                }
            });
        }
        System.out.println("stream循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.forEach(data -> {
                data.getName().equals("9999");
                if (data.getId() == 9999) {

                }
            });
        }
        System.out.println("stream并行循环迭代耗时:" + (System.currentTimeMillis() - start));
    }

    public static void lambdaTest并行迭代stram复杂操作() {
        List<JDK8TestPO> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            JDK8TestPO po = new JDK8TestPO();
            po.setId(i);
            po.setName(String.valueOf(i));
            list.add(po);
        }
        int trunNum = 50;
        long start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            for (JDK8TestPO s : list) {
                Math.sin(s.getId());
            }
        }
        System.out.println("for循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.forEach(data -> {
                Math.sin(data.getId());
            });
        }
        System.out.println("stream循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.forEach(data -> {
                Math.sin(data.getId());
            });
        }
        System.out.println("stream并行循环迭代耗时:" + (System.currentTimeMillis() - start));
    }

    public static void lambdaTest并行迭代stram复杂计算() {
        List<JDK8TestPO> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            JDK8TestPO po = new JDK8TestPO();
            po.setId(i);
            po.setName(String.valueOf(i));
            list.add(po);
        }
        int trunNum = 50;
        long start = System.currentTimeMillis();
        long x = 0;
        for (int i = 0; i < trunNum; i++) {
            for (JDK8TestPO s : list) {
                x += Math.sin(s.getId());
            }
        }
        System.out.println("for循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.stream().mapToInt(JDK8TestPO::getId).sum();
        }
        System.out.println("stream循环迭代耗时:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < trunNum; i++) {
            list.parallelStream().mapToInt(JDK8TestPO::getId).sum();
        }
        System.out.println("stream并行循环迭代耗时:" + (System.currentTimeMillis() - start));
    }
}