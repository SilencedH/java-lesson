package net.youzule.javase.concurrency.chapter03.app3;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/2 16:12
 **/

public class VolatileThread implements Runnable {
    @Override
    public void run() {
        System.out.println("volatile-thread:开始");
        while (!ShareData.flag) {
        }
        System.out.println("volatile-thread:结束");
    }
}
