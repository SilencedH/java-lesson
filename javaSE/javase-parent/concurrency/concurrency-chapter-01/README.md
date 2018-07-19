### Java多线程的实现方式

Java多线程常用的有四种实现方式:

1. 通过继承Thread类，重写run方法；
2. 通过实现Runable接口；
3. 实现Callable接口通过FutureTask包装器来创建Thread线程；
4. 使用ExecutorService、Callable、Future实现有返回结果的多线程

下面看具体demo。

#### 一、继承Thread类

>新建MyThread类，继承Thread

```
public class MyThread extends Thread {

    @Override
    public void run(){
        System.out.println("my thread");
    }
}
```

>运行

```
public class App {
    public static void main(String[] args) {
        //继承Thread
        Thread thread = new MyThread();
        thread.start();
    }
}
```

>运行结果
如果程序运行成功会打印出 **my thread**。

#### 二、实现Runnable接口

>新建MyRunnable类，实现Runnable接口

```
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("my runnable");
    }
}
```

>运行程序

```
public class App02 {
    public static void main(String[] args) {
        //实现Runnable接口
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
```

如果程序运行成功，将会打印出**my runnable**

#### 三、实现Callable接口通过FutureTask包装器来创建Thread线程

>新建MyCallable类，实现Callable接口

```
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("my runnable");
    }
}
```

>运行程序

```
public class App3 {
    public static void main(String[] args) {
        Callable<Boolean> callable = new MyCallable();
        FutureTask<Boolean> booleanFutureTask = new FutureTask<>(callable);
        Thread thread = new Thread(booleanFutureTask);
        thread.start();
    }
}
```

#### 四、使用ExecutorService、Callable、Future实现有返回结果的线程

这种方式其实和上面第三种类似，只不过我们来获取执行结果。

下面我们来写一个小的示例，包含一些小业务。

>新建MyCallable2类，实现Callable接口

```
public class MyCallable2 implements Callable<Boolean> {

    private Integer taskNum;

    public MyCallable2(Integer taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println("my callable2-" + taskNum + "开始");
        //如果线程编号是偶数，睡眠2s，返回true
        if ((taskNum % 2) == 0){
            Thread.sleep(2000);
            long time = System.currentTimeMillis();
            System.out.println("my callable2-" + taskNum + "结束，当前时间戳:" + time);
            return true;
        }
        //如果线程编号是奇数数，睡眠1s，返回false
        Thread.sleep(1000);
        long time = System.currentTimeMillis();
        System.out.println("my callable2-" + taskNum + "结束，当前时间戳:" + time);
        return false;
    }
}
```

>运行程序

```
public class App04 {
    public static void main(String[] args) {

        System.out.println("========程序运行开始=========");

        //定义线程池数量
        int poolSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        //创建多个有返回值的任务

        List<Future<Boolean>> futureList = new ArrayList<>();

        //执行任务并将任务放入list
        for (int i = 1; i <= poolSize; i ++){
            Callable<Boolean> callable = new MyCallable2(i);
            Future<Boolean> future = pool.submit(callable);

            futureList.add(future);
        }

        //5个线程任务结束，关闭线程池，
        pool.shutdown();
        // 从list中拿出返回结果，并打印
        for (Future<Boolean> future:futureList){
            try {
                Boolean result = future.get();
                System.out.println("从futureList中拿到的线程执行结果:" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("==========程序运行结束=========");
    }
}
```

>运行结果

```
========程序运行开始=========
my callable2-1开始
my callable2-2开始
my callable2-4开始
my callable2-3开始
my callable2-5开始
my callable2-1结束，当前时间戳:1531970535977
my callable2-3结束，当前时间戳:1531970535977
my callable2-5结束，当前时间戳:1531970535977
从futureList中拿到的线程执行结果:false
my callable2-2结束，当前时间戳:1531970536981
my callable2-4结束，当前时间戳:1531970536981
从futureList中拿到的线程执行结果:true
从futureList中拿到的线程执行结果:false
从futureList中拿到的线程执行结果:true
从futureList中拿到的线程执行结果:false
==========程序运行结束=========
```

多次运行程序，线程执行顺序并不同。

---
--完--
