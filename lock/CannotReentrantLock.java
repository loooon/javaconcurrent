package lock;

/**
 * @author : michael
 * @description:
 * @date : 2019-08-01 15:36
 */
public class CannotReentrantLock {
  private boolean isLocked = false;

  public synchronized void lock() throws InterruptedException {
    while (isLocked) {
      wait();
    }
    isLocked = true;
  }

  public synchronized void unlock() {
    isLocked = false;
    notify();
  }
}
