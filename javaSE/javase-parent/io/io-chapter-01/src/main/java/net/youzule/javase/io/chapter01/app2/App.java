package net.youzule.javase.io.chapter01.app2;


import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/7 13:59
 **/

public class App {
    public static void main(String[] args) {

        String path = App.class.getClassLoader().getResource("b.txt").getPath();

        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("b.txt");

        byte[] bytes = new byte[1024];

        try {
            inputStream.read(bytes);
            System.out.println(new String(bytes,"utf-8"));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
