import java.sql.*;


/**
 * Created by Thomas on 29-5-2015.
 */
public class DatabaseConnecter {

    public static final String query = "SELECT * FROM product";
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "hrshop";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "";
    private Connection connection;

    public DatabaseConnecter() {

        connect();

    }

    public void connect(){
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url + dbName , userName, password);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void disconnect(){

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
