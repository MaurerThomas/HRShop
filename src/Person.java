import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Wouter on 5/29/2015.
 */
public class Person {
    DatabaseAccessObject dao = new DatabaseAccessObject();
    Connection connection;

    public Person() {
        connect();
    }

    public void connect() {
        connection = dao.connect();
    }

    public void disconnect() {
        if (connection != null) {
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
        ArrayList<Integer> aantal = dao.read(rquery, connection);
        dao.update(wquery + (aantal.get(0) + 10) + " Where product_ID = 1", connection);
    }

    public void writeUnrepeatable() {
        String rquery = "Select totaal_aantal From Product Where product_ID = 1";

        String wquery = "Update Product set totaal_aantal = ";
        ArrayList<Integer> aantal = dao.read(rquery, connection);
        dao.update(wquery + (aantal.get(0) + 10) + " Where product_ID = 1", connection);
    }


    public String readPhantom() {
        String readPhantomQuery = "Select * from Product Where totaal_aantal BETWEEN 10 AND 30";
        ArrayList<String> aantal = dao.readString(readPhantomQuery, connection);
        return String.valueOf(aantal.size());


    }

    public void writePhantom() {
        String writePhantomQuery = "INSERT INTO verandering(verandering_ID,omschrijving,invoerdatum,totaal_verandering,product_ID) VALUES ('kfjh',2,12)";
        dao.update(writePhantomQuery, connection);
    }


    public String createShareLock(){

        String readLockQuery = "SELECT * from Product Where product_ID = 4 LOCK IN SHARE MODE" ;

        ArrayList<String> aantal = dao.readString(readLockQuery, connection);
        return String.valueOf(aantal);
    }

    public void createNewProduct(){
        String createSLockQuery = "INSERT INTO Product(naam,product_ID, totaal_aantal) VALUES ('deadlock',4,14)";
        dao.update(createSLockQuery,connection);

    }

    public void deleteRowPersonB(){
        String deleteRowQueryB = "DELETE from Product Where product_ID = 4";
        dao.update(deleteRowQueryB,connection);
    }

    public void deleteRowPersonA(){
        String deleteRowQueryA = "DELETE from Product Where product_ID = 4";
        dao.update(deleteRowQueryA,connection);
    }


    public void rollback() {
        dao.rollback(connection);
    }

    public void commit() {
        dao.commit(connection);
    }
}
