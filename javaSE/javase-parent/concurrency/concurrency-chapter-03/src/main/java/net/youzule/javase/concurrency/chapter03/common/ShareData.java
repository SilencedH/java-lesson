package net.youzule.javase.concurrency.chapter03.common;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/31 16:28
 **/

public class ShareData {

    public static int count = 2;
    public static int count1 = 0;

    public static volatile boolean flag = false;
}
