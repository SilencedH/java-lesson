package net.youzule.javase.io.chapter01.app3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/7 15:04
 **/

public class App {

    public static void main(String[] args) {
        String path = App.class.getClassLoader().getResource("b.txt").getPath();

        System.out.println(path);

        File file = new File(path);
        System.out.println(file.length());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            StringBuilder stringBuilder = new StringBuilder();

            byte[] bytes = new byte[4];

            int length;
            while ((length = fileInputStream.read(bytes)) != -1) {
                String a = new String(bytes, 0, length, "utf-8");
                stringBuilder.append(a);
            }
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
