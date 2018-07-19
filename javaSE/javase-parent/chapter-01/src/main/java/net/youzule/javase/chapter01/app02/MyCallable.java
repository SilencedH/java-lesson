package net.youzule.javase.chapter01.app02;

import java.util.concurrent.Callable;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/18 21:04
 **/

public class MyCallable implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        System.out.println("my callable");
        return null;
    }
}
