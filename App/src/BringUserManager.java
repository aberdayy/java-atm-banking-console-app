import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BringUserManager{
    public ArrayList<User> bring(int accountNumber, int password) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement;
        ResultSet resultSet;

        connection = helper.getConnection();
        statement = connection.createStatement();

        resultSet = statement.executeQuery("select * from user Where accountNumber =" + accountNumber + " AND password ="+password);
        while(resultSet.next()){
            users.add(
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("password"),
                            resultSet.getInt("accountNumber"),
                            resultSet.getDouble("balance"),
                            resultSet.getDouble("credit")
                    )
            );
        }
        return users;
    }

}
