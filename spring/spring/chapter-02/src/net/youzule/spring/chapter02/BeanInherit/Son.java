package net.youzule.spring.chapter02.BeanInherit;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/3 10:06
 **/

public class Son {
    private String name;
    private int age;
    private String gender;

    public Son() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
