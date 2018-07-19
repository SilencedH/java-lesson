package net.youzule.javase.concurrency.chapter01.app2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/19 10:37
 **/

public class App03 {
    public static void main(String[] args) {
        Callable<Boolean> callable = new MyCallable();
        FutureTask<Boolean> booleanFutureTask = new FutureTask<>(callable);
        Thread thread = new Thread(booleanFutureTask);
        thread.start();

    }
}
