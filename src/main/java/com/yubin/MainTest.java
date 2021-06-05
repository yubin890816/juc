package com.yubin;

/**
 * @author Administrator
 * @date 2021-05-31
 */
public class MainTest {

    public static void main(String[] args) {
        //new MyThread().start();
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE + 2);
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("this.isAlive() = " + this.isAlive());
        }
    }
}

