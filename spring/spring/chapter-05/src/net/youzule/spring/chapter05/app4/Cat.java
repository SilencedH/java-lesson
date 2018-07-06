package net.youzule.spring.chapter05.app4;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/6 18:41
 **/

/*Cat类*/
public class Cat {

    private String type;

    private String home;

    public Cat() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "type='" + type + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
