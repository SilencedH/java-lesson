package net.youzule.javase.chapter01.app02;

import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/18 20:56
 **/

public class App {
    public static void main(String[] args) {
        //1、继承Thread
        Thread thread = new MyThread();
        thread.start();

        //2、实现Runnable接口
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
        //3、实现collable接口
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<int> callable = new MyCallable();
    }
}
