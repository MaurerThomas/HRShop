/**
 * Created by Wouter on 5/29/2015.
 */
public class DirtyRead {
    public static void main(String[] args) {
        // Maak en start thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person1 = new Person();
                    System.out.println("Person 1 reads" + person1.readDirty());


                    // Random wachttijd
                    try {
                        // Genereer een getal tussen de 0 t/m 10.
                        int wachtTijd = (int) (Math.random() * 11);
                        System.out.println(Thread.currentThread().getName() + ": Slaap " +
                                wachtTijd + " sec");

                        // Slaap wachtTijd seconden
                        Thread.sleep(wachtTijd * 1000);
                    } catch (InterruptedException e) {
                    }

                    System.out.println("Person 1 reads" + person1.readDirty());


                }
            }
        }, "Thread 1").start();

        // Maak en start thread 2. Deze draait tegelijkertijd met thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person2 = new Person();
                    // Random wachttijd
                    try {
                        // Genereer een getal tussen de 0 t/m 10.
                        int wachtTijd = (int) (Math.random() * 11);
                        System.out.println(Thread.currentThread().getName() + ": Slaap " +
                                wachtTijd + " sec");

                        // Slaap wachtTijd seconden
                        Thread.sleep(wachtTijd * 1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("writeDirty");
                    person2.writeDirty();
                    // Random wachttijd
                    try {
                        // Genereer een getal tussen de 0 t/m 10.
                        int wachtTijd = (int) (Math.random() * 11);
                        System.out.println(Thread.currentThread().getName() + ": Slaap " +
                                wachtTijd + " sec");

                        // Slaap wachtTijd seconden
                        Thread.sleep(wachtTijd * 1000);
                    } catch (InterruptedException e) {
                    }
                    person2.rollback();
                }
            }
        }, "Thread 2").start();
    }
}
