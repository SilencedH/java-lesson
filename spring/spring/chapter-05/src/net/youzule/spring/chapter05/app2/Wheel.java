package net.youzule.spring.chapter05.app2;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 14:44
 **/

/*Wheel类*/
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
