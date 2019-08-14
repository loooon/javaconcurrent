package chapter2;

/**
 * Created by 13 on 2017/5/4.
 */
public class InterruptAndStopThread {
    public static void main(String args[]) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("�յ��ж��ź�,ֹͣ���߳�!");
                    break;
                }
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println("Running!");

                // todo ����1:�������yield������������ʲô��
                Thread.yield();
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();//�����жϲ���
    }
}
