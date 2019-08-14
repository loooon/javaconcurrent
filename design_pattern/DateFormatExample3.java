//package design_pattern;
//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Semaphore;
//
//<dependency>
//<groupId>joda-time</groupId>
//<artifactId>joda-time</artifactId>
//<version>2.9</version>
//</dependency>
///**
// * @author : michael
// * @description:
// * @date : 2019-07-26 21:58
// */
//public class DateFormatExample3 {
//
//  //请求总数
//  public static int clientTotal = 5000;
//  //同时并发执行的线程数
//  public static int threadTotal = 200;
//
//  private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");
//
//
//  private static void update(int i) {
//    System.out.println("i is "+i + "time is "+ DateTime.parse("20180208", dateTimeFormatter).toDate());
//  }
//
//
//  public static void main(String[] args) throws Exception {
//
//    //定义线程池
//    ExecutorService executorService = Executors.newCachedThreadPool();
//    //定义信号量
//    final Semaphore semaphore = new Semaphore(threadTotal);
//    //定义计数器闭锁
//    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
//
//    for (int i = 0; i < clientTotal; i++) {
//
//      final int count = i;
//      executorService.execute(() -> {
//        try {
//          semaphore.acquire();
//          update(count);
//          semaphore.release();
//        } catch (Exception e) {
//          System.out.println("exception is " + e);
//        }
//        countDownLatch.countDown();
//      });
//    }
//    countDownLatch.await();
//    executorService.shutdown();
//
//  }
//
//}
