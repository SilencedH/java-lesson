package net.youzule.spring.chapter02.Bean;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/2 20:06
 **/

public class Duck {

    private String name;

    public Duck() {
        System.out.println("duck constructor ...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                '}';
    }

    public void init(){
        System.out.println("duck init-method");
    }

    public void destroy(){
        System.out.println("duck destroy-method");
    }
}
