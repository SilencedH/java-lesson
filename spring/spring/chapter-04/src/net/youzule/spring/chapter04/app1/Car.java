package net.youzule.spring.chapter04.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 18:56
 **/
/*Car类*/
public class Car {
    private String brand;

    private Wheel wheel;

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

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
