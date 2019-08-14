package lock;

/**
 * @author : michael
 * @description: 测试不可重入锁
 * @date : 2019-08-01 15:36
 */
public class Count {
  // 不可重入锁
  //  CannotReentrantLock lock = new CannotReentrantLock();
  // 可重入锁
  CanReentrantLock lock = new CanReentrantLock();

  public void print() throws InterruptedException {
    lock.lock();
    doAdd();
    lock.unlock();
  }

  public void doAdd() throws InterruptedException {
    lock.lock();
    //do something
    lock.unlock();
  }

  public static void main(String[] args) throws InterruptedException {
    Count count = new Count();
    count.print();
  }
}
