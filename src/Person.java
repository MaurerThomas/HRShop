import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Wouter on 5/29/2015.
 */
public class Person {
    DatabaseAccessObject dao = new DatabaseAccessObject();
    Connection connection;

    public Person() {
        connect();
    }
    public void connect(){
        connection = dao.connect();
    }
    public void disconnect(){
        if(connection != null){
            dao.disconnect(connection);
            connection = null;
        }
    }
    public int readDirty() {
        String readDirtyQuery = "Select totaal_aantal From Product Where product_ID = 1";

        ArrayList<Integer> aantal = dao.read(readDirtyQuery, connection);
        return aantal.get(0);
    }

    public void writeDirty() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";

        String wquery = "Update Product set totaal_aantal = ";
        ArrayList<Integer> aantal = dao.read(rquery,dao.connect());
        dao.update(wquery + (aantal.get(0)+10) + " Where product_ID = 1",connection,false);
    }
    public void writeUnrepeatable() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";

        String wquery = "Update Product set totaal_aantal = ";
        ArrayList<Integer> aantal = dao.read(rquery,dao.connect());
        dao.update(wquery + (aantal.get(0)+10) + " Where product_ID = 1",connection,true);
    }


    public String readPhantom() {
        String readPhantomQuery = "Select * from Product Where totaal_aantal BETWEEN 10 AND 30";
        dao.connect();
        ArrayList<String> aantal = dao.readString(readPhantomQuery,connection);
        return aantal.get(0);


    }

    public void writePhantom(){
        //String readPhantomQuery = "Select * from Product Where totaal_aantal BETWEEN 10 AND 30";
        String writePhantomQuery = "INSERT INTO Product(naam,product_ID, totaal_aantal) VALUES ('kfjh',2,12)";
        //ArrayList<String> aantal = dao.readString(readPhantomQuery,dao.connect());
        dao.update(writePhantomQuery, connection, true);
    }


    public void rollback() {
        dao.rollback(connection);
    }
}
