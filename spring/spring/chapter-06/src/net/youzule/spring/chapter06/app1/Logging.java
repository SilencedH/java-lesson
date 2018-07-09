package net.youzule.spring.chapter06.app1;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/7 17:03
 **/

public class Logging {

    public void beforeAdvice(){
        System.out.println("before advice...");
    }

    public void afterAdvice(){
        System.out.println("after advice");
    }

    public void afterReturningAdvice(Object returnObject){
        System.out.println("after returning advice: " + returnObject);
    }

    public void exceptionAdvice(IllegalArgumentException e){
        System.out.println("exception advice:" + e.toString());
    }
}
