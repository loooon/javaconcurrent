package collectionexample.safe;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 22:54
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 22:54
 */
public class TestMultiArrayListRemove {
  static ArrayList<Integer> list = new ArrayList<Integer>();

  public static void main(String[] args) throws InterruptedException {
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    Thread thread1 = new Thread() {
      @Override
      public void run() {
        Iterator<Integer> iterator = list.iterator();
        synchronized (TestMultiArrayListRemove.class) {

          while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println(integer);
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }

      ;
    };
    Thread thread2 = new Thread() {
      @Override
      public void run() {
        Iterator<Integer> iterator = list.iterator();
        synchronized (TestMultiArrayListRemove.class) {
          while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2) {
              iterator.remove();
            }
          }
        }
      }
    };
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println(list);
  }
}
