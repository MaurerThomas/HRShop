/**
 * Created by Thomas on 31-5-2015.
 */
public class DeadLock {


    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String args[]) {

        // Maak en start thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock1) {
                    System.out.println("Thread 1: Holding lock 1...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Thread 1: Waiting for lock 2...");
                    synchronized (Lock2) {
                        System.out.println("Thread 1: Holding lock 1 & 2...");
                    }
                }

            }
        }, "Thread 1").start();

        // Maak en start thread 2. Deze draait tegelijkertijd met thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 2...");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Thread 2: Waiting for lock 1...");
                    synchronized (Lock1) {
                        System.out.println("Thread 2: Holding lock 1 & 2...");
                    }
                }
            }
        }, "Thread 2").start();
    }
}





