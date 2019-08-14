package collectionexample.safe;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 22:20
 */

import java.util.Vector;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 22:20
 */
public class TestVectorAddRemoveArrayIndexOutOfBoundsException {
  static Vector<Integer> vector = new Vector<Integer>();

  public static void main(String[] args) throws InterruptedException {
    while (true) {
      for (int i = 0; i < 10; i++) {
        vector.add(i);
      }
      Thread thread1 = new Thread() {
        @Override
        public void run() {
          for (int i = 0; i < vector.size(); i++) {
            vector.remove(i);
          }
         /* synchronized (TestVectorAddAndRemoveOutOfException.class) {
            for (int i = 0; i < vector.size(); i++) {
              vector.remove(i);
            }
            System.out.println(Thread.currentThread().getId() + "" + vector);
          }*/


        }

        ;
      };
      Thread thread2 = new Thread() {
        @Override
        public void run() {
          for (int i = 0; i < vector.size(); i++) {
            vector.get(i);
          }

          /*synchronized (TestVectorAddAndRemoveOutOfException.class) {
            for (int i = 0; i < vector.size(); i++) {
              vector.get(i);
            }
            System.out.println(Thread.currentThread().getId() + "" +vector);
          }*/
        }

        ;
      };
      thread1.start();
      thread2.start();
      while (Thread.activeCount() > 10) {

      }
    }
  }
}
