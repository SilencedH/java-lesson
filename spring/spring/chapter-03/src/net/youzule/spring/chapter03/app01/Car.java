package net.youzule.spring.chapter03.app01;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/3 20:36
 **/

/*Car类*/
public class Car {
    private String brand;

    private int capacity;

    private Wheel wheel;

    private Frame frame;

    public Car() {
    }

    public Car(String brand, int capacity) {
        this.brand = brand;
        this.capacity = capacity;
    }

    public Car(Wheel wheel, Frame frame) {
        this.wheel = wheel;
        this.frame = frame;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", wheel=" + wheel +
                ", frame=" + frame +
                '}';
    }
}
