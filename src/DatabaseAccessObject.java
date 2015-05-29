import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Thomas on 29-5-2015.
 */
public class DatabaseAccessObject {

    public static final String query = "SELECT * FROM product";
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "hrshop";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "";
    private Connection connection;


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
    public void update(String sql){
        try {
            connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Integer> read(String sql) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> integers = new ArrayList<Integer>();
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                integers.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return integers;
    }
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}