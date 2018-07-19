package net.youzule.javase.concurrency.chapter01.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/19 10:22
 **/

public class App02 {
    public static void main(String[] args) {
        //实现Runnable接口
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
