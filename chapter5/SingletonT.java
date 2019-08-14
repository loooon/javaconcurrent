package chapter5;

/**
 * @author : michael
 * @description:
 * @date : 2019-07-27 15:40
 */
public class SingletonT {
  private SingletonT() {
    System.out.println("Singleton is create");
  }

  private static SingletonT instance = new SingletonT();

  public static SingletonT getInstance() {
    return instance;
  }
}
