package net.youzule.spring.chapter05.app3;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 15:39
 **/

public class Wheel {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "count=" + count +
                '}';
    }
}
