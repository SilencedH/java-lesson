package net.youzule.javase.concurrency.chapter03.app2;

import net.youzule.javase.concurrency.chapter03.common.ShareData;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/2 14:56
 **/

public class ThreadA implements Runnable {

    /*开启此线程，ShareData.count增加100*/

    @Override
    public void run() {
        for (int i = 0; i < 1000; i ++){
            ShareData.count1 ++;
        }
    }
}
