package net.youzule.spring.chapter05.app2;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 14:43
 **/

/*Car类*/
public class Car {

    private String brand;

/*
    @Autowired
*/
    private Wheel wheel;

    public Car() {
    }

    /*新增构造函数，使用@Autowire注解*/
    @Autowired
    public Car(Wheel wheel) {
        this.wheel = wheel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Wheel getWheel() {
        return wheel;
    }

   /* @Autowired*/
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
