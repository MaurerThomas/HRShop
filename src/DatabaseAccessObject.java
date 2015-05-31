import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Thomas on 29-5-2015.
 */
public class DatabaseAccessObject {

    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "hrshop";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "";


    public Connection connect() {
        try {
            Class.forName(driver).newInstance();
            Connection connection;
            connection = DriverManager.getConnection(url + dbName, userName, password);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String sql, Connection connection) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> read(String sql, Connection connection) {
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

    public ArrayList<String> readString(String sql, Connection connection) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> strings = new ArrayList<String>();
        try {
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                strings.add(rs.getString(1));
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
        return strings;
    }


    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
