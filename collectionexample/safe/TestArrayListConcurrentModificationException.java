package collectionexample.safe;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 22:25
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 22:25
 */
public class TestArrayListConcurrentModificationException {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(2);
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer integer = iterator.next();
      if (integer == 2) {
        list.remove(integer);
      }
    }
  }

}
