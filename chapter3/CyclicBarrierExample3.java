package chapter3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample3 {

  private static CyclicBarrier barrier = new CyclicBarrier(5, () -> {
    System.out.println("callback is running");
  });

  public static void main(String[] args) throws Exception {

    ExecutorService executor = Executors.newCachedThreadPool();

    for (int i = 0; i < 10; i++) {
      final int threadNum = i;
      Thread.sleep(1000);
      executor.execute(() -> {
        try {
          race(threadNum);
        } catch (Exception e) {
          System.out.println("exception is " + threadNum + "   " + e);
        }
      });
    }
    executor.shutdown();
  }

  private static void race(int threadNum) throws Exception {
    Thread.sleep(1000);
    System.out.println("{} is ready" + threadNum);
    barrier.await();
    System.out.println("{} is continue" + threadNum);

  }
}
