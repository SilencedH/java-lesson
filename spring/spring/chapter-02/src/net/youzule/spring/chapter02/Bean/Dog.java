package net.youzule.spring.chapter02.Bean;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 17:57
 **/

public class Dog {

    private String mouse;

    private String eyes;

    public Dog() {
        System.out.println("dog is initializing... ");
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "mouse='" + mouse + '\'' +
                ", eyes='" + eyes + '\'' +
                '}';
    }
}
