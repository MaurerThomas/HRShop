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

                    System.out.println("Person 1 reads and writes" + person1.readDirty());
                    person1.writeDirty();

                    System.out.println("Person 1 after write" + person1.readDirty());
                    person1.commit();
                    person1.disconnect();

                }
            }
        }, "Thread 1").start();

        // Maak en start thread 2. Deze draait tegelijkertijd met thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person2 = new Person();
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
                    System.out.println("rollback");

                    person2.rollback();

                    person2.commit();
                    person2.disconnect();
                }
            }
        }, "Thread 2").start();
    }
}
