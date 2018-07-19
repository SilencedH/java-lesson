package net.youzule.javase.concurrency.chapter01.app2;

import java.util.concurrent.Callable;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/19 10:45
 **/

public class MyCallable2 implements Callable<Boolean> {

    private Integer taskNum;

    public MyCallable2(Integer taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("my callable2-" + taskNum + "开始");
        //如果线程编号是偶数，睡眠2s，返回true
        if ((taskNum % 2) == 0){
            Thread.sleep(2000);
            long time = System.currentTimeMillis();
            System.out.println("my callable2-" + taskNum + "结束，当前时间戳:" + time);
            return true;
        }
        //如果线程编号是奇数数，睡眠1s，返回false
        Thread.sleep(1000);
        long time = System.currentTimeMillis();
        System.out.println("my callable2-" + taskNum + "结束，当前时间戳:" + time);
        return false;
    }
}
