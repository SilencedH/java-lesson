package net.youzule.javase.concurrency.chapter04.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/3 16:44
 **/

public class User {

    /*使用final关键字修饰变量时，必须要初始化，否则会报错*/

    private final int a = 6;

    private final String b; //在下面初始化代码块中进行了初始化

    private final static boolean c; //在下面静态初始化块中初始化

    private final char d;

    //private final double e;//如果未初始化会报错


    /*实例变量初始化代码块*/
    {
        b = "b";
        //c = false;
    }

    /*静态初始化块，初始化静态变量*/
    static {
        c = false;
    }

    public User(){
        this.d = 'd';
        //this.a = 8; //会报错，已经初始化的不能更改
    }

    //实例方法不能给final变量赋值
    /*public void e(){
        e = 2;
    }*/
}
