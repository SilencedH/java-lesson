package net.youzule.javase.io.chapter01.app1;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/8/7 17:54
 **/

public class AppTest {

    @Test
    public void test(){
        String url = "url:[{pre_url:\"\"}]";

        System.out.println(url);
    }

    @Test
    public void test1(){
        byte[] bytes = new byte[1024];
        System.out.println(bytes.length);
    }

    @Test
    public void testASDASDAS(){
        int i = 17;
        int j = 11;
        System.out.println(i&j);
        System.out.println(i^j);
    }

    @Test
    public void test11(){
        Map<String,String> map = new HashMap<>();
        map.put("name","sean");
        map.put("age","11");

        List<Map<String,String>> mapList = new ArrayList<>();
        mapList.add(map);

        String string = JSON.toJSONString(mapList);
        System.out.println(string);
    }

    @Test
    public void test12(){
        Map<String,Object> map1 = new HashMap<>();
        Map<String,String> map = new HashMap<>();
        map.put("name","sean");
        map.put("age","11");

        List<Map<String,String>> mapList = new ArrayList<>();
        mapList.add(map);
        map1.put("contact",mapList);

        List list = (List) map1.get("contact");
        System.out.println(list);
    }

    @Test
    public void test3(){

    }
}
