package net.youzule.spring.chapter03.app01;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 10:27
 **/
/*Frame类*/
public class Frame {
    private String name;

    public Frame() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "name='" + name + '\'' +
                '}';
    }
}
