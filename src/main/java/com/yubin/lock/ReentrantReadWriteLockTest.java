package com.yubin.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁测试类
 *
 * @author Administrator
 * @date 2021-06-01
 */
public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(new ReadThread()).start();
    }

    private static class ReadThread implements Runnable {

        private int i = 0;
        @Override
        public void run() {
            test();
        }

        private void test() {
            while (i < 65590) {
                System.out.println(i);
                readLock.lock();
                i++;
                try {

                }finally {
                    readLock.unlock();
                }
            }
        }
    }


    private static class WriteThread implements Runnable {
        @Override
        public void run() {
            writeLock.lock();

            try {

            }finally {
                writeLock.unlock();
            }
        }
    }
}

