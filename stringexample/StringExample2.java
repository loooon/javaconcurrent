package stringexample;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 21:36
 */
public class StringExample2 {
  //请求总数
  public static int clientTotal = 5000;
  //同时并发执行的线程数
  public static int threadTotal = 200;

  public static StringBuffer stringBuffer = new StringBuffer();

  private static void update() {
    stringBuffer.append("1");
    System.out.println(stringBuffer.toString());
  }


  public static void main(String[] args) throws Exception {

    //定义线程池
    ExecutorService executorService = Executors.newCachedThreadPool();
    //定义信号量
    final Semaphore semaphore = new Semaphore(threadTotal);
    //定义计数器闭锁
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    for (int i = 0; i < clientTotal; i++) {
      executorService.execute(() -> {
        try {
          semaphore.acquire();
          update();
          semaphore.release();
        } catch (Exception e) {
          System.out.println("exception is " + e);
        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();

    System.out.println("size:{}:" + stringBuffer.length());
  }
}
