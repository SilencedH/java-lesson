### 线程状态转换的基本操作

---

#### 一、Java线程状态

Java中，线程有以下几种状态。

状态名称|说明
-|-
NEW|初始状态，线程被构建，但是还没有调用start()方法
RUNNABLE|运行状态，Java线程将操作系统中的就绪和运行两种状态笼统地称作"运行中"
BLOCKED|阻塞状态，表示线程阻塞于锁
WAITING|等待状态，表示线程进入等待状态，进入该状态表示当前线程需要等待其他线程做出一些特定动作(通知或中断)
TIME_WAITING|超时等待状态，该状态不同于WAITING,它是可以在指定的时间自行返回的
TERMINATED|终止状态，表示当前线程已经执行完毕

#### 二、线程状态的基本操作

##### 2.1 interrupted(中断)

中断是指当一个线程运行时，可以被其他线程调用此线程的 **interrupted()**方法中断此线程，或者由于其他原因线程被中断(时间片不够)。</br>
每个线程有一个isInterrupted属性，表示此线程是否被中断，可以通过此属性实时监控线程中断。</br>
下面我们写个例子，在主线程中中断并监控中断操作。

>线程中断

```
public class App {
    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            System.out.println("thread-1 开始执行");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("我是thread-1,正在循环:" + i);
                } catch (InterruptedException e) {
                    System.out.println("我是thread-1,我中断了，准备休息3s");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        System.out.println("我是thread-1,休息3秒时也被中断了");
                    }
                }
            }

            System.out.println("thread-1 执行结束");

        });
        System.out.println("我是thread-0,我要启动thread-1");

        thread1.start();
        System.out.println("我是thread-0,我要在4s后中断thread-1");
        try {
            System.out.println("中断thread-1之前,isInterruted:" + thread1.isInterrupted());
            Thread.sleep(4000);
            thread1.interrupt();
            System.out.println("中断thread-1之后,isInterruted:" + thread1.isInterrupted());
            while (thread1.isInterrupted()) {
                System.out.println("我是thread-0，我发现thread-1中断了");
            }
        } catch (InterruptedException e) {
            System.out.println("我是thread-0,我被中断了");
        }
    }
}
```

>运行结果

```
我是thread-0,我要启动thread-1
我是thread-0,我要在4s后中断thread-1
中断thread-1之前,isInterruted:false
thread-1 开始执行
我是thread-1,正在循环:0
我是thread-1,正在循环:1
我是thread-1,正在循环:2
中断thread-1之后,isInterruted:true
我是thread-1,我中断了，准备休息3s
我是thread-1,正在循环:4
我是thread-1,正在循环:5
我是thread-1,正在循环:6
我是thread-1,正在循环:7
我是thread-1,正在循环:8
我是thread-1,正在循环:9
thread-1 执行结束
```

##### 2.2 join(加入)

join的意思是指，在线程A中，加入线程B，线程A会等线程B执行完后继续执行。线程B加入时还提供了超时等待，如果在指定时间内B还没执行完毕，A就不再等待B执行完毕，A继续执行。

>新建AThead类

```
public class AThread implements Runnable{

    private Thread thread;

    public AThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("thread-a:开始");

        try {
            System.out.println("thread-a:我要休息3s,等待子线程加入");
            thread.start();
            /*thread.join(2000);
            System.out.println("thread-a:我等了thread2s后不等他了");*/
            System.out.println("thread-a:thread已经Join,等他执行完毕");
            thread.join();

        } catch (InterruptedException e) {
            System.out.println("thread-a我被中断了");
        }

        System.out.println("thread-a:结束");

    }
}
```

>新家BThread类

```
public class BThread implements Runnable {
    @Override
    public void run() {
        System.out.println("thread-b:开始,count:");
        try {
            System.out.println("thread-b:我要休息3s");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("thread-b:我被中断了");
        }
        System.out.println("thread-b:结束");

    }
}
```

>运行程序

```
public class App {

    public static void main(String[] args) {
        Thread threadB = new Thread(new BThread());
        Thread threadA = new Thread(new AThread(threadB));
        threadA.start();
    }
}
```

>运行结果

```
thread-a:开始
thread-a:我要休息3s,等待子线程加入
thread-a:thread已经Join,等他执行完毕
thread-b:开始,count:
thread-b:我要休息3s
thread-b:结束
thread-a:结束
```

使用超时等待，A线程只等待B线程2s。

>修改AThread

```
public class AThread implements Runnable{

    private Thread thread;

    public AThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("thread-a:开始");

        try {
            System.out.println("thread-a:我要休息3s,等待子线程加入");
            thread.start();
            thread.join(2000);
            System.out.println("thread-a:我等了thread2s后不等他了");
            /*System.out.println("thread-a:thread已经Join,等他执行完毕");
            thread.join();*/

        } catch (InterruptedException e) {
            System.out.println("thread-a我被中断了");
        }

        System.out.println("thread-a:结束");

    }
}
```

>运行结果

```
thread-a:开始
thread-a:我要休息3s,等待子线程加入
thread-b:开始,count:
thread-b:我要休息3s
thread-a:我等了thread2s后不等他了
thread-a:结束
thread-b:结束
```

可以看到线程A只等待了线程B2s,而线程B执行完成需要3s，所以a先结束。

##### 2.3 sleep

public static native void sleep(long millis)方法显然是Thread的静态方法，很显然它是让当前线程按照指定的时间休眠，其休眠时间的精度取决于处理器的计时器和调度器。需要注意的是如果当前线程获得了锁，sleep方法并不会失去锁。sleep方法经常拿来与Object.wait()方法进行比价，这也是面试经常被问的地方。

>sleep() VS wait()

两者主要的区别：

1.sleep()方法是Thread的静态方法，而wait是Object实例方法

2.wait()方法必须要在同步方法或者同步块中调用，也就是必须已经获得对象锁。而sleep()方法没有这个限制可以在任何地方种使用。另外，wait()方法会释放占有的对象锁，使得该线程进入等待池中，等待下一次获取资源。而sleep()方法只是会让出CPU并不会释放掉对象锁；

3.sleep()方法在休眠时间达到后如果再次获得CPU时间片就会继续执行，而wait()方法必须等待Object.notift/Object.notifyAll通知后，才会离开等待池，并且再次获得CPU时间片才会继续执行。

##### 2.4 yield

public static native void yield();这是一个静态方法，一旦执行，它会是当前线程让出CPU，但是，需要注意的是，让出的CPU并不是代表当前线程不再运行了，如果在下一次竞争中，又获得了CPU时间片当前线程依然会继续运行。另外，让出的时间片只会分配给当前线程相同优先级的线程。什么是线程优先级了？下面就来具体聊一聊。

现代操作系统基本采用时分的形式调度运行的线程，操作系统会分出一个个时间片，线程会分配到若干时间片，当前时间片用完后就会发生线程调度，并等待这下次分配。线程分配到的时间多少也就决定了线程使用处理器资源的多少，而线程优先级就是决定线程需要或多或少分配一些处理器资源的线程属性。

在Java程序中，通过一个整型成员变量Priority来控制优先级，优先级的范围从1~10.在构建线程的时候可以通过setPriority(int)方法进行设置，默认优先级为5，优先级高的线程相较于优先级低的线程优先获得处理器时间片。需要注意的是在不同JVM以及操作系统上，线程规划存在差异，有些操作系统甚至会忽略线程优先级的设定。

另外需要注意的是，sleep()和yield()方法，同样都是当前线程会交出处理器资源，而它们不同的是，sleep()交出来的时间片其他线程都可以去竞争，也就是说都有机会获得当前线程让出的时间片。而yield()方法只允许与当前线程具有相同优先级的线程能够获得释放出来的CPU时间片。











---

--完--