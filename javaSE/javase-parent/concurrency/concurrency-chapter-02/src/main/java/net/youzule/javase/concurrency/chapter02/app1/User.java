package net.youzule.javase.concurrency.chapter02.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/20 19:08
 **/

public class User {
    private String reuslt;
    private int i ;

    public String getReuslt() {
        return reuslt;
    }

    public void setReuslt(String reuslt) {
        this.reuslt = reuslt;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "User{" +
                "reuslt='" + reuslt + '\'' +
                ", i=" + i +
                '}';
    }
}
