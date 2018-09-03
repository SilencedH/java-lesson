package net.youzule.javase.io.chapter01.app1;

import java.io.*;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/6 18:01
 **/

public class App {
    public static void main(String[] args) {
        String appDir = System.getProperty("user.dir");
        System.out.println(appDir + "/io/io-chapter-01/src/main/java/net/youzule/javase/io/chapter01/app1/a.txt");
        //File file = new File("C:/Users/13673/Documents/workspace/github/SilencedH/java-lesson/javaSE/javase-parent/io/io-chapter-01/src/main/java/net/youzule/javase/io/chapter01/app1/a.txt");

        File file = new File(appDir + "/io/io-chapter-01/src/main/java/net/youzule/javase/io/chapter01/app1/a.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];

            fileInputStream.read(bytes);

           String a = new String (bytes,"utf-8");
            System.out.println(a);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
