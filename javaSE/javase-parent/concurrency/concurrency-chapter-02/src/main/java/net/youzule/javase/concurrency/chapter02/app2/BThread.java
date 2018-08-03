package net.youzule.javase.concurrency.chapter02.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/30 16:43
 **/

public class BThread implements Runnable {
    @Override
    public void run() {
        System.out.println("thread-b:开始,count:");
        try {
            System.out.println("thread-b:我要休息3s");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("thread-b:我被中断了");
        }
        System.out.println("thread-b:结束");

    }
}
