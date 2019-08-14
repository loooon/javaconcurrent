package chapter3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-25 11:38
 */
public class CyclicBarrierExample1 {
  private static CyclicBarrier barrier = new CyclicBarrier(2);

  public static void main(String[] args) throws Exception {

    ExecutorService executor = Executors.newCachedThreadPool();

    for (int i = 0; i < 10; i++) {
      final int threadNum = i;
      Thread.sleep(1000);
      executor.execute(() -> {
        try {
          race(threadNum);
        } catch (Exception e) {
          System.out.println("exception" + e);
        }
      });
    }
    executor.shutdown();
  }

  private static void race(int threadNum) throws Exception {
    Thread.sleep(1000);
    System.out.println("{} is ready+ " + threadNum);
    barrier.await();
    System.out.println("{} is continue+ " + threadNum);

  }
}
