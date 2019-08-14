package chapter3;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;

public class SemaphoreExample2 {

  private final static int threadCount = 20;

  public static void main(String[] args) throws Exception {

    ExecutorService exec = Executors.newCachedThreadPool();

    final Semaphore semaphore = new Semaphore(5);

    for (int i = 0; i < threadCount; i++) {
      final int threadNum = i;
      exec.execute(() -> {
        try {
          System.out.println("adddd" + "ddd");
          semaphore.acquire(3); // 获取多个许可
          test(threadNum);
          semaphore.release(2); // 释放多个许可
        } catch (Exception e) {
          System.out.println("exception" + e);
        }
      });
    }
//    exec.shutdown();
  }

  private static void test(int threadNum) throws Exception {
    System.out.println("{}" + threadNum);
    Thread.sleep(1000);
  }
}