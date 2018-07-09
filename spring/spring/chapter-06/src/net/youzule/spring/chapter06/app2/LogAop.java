package net.youzule.spring.chapter06.app2;

import org.aspectj.lang.annotation.*;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/9 14:17
 **/

@Aspect
public class LogAop {

    @Pointcut("execution(* net.youzule.spring.chapter06.app2.Student.test(..))")
    public void pc(){

    }

    @Before("pc()")
    public void beforeAdvice(){
        System.out.println("before advice...");
    }

    @After("pc()")
    public void afterAdvice(){
        System.out.println("after advice...");
    }

    @AfterReturning(pointcut = "pc()",returning = "retVal")
    public void afterReturningAdvice(Object retVal){
        System.out.println(retVal);
    }

    @AfterThrowing(pointcut = "pc()",throwing = "ex")
    public void afterThrowExeception(IllegalArgumentException ex){
        System.out.println("throw exception :" + ex);
    }
}
