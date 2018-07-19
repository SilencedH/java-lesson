package net.youzule.javase.concurrency.chapter01.app2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/19 10:44
 **/

public class App04 {
    public static void main(String[] args) {

        System.out.println("========程序运行开始=========");

        //定义线程池数量
        int poolSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        //创建多个有返回值的任务

        List<Future<Boolean>> futureList = new ArrayList<>();

        //执行任务并将任务放入list
        for (int i = 1; i <= poolSize; i ++){
            Callable<Boolean> callable = new MyCallable2(i);
            Future<Boolean> future = pool.submit(callable);

            futureList.add(future);
        }

        //5个线程任务结束，关闭线程池，
        pool.shutdown();
        // 从list中拿出返回结果
        for (Future<Boolean> future:futureList){
            try {
                Boolean result = future.get();
                System.out.println("从futureList中拿到的线程执行结果:" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("==========程序运行结束=========");
    }
}
