package com.yubin.volati;

/**
 * volatile关键字测试
 *
 * @author Administrator
 * @date 2021-05-31
 */
public class VolatileTest {

    private volatile static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                System.out.println("读取线程执行开始");
                while (!initFlag) {

                }
                System.out.println("读取线程执行结束");
            }
        }.start();

        Thread.sleep(200);

        new Thread() {
            @Override
            public void run() {
                System.out.println("修改线程执行开始");
                initFlag = true;
                System.out.println("修改线程执行结束");
            }
        }.start();
    }

}
