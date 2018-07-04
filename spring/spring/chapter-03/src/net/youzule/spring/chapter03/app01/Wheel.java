package net.youzule.spring.chapter03.app01;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/3 20:39
 **/

public class Wheel {
    private int count;

    public Wheel() {
    }

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
