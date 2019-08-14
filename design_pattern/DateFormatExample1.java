package design_pattern;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 20:52
 */

public class DateFormatExample1 {
  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
  //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

  //请求总数
  public static int clientTotal = 5000;
  //同时并发执行的线程数
  public static int threadTotal = 200;

  public static AtomicInteger count = new AtomicInteger(0);


  private static void update() {
    try {
      //      Date parse = simpleDateFormat.parse("20180208");
      //      System.out.println("parse result is :" + parse + " " + count.addAndGet(1));
      LocalDate time = LocalDate.parse("20180208", formatter);
      System.out.println(time);
    } catch (Exception e) {
      System.out.println("parse exception" + e);
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
          System.out.println("exception " + e);
        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();

  }

}
