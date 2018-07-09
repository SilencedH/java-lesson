package net.youzule.spring.chapter07.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 17:25
 **/

public class Student {
    private Integer age;
    private String name;
    private Integer id;
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
