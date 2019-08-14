package chapter5;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-27 13:16
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 *
 * @author     : michael
 * @date       : 2019-07-27 13:16
 */
public class FutureExample
{

  static class MyCallable implements Callable<DtoTest> {
    @Override
    public DtoTest call() throws Exception {
      Thread.sleep(5000);
      return new DtoTest("xi小明",33);
    }
  }

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<DtoTest> future = executorService.submit(new MyCallable());//线程池提交任务
    Thread.sleep(1000);
    DtoTest result = future.get();//获取不到一直阻塞
    System.out.println(result);
  }
}
