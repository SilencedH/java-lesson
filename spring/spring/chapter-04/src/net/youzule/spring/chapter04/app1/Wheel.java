package net.youzule.spring.chapter04.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 18:56
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
