package net.youzule.javase.concurrency.chapter03.app1;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/31 16:28
 **/

public class App {

    public static void main(String[] args) {
        System.out.println("main-开始:ShareData.count:" + ShareData.count);

        Thread threadA = new Thread(new ThreadA());
        threadA.start();
        System.out.println("main-thread:线程A开启");

        Thread threadB = new Thread(new ThreadB());
        threadB.start();
        System.out.println("main-thread:线程B开启");

        try {
            threadA.join();
            System.out.println("main-thread:线程A加入主线程");

            threadB.join();
            System.out.println("main-thread:线程B加入主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main-结束:最终ShareData.count:" + ShareData.count);
    }
}
