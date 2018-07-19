package net.youzule.javase.chapter01.app01;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/17 18:12
 **/

public class DeadLock {
    public static String resourceA = "resourceA";
    public static String resourceB = "resourceB";

    public static void main(String[] args) {
        deadLock();//在终端中输入 jstack pid(进程id)查看信息
    }

    public static void deadLock() {
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
}
