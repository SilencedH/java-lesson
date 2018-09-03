package net.youzule.javase.concurrency.chapter05.app2;

import java.util.LinkedList;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/21 10:44
 **/

public class Consumer implements Runnable {

    private LinkedList<Integer> linkedList;



    @Override
    public void run() {
        while (true){
            synchronized (linkedList){
                while (linkedList.isEmpty()){
                    System.out.println("消费者:" + Thread.currentThread().getName() + " 数据为空");
                    try {
                        linkedList.wait();
                        System.out.println("消费者:" + Thread.currentThread().getName() + "退出wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者:" + Thread.currentThread().getName() + "开始消费:" + linkedList.remove(0));
                linkedList.notifyAll();
            }
        }
    }


    public Consumer(LinkedList<Integer> linkedList) {
        this.linkedList = linkedList;
    }

    public LinkedList<Integer> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<Integer> linkedList) {
        this.linkedList = linkedList;
    }
}
