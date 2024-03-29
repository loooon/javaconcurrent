package chapter3;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CyclicBarrierExample2 {
  private static CyclicBarrier barrier = new CyclicBarrier(5);

  public static void main(String[] args) throws Exception {

    ExecutorService executor = Executors.newCachedThreadPool();

    for (int i = 0; i < 10; i++) {
      final int threadNum = i;
      Thread.sleep(1000);
      executor.execute(() -> {
        try {
          race(threadNum);
        } catch (Exception e) {
          System.out.println("exception :" + e);
        }
      });
    }
    executor.shutdown();
  }

  private static void race(int threadNum) throws Exception {
    Thread.sleep(1000);
    System.out.println("{} is ready" + threadNum);
    try {
      barrier.await(2000, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      System.out.println("BarrierException " + threadNum + "   " + e);
    }
    System.out.println("{} continue " + threadNum);
  }
}
