package net.youzule.javase.concurrency.chapter05.app1;

import java.util.List;

/**
 * @description:消费者
 * @company:
 * @author:Sean
 * @date:2018/8/20 17:45
 **/

public class Consumer implements Runnable {

    private List<String> lock;

    public Consumer(List<String> lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){

            //这里不能用if，需要用while循环判断

            while (lock.isEmpty()){
                System.out.println(Thread.currentThread().getName() + ":lock为空");
                System.out.println(Thread.currentThread().getName() + ":调用wait方法");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("中断异常");
                }
                System.out.println(Thread.currentThread().getName() + ":wait结束");
            }
            String data = lock.remove(0);
            System.out.println(Thread.currentThread().getName() + ":取出第一个元素为:" + data);

        }
    }
}
