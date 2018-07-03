package net.youzule.spring.chapter02.Bean;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/6/30 18:24
 **/

public class Lion {
    private String lion;

    public Lion() {
        System.out.println("initialize liong...");
    }

    public String getLion() {
        return lion;
    }

    public void setLion(String lion) {
        this.lion = lion;
    }

    public void init(){
        System.out.println("initialize call back...");
    }

    public void destory(){
        System.out.println("destroy call back...");
    }

    @Override
    public String toString() {
        return "Lion{" +
                "lion='" + lion + '\'' +
                '}';
    }
}
