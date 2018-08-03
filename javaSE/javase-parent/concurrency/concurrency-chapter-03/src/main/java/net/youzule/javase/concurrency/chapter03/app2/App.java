package net.youzule.javase.concurrency.chapter03.app2;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/1 16:53
 **/

public class App {

    public static void main(String[] args) {

        System.out.println(ShareData.count1);
        /*开启10个ThreadA线程共同计算*/
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedThread());
            thread.start();
        }

        try {
            /*休息5s是为了等待10个线程都结束，其实休息时间为一个线程的运行时间即可*/
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ShareData.count1);
    }
}
