package collectionexample;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 22:06
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 22:06
 */
public class HashMapExample {
  //请求总数
  public static int clientTotal = 5000;
  //同时并发执行的线程数
  public static int threadTotal = 200;

  //HashMap是线程不安全的
  private static Map<Integer, Integer> map = new HashMap<>();


  private static void update(int i) {
    map.put(i, i);
  }

  public static void main(String[] args) throws Exception {

    //定义线程池
    ExecutorService executorService = Executors.newCachedThreadPool();
    //定义信号量
    final Semaphore semaphore = new Semaphore(threadTotal);
    //定义计数器闭锁
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    for (int i = 0; i < clientTotal; i++) {

      final int count = i;
      executorService.execute(() -> {
        try {
          semaphore.acquire();
          update(count);
          semaphore.release();
        } catch (Exception e) {
          System.out.println("exception is " + e);

        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();
    System.out.println("size is :" + map.size());
  }
}
