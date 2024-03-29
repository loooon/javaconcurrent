package threadpooltest;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : michael
 * @description:
 * @date : 2019-08-08 14:58
 */
public class ThreadTest {
  public static void main(String[] args) throws InterruptedException, IOException {
    int corePoolSize = 2;
    int maximumPoolSize = 4;
    long keepAliveTime = 10;
    TimeUnit unit = TimeUnit.SECONDS;
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    ThreadFactory threadFactory = new NameTreadFactory();
    RejectedExecutionHandler handler = new MyIgnorePolicy();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
        workQueue, threadFactory, handler);

    executor.prestartAllCoreThreads(); // 预启动所有核心线程
//    executor.prestartAllCoreThreads(); // 预启动所有核心线程

    for (int i = 1; i <= 10; i++) {
      MyTask task = new MyTask(String.valueOf(i));
      executor.submit(task);
    }

    System.in.read(); //阻塞主线程
  }

  static class NameTreadFactory implements ThreadFactory {

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
      System.out.println(t.getName() + " has been created");
      return t;
    }
  }

  public static class MyIgnorePolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
      doLog(r, e);
    }

    private void doLog(Runnable r, ThreadPoolExecutor e) {
      // 可做日志记录等
      System.err.println(r.toString() + " rejected");
      // System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
    }
  }

  static class MyTask implements Runnable {
    private String name;

    public MyTask(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      try {
        System.out.println(this.toString() + " is running!");
        // 让任务执行慢点
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "MyTask [name=" + name + "]";
    }
  }

}
