package net.youzule.javase.concurrency.chapter02.app1;

import org.junit.Test;

import java.util.*;

public class AppTest {


    @Test
    public void test1() {
        String a = "a";
        String b = "b";
        String c = "c";

        String d = a + b + c;
        String e = "a" + "b" + "c";

        //d和e是一样的速度的，因为d和e都是新对象，

        /*
       我们所说的StringBuffer快是指什么快呢，如下面的例子
        */

        String f = "f";
        String g = "g";
        String h = "h";
        h = h + f;
        h = h + g;
        //上面的拼接不如用StringBuffer快,因为h = h + f这样的操作，其实每次都new了一个新对象，再赋值给h
        /*
         * String temp = h + f;
         * h = temp;
         * */
        StringBuffer stringBuffer = new StringBuffer(h);
        stringBuffer.append(f);
        stringBuffer.append(g);


        /*
         * 总结，StringBuffer快于String是指在原来对象上拼接快，直接赋值给新对象并没有String快。
         * */
    }

    @Test
    public void test2() {
        User user = new User();
        System.out.println(user.getReuslt());
    }


    @Test
    public void test3() {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        list1.add("name1");
        list1.add("name2");


        list2.add("name1");
        list2.add("name2");
        list2.add("name9");
        boolean result = compareList(list1, list2);
        System.out.println("==========测试结果" + result + "===========");
    }

    public boolean compareList1(List<String> list1, List<String> list2) {

        //定义flag，如果为false说明list1中有一个String在lsit2中找不到，返回false
        boolean flag = true;

        System.out.println("=============开始=============");
        for (String item1 : list1) {
            int outerInt = list1.indexOf(item1);
            System.out.println("外部list:" + outerInt + " 内容:" + item1);
            for (String item2 : list2) {
                int innerInt = list2.indexOf(item2);
                System.out.println("内部list:" + innerInt + " 内容:" + item2);
                if (item1.equals(item2)) {
                    System.out.println("外部list:" + outerInt + " 内容:" + item1 + "在内部list:" + innerInt + "内容:" + item2 + " 中找到了");
                    //list1中的这一个String在list2中找到了，flag改为true,跳出list2
                    flag = true;
                    System.out.println("flag:" + flag + "跳出list2");
                    break;
                } else {
                    //list1中这一个String和list2中这一个不相同，继续遍历
                    System.out.println("外部list:" + outerInt + " 内容:" + item1 + "在内部list:" + innerInt + "内容:" + item2 + " 中没找到");
                    flag = false;
                    System.out.println("flag:" + flag + "继续list2遍历");
                }
            }
            //遍历list2完后，flag还为false，说明在list2没找到，返回false
            if (!flag) {
                System.out.println("外部list:" + outerInt + " 内容:" + item1 + "在内部list没找到，返回false");
                return false;
            }
        }
        System.out.println("=============都找到了===================");
        //遍历完都有,返回true
        return true;
    }

    public boolean compareList(List<String> list1, List<String> list2) {

        boolean flag = true;

        for (String item1 : list1) {
            for (String item2 : list2) {
                if (item1.equals(item2)) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test4() {

        System.out.println(System.currentTimeMillis());
    }


    public boolean compare(List<String> list1, List<String> list2) {

        return false;
    }

    public boolean listContainsValue(List<String> list, String value) {
        //判断list中是否有 value，有返回true，没有返回false
        for (String item : list) {
            if (item.contains(value)) {
                return true;
            }
        }

        return false;
    }

    @Test
    public void test5() {
        List<String> list = new ArrayList<String>();
        list.add("nnnnameeee");
        System.out.println(listContainsValue(list, "name"));
    }


    public void test6() {
        List<String> list = new ArrayList<String>();

        list.add("name");
        list.add("age");
        list.add("sex");

        String string = "";

        for (String item : list) {
            string = string + item;
        }

        System.out.println(string);

    }


    public boolean compareList2(List<String> list1, List<String> list2) {


        if (list2.containsAll(list1)) {

            return true;

        }

        return false;


    }

    /*
     * 有个Man类，属性name，有个woman类，属性也是name，List1<Man>，List2<Woman>，比较这两个list中姓名是否list2包含list1
     *
     * 就是前一个问题的拓展:不使用list.contains和list.containsAll方法
     *
     * */


    @Test
    public void test7() {
        Map<String, String> map = new HashMap<>();
        String status = map.get("status");

        switch (status) {
            case ("N"): {
                System.out.println("N");
            }
            case ("Y"): {

            }

        }
    }

    @Test
    public void test8() {
        User user = new User();
        user.setI(1);
        user.setReuslt("11");

        Map map = (Map) user;
        System.out.println(map);
    }

    @Test
    public void test9() {
        Map map = new HashMap();

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "name");
        list.add(map1);

        map.put("list", list);
        List<Map<?, ?>> list1 = (List<Map<?, ?>>) map.get("list");

        System.out.println(list1);
    }

    @Test
    public void test10() {
        User user = new User();
        System.out.println(isNull(user.getReuslt()));
    }

    public boolean isNull(String string) {
        return (string == null) || (string == "");
    }

    @Test
    public void test11() {
        String name = "霍建华";
        name = "*" + name.substring(1);

        String idNumber = "122222233222311111";
        idNumber = idNumber.substring(0,3)+"***********"+idNumber.substring(14);
        System.out.println(name);
        System.out.println(idNumber);
    }
}
