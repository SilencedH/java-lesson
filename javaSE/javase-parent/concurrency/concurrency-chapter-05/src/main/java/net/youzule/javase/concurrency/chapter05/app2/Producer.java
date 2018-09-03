package net.youzule.javase.concurrency.chapter05.app2;

import java.util.LinkedList;
import java.util.Random;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/21 10:36
 **/

public class Producer implements Runnable {

    private LinkedList<Integer> linkedList;

    private int maxLength;

    @Override
    public void run() {
        while (true){
            synchronized (linkedList){
                while (linkedList.size() >= maxLength){
                    System.out.println("生产者:" +Thread.currentThread().getName() + "已经达到最大生产数量:" + maxLength);
                    try {
                        System.out.println("生产者:" + Thread.currentThread().getName() + "进入wait");
                        linkedList.wait();
                        System.out.println("生产者:" + Thread.currentThread().getName() + "退出wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                int i = new Random().nextInt();
                System.out.println("生产者:" + Thread.currentThread().getName() + "生产数据:" + i);
                linkedList.add(i);
                linkedList.notifyAll();
            }
        }



    }




    public Producer(LinkedList<Integer> linkedList, int maxLength) {
        this.linkedList = linkedList;
        this.maxLength = maxLength;
    }

    public LinkedList<Integer> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<Integer> linkedList) {
        this.linkedList = linkedList;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }


}
