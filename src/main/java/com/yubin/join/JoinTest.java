package com.yubin.join;

/**
 * join方法测试
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class JoinTest {

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new MyThread("A", null, 3000);
        Thread threadB = new MyThread("B", threadA, 2000);
        Thread threadC = new MyThread("C", threadB, 1000);
        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static class MyThread extends Thread {

        private Thread thread;

        private long sleepTime;

        public MyThread(String name, Thread thread, long sleepTime) {
            this.setName(name);
            this.thread = thread;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
                //synchronized (obj) {
                    if (thread != null) {
                        thread.join(); // join方法不会自动释放锁
                    }
                    //System.out.println(Thread.currentThread().getName() + "开始执行");
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("打印:" + Thread.currentThread().getName());
        }
    }

}
