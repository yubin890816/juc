package com.yubin.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock和Condition配合实现等待通知机制
 *
 * @author Administrator
 * @date 2021-06-01
 */
public class BlockingQueueLC {

    private static final int limit = 10;

    private static List<Integer> queue = new ArrayList<>(limit);

    private static Lock lock = new ReentrantLock();

    private static Condition emptyCondition = lock.newCondition();

    private static Condition fullCondition = lock.newCondition();

    private static CountDownLatch count = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new DequeueThread()).start();
        new Thread(new EnqueueThread()).start();
        Thread.sleep(10);
        count.countDown();
    }

    /**
     * 出队线程
     */
    private static class DequeueThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                dequeue();
            }
        }
    }

    /**
     * 出队操作
     */
    private static Integer dequeue() {
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("队列为空, " + Thread.currentThread().getName() + " 休眠了");
                emptyCondition.await();
                System.out.println(Thread.currentThread().getName() + " 被唤醒了");
            }
            if (queue.size() == limit) {
                System.out.println("入队即将被唤醒");
                fullCondition.signal();
            }
            Integer item = queue.remove(0);
            System.out.println("元素" + item + "出队");
            return item;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
        return null;
    }

    /**
     * 入队线程
     */
    private static class EnqueueThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                enqueue(i);
            }
        }
    }

    /**
     * 入队操作
     */
    private static void enqueue(int item) {
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("即将入队的元素是:" + item);
        lock.lock();
        try {
            while (queue.size() == limit) {
                System.out.println("队列满了, " + Thread.currentThread().getName() + " 休眠了");
                fullCondition.await();
                System.out.println(Thread.currentThread().getName() + " 被唤醒了");
            }
            if (queue.size() == 0) {
                System.out.println("出队即将被唤醒");
                emptyCondition.signal();
            }
            queue.add(item);
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

}
