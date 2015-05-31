import java.util.ArrayList;

/**
 * Created by Wouter on 5/29/2015.
 */
public class Person {
    DatabaseAccessObject connection = new DatabaseAccessObject();


    public Person() {
    }

    public int readDirty() {
        String readDirtyQuery = "Select totaal_aantal From Product Where product_ID = 1";
        connection.connect();
        ArrayList<Integer> aantal = connection.read(readDirtyQuery);

        connection.disconnect();
        return aantal.get(0);
    }

    public void writeDirty() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";

        String wquery = "Update Product set totaal_aantal = ";
        connection.connect();
        ArrayList<Integer> aantal = connection.read(rquery);
        connection.update(wquery + (aantal.get(0) + 10) + "Where product_ID = 1");

        connection.disconnect();
    }

    public String readPhantom() {
        String readPhantomQuery = "Select * from Product Where totaal_aantal BETWEEN 10 AND 30";
        connection.connect();
        ArrayList<String> aantal = connection.readString(readPhantomQuery);
        connection.disconnect();
        return aantal.get(0);


    }

    public void writePhantom(){
        String readPhantomQuery = "Select * from Product Where totaal_aantal BETWEEN 10 AND 30";
        String writePhantomQuery = "INSERT INTO Product(naam,product_ID, totaal_aantal) VALUES ('kfjh',2,12)";
        connection.connect();
        ArrayList<String> aantal = connection.readString(readPhantomQuery);
        connection.update(writePhantomQuery);
        connection.disconnect();
    }


    public void rollback() {
        connection.connect();
        connection.rollback();
        connection.disconnect();
    }
}
