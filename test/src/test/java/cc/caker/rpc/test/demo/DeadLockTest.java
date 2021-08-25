package cc.caker.rpc.test.demo;

/**
 * DeadLockTest
 *
 * @author cakeralter
 * @date 2021/8/22
 * @since 1.0
 */
public class DeadLockTest {

    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + " get lockA");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + " get lockB");
                }
            }
        }, "Thread-1").start();

        new Thread(() -> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " get lockB");

                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + " get lockA");
                }
            }
        }, "Thread-2").start();
    }
}
