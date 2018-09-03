package net.youzule.javase.other.valuereference.chapter;

import org.junit.Test;

public class AppTest {


    @Test
    public void test1(){

        String string = "test1";
        System.out.println(string);
        changeString(string);
        System.out.println(string);
    }

    public void changeString(String str){
        str = "str1";

        str = new String("str1");
    }


    @Test
    public void test2(){
        String string = "test2";
        addString(string);
        System.out.println(string);
    }

    public void addString(String string){
        string += "add string";
    }

    @Test
    public void test3(){
        StringBuilder stringBuilder = new StringBuilder("test3");
        System.out.println(stringBuilder.toString());

        appendStringBuilder(stringBuilder);

        System.out.println(stringBuilder.toString());
    }

    public void appendStringBuilder(StringBuilder stringBuilder){
        stringBuilder.append("append string builder");
    }


    @Test
    public void test4(){
        StringBuilder stringBuilder = new StringBuilder("test4");
        System.out.println(stringBuilder.toString());
        appendStringBuilder1(stringBuilder);
        System.out.println(stringBuilder.toString());
    }

    public void appendStringBuilder1(StringBuilder stringBuilder){
        stringBuilder = new StringBuilder("append string builder1");
    }

    @Test
    public void test5(){
        User user = new User();
        user.setName("123");
        changeUserName(user);
        System.out.println(user.getName());
    }

    public void changeUserName(User user){
        user.setName("zdh");
    }


    @Test
    public void test6(){
        int[] a  = {1,2};
        int[] b = a;
        b[1] = 5;
        System.out.println(a[1]);
    }
}
