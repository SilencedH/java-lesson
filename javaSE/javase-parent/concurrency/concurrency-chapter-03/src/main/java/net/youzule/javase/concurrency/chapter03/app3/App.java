package net.youzule.javase.concurrency.chapter03.app3;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/2 16:11
 **/

public class App {

    public static void main(String[] args) {
        System.out.println("main-thread开始:share-data-flag=" + ShareData.flag);

        Thread thread = new Thread(new VolatileThread());
        thread.start();
        System.out.println("main-thread:volatile-thread已经启动");

        try {
            Thread.sleep(5000);
            ShareData.flag = true;
            System.out.println("main-thread:5秒之后，我修改了flag:" + ShareData.flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main-thread结束:share-data-flag=" + ShareData.flag);
    }
}
