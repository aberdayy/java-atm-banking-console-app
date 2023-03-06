import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String usr = "root";
    private String psw = "admin";
    private String dbUrl = "jdbc:mysql://localhost:3306/bank";
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl,usr,psw);
    }
    public String showErrorMess(SQLException e){
        String error = "Error => " + e.getMessage();
        String errorCode = "Error code => " + e.getErrorCode();
        return error + errorCode;
    }
}
