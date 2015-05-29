import java.sql.*;

/**
 * Created by Thomas on 29-5-2015.
 */
public class DatabaseConnecter {

    public static final String query = "SELECT * FROM product";
    public static void main(String[] args) {


        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/hrshop", "root", "");
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                System.out.println("Balance is:" + resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
