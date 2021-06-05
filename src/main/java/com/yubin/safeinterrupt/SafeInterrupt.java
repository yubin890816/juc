package com.yubin.safeinterrupt;

/**
 * 安全中断线程
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class SafeInterrupt {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        Thread.sleep(1000);
        myThread.interrupt(thread);

    }
}
