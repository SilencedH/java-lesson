package net.youzule.javase.concurrency.chapter03.app1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppTest {


    @Test
    public void test1(){
        /*方式1，使用map*/
        Map<String,String> map = new HashMap<>();
        map.put("pre_type","ss");
        map.put("pre_url","ss");

        List<Map> list = new ArrayList<>();
        list.add(map);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("url_list",list);

        /*
        * data = map1
        * */
    }

    private static boolean listCompare1(List<String> list1, List<String> list2){
        for (int i = 0; i <= list1.size()-1; i++){
            Boolean flag=false;
            for (int j = 0; j <=list2.size()-1; j++){
                if (list1.get(i).equals(list2.get(j))){
                    flag=true;
                }
            }
            if (!flag){
                return false;
            }
        }
        return true;
    }

    private static boolean listCompare(List<String> list1, List<String> list2){
        for (int i = 0; i <= list1.size()-1; i++){
            for (int j = 0; j <=list2.size()-1; j++){
                if(j<list2.size()-1) {
                    if (list1.get(i).equals(list2.get(j))) {
                        break;
                    }
                }else {
                    if (list1.get(i).equals(list2.get(j))) {
                        break;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
