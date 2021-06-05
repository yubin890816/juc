package com.yubin.sync;

/**
 * 同步代码块测试
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class SynchronizedTest {

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyThread()).start();
        Thread.sleep(100);
        synchronized (obj) {
            obj.notify();

            Thread.sleep(10000);
            System.out.println("main线程中的同步代码块指向完成");
        }
        System.out.println("main线程执行完成");
    }


    private static class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("==============MyThread执行了");
                try {
                    Thread.sleep(50);
                    synchronized (obj) {
                        obj.wait();
                    }

                    System.out.println("=====================MyThread被唤醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
