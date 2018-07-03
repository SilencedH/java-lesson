package net.youzule.spring.chapter02.Bean;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 17:59
 **/

public class Cat {
    private String hands;
    private String legs;

    public Cat() {
        System.out.println("cat is initializing...");
    }

    public String getHands() {
        return hands;
    }

    public void setHands(String hands) {
        this.hands = hands;
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "hands='" + hands + '\'' +
                ", legs='" + legs + '\'' +
                '}';
    }
}
