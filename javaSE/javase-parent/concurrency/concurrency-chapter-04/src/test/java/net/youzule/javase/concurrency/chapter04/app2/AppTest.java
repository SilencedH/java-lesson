package net.youzule.javase.concurrency.chapter04.app2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AppTest {


    @Test
    public void test1(){
        String a = new String("abc");
        String b = "abc";

        Map<String,Integer> map = new HashMap<>();
        map.put(a,1);
        map.put(b,2);

        System.out.println(map.size());
    }

    @Test
    public void test2(){
        Map<String,Integer> map = new HashMap<>();
        map.put(new A(2).toString(),1);
        map.put(new A(2).toString(),1);
        System.out.println(map.size());
    }


    @Test
    public void test3(){
        System.out.println("abc" + null);
    }

    @Test
    public void test4(){
        A a1 = new A(1);
        A a2 = new A(1);
        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
    }

    class A{
        private int i;

        public A(int i){
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }


}
