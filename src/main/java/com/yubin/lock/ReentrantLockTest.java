package com.yubin.lock;

import com.yubin.safeinterrupt.MyThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入锁测试类
 *
 * @author Administrator
 * @date 2021-06-01
 */
public class ReentrantLockTest {

    private static int count = 10000;

    private static Lock lock = new ReentrantLock();

    private static class MyRunnable implements Runnable {

        private CountDownLatch downLatch;

        public MyRunnable(CountDownLatch downLatch) {
            this.downLatch = downLatch;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    count--;
                } finally {
                    lock.unlock();
                }
            }
            downLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable(downLatch)).start();
        }
        downLatch.await();
        System.out.println(count);
    }
}
