import java.util.ArrayList;

/**
 * Created by Wouter on 5/29/2015.
 */
public class Person {
    DatabaseAccessObject connection = new DatabaseAccessObject();

    public Person() {
    }

    public int read() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";
        connection.connect();
        ArrayList<Integer> aantal = connection.read(rquery);

        connection.disconnect();
        return aantal.get(0);
    }

    public void write() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";

        String wquery = "Update Product set totaal_aantal = ";
        connection.connect();
        ArrayList<Integer> aantal = connection.read(rquery);
        connection.update(wquery + (aantal.get(0) + 10) + "Where product_ID = 1");

        connection.disconnect();
    }

    public void rollback() {
        connection.connect();

        connection.rollback();
        connection.disconnect();
    }
}
