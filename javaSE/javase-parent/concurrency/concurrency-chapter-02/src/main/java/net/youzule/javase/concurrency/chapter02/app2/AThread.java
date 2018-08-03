package net.youzule.javase.concurrency.chapter02.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/30 16:43
 **/

public class AThread implements Runnable{

    private Thread thread;

    public AThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("thread-a:开始");

        try {
            System.out.println("thread-a:我要休息3s,等待子线程加入");
            thread.start();
            thread.join(2000);
            System.out.println("thread-a:我等了thread2s后不等他了");
            /*System.out.println("thread-a:thread已经Join,等他执行完毕");
            thread.join();*/

        } catch (InterruptedException e) {
            System.out.println("thread-a我被中断了");
        }

        System.out.println("thread-a:结束");

    }
}
