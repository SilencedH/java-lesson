package net.youzule.javase.concurrency.chapter01.app2;

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

public class App01 {
    public static void main(String[] args) {
        //1、继承Thread
        Thread thread = new MyThread();
        thread.start();
    }
}
