package net.youzule.javase.concurrency.chapter02.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/30 11:01
 **/

public class App {

    public static void main(String[] args) {
        Thread threadB = new Thread(new BThread());
        Thread threadA = new Thread(new AThread(threadB));
        threadA.start();

    }
}
