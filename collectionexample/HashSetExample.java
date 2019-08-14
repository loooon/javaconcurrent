package collectionexample;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 22:04
 */

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description:
 *
 * @author     : michael
 * @date       : 2019-07-26 22:04
 */
public class HashSetExample {
  //请求总数
  public static int clientTotal = 5000;
  //同时并发执行的线程数
  public static int threadTotal = 200;

  //HashSet是线程不安全的
  private static Set<Integer> set = new CopyOnWriteArraySet<>();


  private  static void update(int i) {
    set.add(i);
  }

  public static void main(String[] args)throws Exception {

    //定义线程池
    ExecutorService executorService = Executors.newCachedThreadPool();
    //定义信号量
    final Semaphore semaphore = new Semaphore(threadTotal);
    //定义计数器闭锁
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    for (int i = 0; i < clientTotal; i++) {

      final int count = i;
      executorService.execute(()->{
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
    System.out.println("size is :" + set.size());
  }

}
