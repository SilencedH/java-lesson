package net.youzule.javase.concurrency.chapter05.app1;

import java.util.List;

/**
 * @description:生产者
 * @company:
 * @author:Sean
 * @date:2018/8/20 17:42
 **/

public class Producer implements Runnable {

    private List<String> lock;

    public Producer(List<String> lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + "开始添加元素");
            this.lock.add("a");
            lock.notifyAll();
        }
    }
}
