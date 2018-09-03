package net.youzule.javase.io.chapter01.app4;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/9 16:26
 **/

public class App {

    public static void main(String[] args){
        //InputStream mdIs = App.class.getClassLoader().getResourceAsStream("README.md");
        InputStream mdIs = App.class.getClassLoader().getResourceAsStream("14.html");

        System.out.println(mdIs);
        int length = 0;

        byte[] bytes = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        try {

            while ((length =  mdIs.read(bytes)) != -1){
                stringBuilder.append(new String(bytes,0,length,"utf-8"));
            }

            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
