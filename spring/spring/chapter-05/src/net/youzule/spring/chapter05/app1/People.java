package net.youzule.spring.chapter05.app1;

import org.springframework.beans.factory.annotation.Required;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/5 15:46
 **/

public class People {
    private String name;
    private String gender;

    private String address;

    public People() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
