package net.youzule.javase.concurrency.chapter05.app2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/21 10:35
 **/

public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);

        for (int i = 0; i < 5; i++){
            service.submit(new Producer(CommonData.LINKED_LIST,CommonData.MAX_LENGTH));
        }

        for (int i = 0; i < 10; i++){
            service.submit(new Consumer(CommonData.LINKED_LIST));
        }
    }
}
