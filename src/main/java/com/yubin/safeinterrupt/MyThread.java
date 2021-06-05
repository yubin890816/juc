package com.yubin.safeinterrupt;

/**
 * 自定义线程类
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class MyThread implements Runnable {

    private volatile boolean on = true;

    public void run() {
        while (on && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                System.out.println(Thread.interrupted());
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                System.out.println(Thread.interrupted());
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                System.out.println(Thread.interrupted());
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("catch:" + Thread.currentThread().isInterrupted());
            }
        }
        System.out.println(Thread.currentThread().isInterrupted());
    }

    public void interrupt(Thread currentThread) {
        this.on = false;
        currentThread.interrupt();
    }
}
