import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException  {
        //Check user information Database
        Scanner input = new Scanner(System.in);
        System.out.println("********************************************************************");

        System.out.println("HOSGELDINIZ");
        System.out.println("Lutfen hesap numaranizi giriniz: ");
        String accountNum = input.next();
        System.out.println("Lutfen " + accountNum + " numarali hesap icin sifrenizi giriniz:1 ");
        String pass = input.next();
        bringUser(accountNum,pass);

    }
    public static void bringUser(String accountNum, String password) throws SQLException  {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user Where accountNumber =" + accountNum + " AND password ="+password);

            ArrayList<User> users = new ArrayList<>();
            while(resultSet.next()){
                users.add(
                    new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("password"),
                            resultSet.getInt("accountNumber")
                    )
                );
            }
            for (User myUser : users){
                System.out.println("********************************************************************");
                String client = "Hosgeldiniz sayin " + myUser.name + " " +  myUser.surname ;
                System.out.println(client);
            }

        } catch (SQLException e) {
            helper.showErrorMess(e);
        }finally {
            connection.close();
        }

    }
}