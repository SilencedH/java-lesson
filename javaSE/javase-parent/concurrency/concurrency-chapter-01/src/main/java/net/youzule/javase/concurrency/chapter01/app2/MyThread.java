package net.youzule.javase.concurrency.chapter01.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/18 20:57
 **/

public class MyThread extends Thread {

    @Override
    public void run(){
        System.out.println("my thread");
    }
}
