package net.youzule.javase.concurrency.chapter03.app1;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/31 16:36
 **/

public class ThreadB implements Runnable {
    /*ThreadB，也进行减1操作*/

    private int count;
    @Override
    public void run() {
        this.count = ShareData.count;
        System.out.println("thread-b:开始，count=" + this.count);
        this.count --;
        ShareData.count = this.count;
        System.out.println("thread-b:结束，count=" + this.count);

    }
}
