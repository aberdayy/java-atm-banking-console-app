import java.sql.*;

public class CashOperationsManager {
    public static boolean deposit(int accountNum, double valueToAdd, int password) throws SQLException {
        DbHelper helper = new DbHelper();
        Bringer manager = new Bringer();
        Connection connection = null;
        PreparedStatement statement;
        int result = 0;
        double clientBal = manager.balanceBringer(accountNum,password);
        double totalBal = valueToAdd + clientBal;
        try {
            connection = helper.getConnection();
            String sql = "update user set balance = ? WHERE accountNumber= ? AND password =? ";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,totalBal);
            statement.setInt(2,accountNum);
            statement.setInt(3,password);
            result = statement.executeUpdate();
        }catch (SQLException e){
            helper.showErrorMess(e);
        }
        if (result==1){
            return true;
        }else{
            return false;
        }
    }
    public static boolean withdraw(int accountNum, double valueToDeduct, int password) throws SQLException{
        DbHelper helper = new DbHelper();
        Bringer manager = new Bringer();
        Connection connection= null;
        PreparedStatement statement;
        int result = 0;
        double clientBal = manager.balanceBringer(accountNum,password);
        double totalBal = clientBal - valueToDeduct ;
        if (clientBal>=valueToDeduct){
            try {
                connection = helper.getConnection();
                String sql = "update user set balance = ? WHERE accountNumber= ? AND password = ?";
                statement = connection.prepareStatement(sql);
                statement.setDouble(1,totalBal);
                statement.setInt(2,accountNum);
                statement.setInt(3,password);
                result = statement.executeUpdate();
            }catch (SQLException e){
                helper.showErrorMess(e);
            }
            if(result==1){
                return true;
            }else{
                return false;
            }
        }
        else {
            return false;
        }
    }

}
