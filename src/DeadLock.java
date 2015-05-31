/**
 * Created by Thomas on 31-5-2015.
 */
public class DeadLock {


    public static void main(String[] args) {
        // Maak en start thread 1

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person = new Person();
                    person.createNewProduct();
                    person.commit();
                    System.out.println("Check Result from insert" + person.createShareLock());

                    // Random wachttijd
                    try {


                        // Slaap wachtTijd seconden
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Person A trying to delete row");
                    person.deleteRowPersonA();
                    person.commit();
                    person.disconnect();
                    break;

                }
            }
        }, "Thread 1").start();

        // Maak en start thread 2. Deze draait tegelijkertijd met thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person1 = new Person();
                    // Random wachttijd
                    try {
                       // Slaap wachtTijd seconden
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                    System.out.println("Person B trying to delete row");
                    person1.deleteRowPersonB();
                    person1.commit();
                    person1.disconnect();
                    break;

                }
            }
        }, "Thread 2").start();
    }

}





