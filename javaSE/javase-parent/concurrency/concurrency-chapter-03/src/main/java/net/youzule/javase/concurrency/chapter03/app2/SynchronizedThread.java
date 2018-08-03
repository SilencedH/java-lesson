package net.youzule.javase.concurrency.chapter03.app2;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/1 16:53
 **/

public class SynchronizedThread implements Runnable {
    @Override
    public void run() {
        synchronized (SynchronizedThread.class){
            for (int i = 0; i < 1000; i++) {
                ShareData.count1++;
            }
        }

    }
}
