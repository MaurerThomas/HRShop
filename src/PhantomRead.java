/**
 * Created by Thomas on 31-5-2015.
 */
public class PhantomRead {



    public static void main(String[] args) {
        // Maak en start thread 1

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Person person = new Person();
                    System.out.println("Thread 1: First Read: "+person.readPhantom(false));

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

                    System.out.println("Thread 1: Second Read: " + person.readPhantom(true));
                    person.disconnect();


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
                        // Genereer een getal tussen de 0 t/m 10.
                        int wachtTijd = (int) (Math.random() * 11);
                        System.out.println(Thread.currentThread().getName() + ": Slaap " +
                                wachtTijd + " sec");

                        // Slaap wachtTijd seconden
                        Thread.sleep(wachtTijd * 1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("Thread 2: Insert");
                    person1.writePhantom();
                    person1.disconnect();
                    break;

                }
            }
        }, "Thread 2").start();
    }


}
