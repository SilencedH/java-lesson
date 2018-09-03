package net.youzule.javase.concurrency.chapter05.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/20 17:41
 **/

public class App {

    public static void main(String[] args) {
        Thread consumer1 = new Thread(new Consumer(CommonData.COMMON_LIST));

        Thread consumer2 = new Thread(new Consumer(CommonData.COMMON_LIST));

        Thread producer = new Thread(new Producer(CommonData.COMMON_LIST));

        consumer1.start();
        consumer2.start();
        producer.start();
    }
}
