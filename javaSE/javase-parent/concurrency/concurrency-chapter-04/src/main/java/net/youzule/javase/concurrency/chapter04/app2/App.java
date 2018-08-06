package net.youzule.javase.concurrency.chapter04.app2;

/**
 * @description:final引用型变量的使用
 * @company:
 * @author:Sean
 * @date:2018/8/3 17:06
 **/

public class App {

    //必须要初始化
    private static final People people = new People("sean",11);

    public static void main(String[] args) {
        System.out.println(people);
        people.setAge(22);
        System.out.println(people);

        /*执行下面的代码*/
       /* People people1 = new People("zyn",12);
        people = people1;*/
    }

     static class People{
        private String name;
        private int age;

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
