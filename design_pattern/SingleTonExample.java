package design_pattern;/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-26 18:18
 */


/**
 * @author : michael
 * @description:
 * @date : 2019-07-26 18:18
 */
public class SingleTonExample {

  public static void main(String[] args) {
    SingleTonExample instance = new SingleTonExample().getInstance();
    SingleTonExample instance1 = instance.getInstance();
    System.out.println(instance==instance1);
  }

  private SingleTonExample() {

  }

  public SingleTonExample getInstance() {
    return Singleton.INSTANCE.getInstance();
  }

  private enum Singleton {
    INSTANCE;
    private SingleTonExample singleton;

    // JVM保证这个方法绝对只调用一次
    Singleton() {
      singleton = new SingleTonExample();
    }

    public SingleTonExample getInstance() {
      return singleton;
    }

  }
}
