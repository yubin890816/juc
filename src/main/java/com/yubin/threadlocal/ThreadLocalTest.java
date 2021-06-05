package com.yubin.threadlocal;

/**
 * ThreadLocal测试类
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            System.out.println("initialValue方法执行了");
            return "initialValue";
        }
    };

    public static void main(String[] args) {
        System.out.println(threadLocal.get());
        threadLocal.set("set1");

        System.out.println(threadLocal.get());

        threadLocal.remove();

        System.out.println(threadLocal.get());

    }
}
