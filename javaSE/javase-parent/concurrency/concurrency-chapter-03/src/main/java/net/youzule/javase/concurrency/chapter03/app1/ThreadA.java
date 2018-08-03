package net.youzule.javase.concurrency.chapter03.app1;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/31 16:28
 **/

public class ThreadA implements Runnable {

    /*A对ShareData.count -1 */

    private int count;

    @Override
    public void run() {
        this.count = ShareData.count;
        System.out.println("thread-a:开始执行，读取count:" + this.count);

        System.out.println("thread-a:ShareData.count-1");
        this.count --;
        System.out.println("thread-a:cout减1了，写进内存");

        /*sleep3s，模拟没来得及写入内存中*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ShareData.count = this.count;

        System.out.println("thread-a:执行结束，count:" + this.count);

    }

}
