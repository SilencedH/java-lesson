package net.youzule.javase.concurrency.chapter05.app2;

import org.junit.Test;

public class AppTest {


    @Test
    public void test1(){
        String idNumber = "410881199209055571";

        String idNumber1 = idNumber.substring(0,14)+"****";
        System.out.println(idNumber1);
    }

}
