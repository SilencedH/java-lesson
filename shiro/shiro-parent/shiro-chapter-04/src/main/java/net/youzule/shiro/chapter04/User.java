package net.youzule.shiro.chapter04;

import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/13 17:24
 **/

public class User {
    private String name;
    private int age;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", data=" + data +
                '}';
    }
}
