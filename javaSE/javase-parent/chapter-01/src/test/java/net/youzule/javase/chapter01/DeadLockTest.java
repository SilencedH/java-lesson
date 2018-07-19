package net.youzule.javase.chapter01;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DeadLockTest {

    private String resourceA = "resourceA";
    private String resourceB = "resourceB";

    @Test
    public void test1() {
        deadLock();
    }

    public void deadLock() {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("get resourceA");
                    try {
                        Thread.sleep(3000);
                        synchronized (resourceB){
                            System.out.println("get resourceB");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println("get resourceB");
                    synchronized (resourceA){
                        System.out.println("get resourceA");
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }

    @Test
    public void test2() {
        Thread thread = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                count ++;
                System.out.println(count);
            }
        });
        thread.start();
    }


}
