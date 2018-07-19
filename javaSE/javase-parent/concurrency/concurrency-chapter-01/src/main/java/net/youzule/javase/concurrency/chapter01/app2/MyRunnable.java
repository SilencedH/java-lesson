package net.youzule.javase.concurrency.chapter01.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/18 21:02
 **/

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("my runnable");
    }
}
