package collectionexample.safe;


import java.util.ArrayList;
import java.util.Vector;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 22:11
 */
public class Test {
  public static void main(String[] args) throws InterruptedException {
    ArrayList<Integer> list = new ArrayList<Integer>();
    Vector<Integer> vector = new Vector<Integer>();
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      list.add(i);
    }
    long end = System.currentTimeMillis();
    System.out.println("ArrayList 进行100000次插入操作耗时：" + (end - start) + "ms");
    start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      vector.add(i);
    }
    end = System.currentTimeMillis();
    System.out.println("Vector 进行100000次插入操作耗时：" + (end - start) + "ms");
  }
}
