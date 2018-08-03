package net.youzule.javase.concurrency.chapter02.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/19 17:16
 **/

public class App {
    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            System.out.println("thread-1 开始执行");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("我是thread-1,正在循环:" + i);
                } catch (InterruptedException e) {
                    System.out.println("我是thread-1,我中断了，准备休息3s");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        System.out.println("我是thread-1,休息3秒时也被中断了");
                    }
                }
            }

            System.out.println("thread-1 执行结束");

        });
        System.out.println("我是thread-0,我要启动thread-1");

        thread1.start();
        System.out.println("我是thread-0,我要在4s后中断thread-1");
        try {
            System.out.println("中断thread-1之前,isInterruted:" + thread1.isInterrupted());
            Thread.sleep(4000);
            thread1.interrupt();
            System.out.println("中断thread-1之后,isInterruted:" + thread1.isInterrupted());
            while (thread1.isInterrupted()) {
                System.out.println("我是thread-0，我发现thread-1中断了");
            }
        } catch (InterruptedException e) {
            System.out.println("我是thread-0,我被中断了");
        }


    }
}
