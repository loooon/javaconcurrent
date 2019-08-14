package design_pattern;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 21:42
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 21:42
 */
public class DateFormatExample2 {

  //请求总数
  public static int clientTotal = 5000;
  //同时并发执行的线程数
  public static int threadTotal = 200;


  private static void update() {
    try {
      //用堆栈封闭的方式
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
      simpleDateFormat.parse("20180208");
    } catch (ParseException e) {
      System.out.println("parse exception is " + e);
    }
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
          System.out.println("exception is :" + e);
        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();

  }

}
