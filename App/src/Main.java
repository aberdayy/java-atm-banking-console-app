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
        System.out.println("Lutfen " + accountNum + " numarali hesap icin sifrenizi giriniz: ");
        String pass = input.next();

            int userChoice;
            do {

                ArrayList<User> activeClient =  bringUser(accountNum, pass);
                String clientName="";
                String clientSurname="";
                double clientBallance = 0;
                int clientAccNum = 0;

                for (User myUser : activeClient){
                    System.out.println("****************************SUCCESFULL LOGIN****************************");
                    clientName = myUser.name;
                    clientSurname = myUser.surname;
                    clientBallance = myUser.balance;
                    clientAccNum = myUser.accountNumber;
                    System.out.println("********************************************************************");
                }


                System.out.println("Welcome to our bank dear " + clientName + " please choose what process you would like to proceed\n" +
                        "1 => Deposit\n" +
                        "2 => Withdraw \n" +
                        "3 => Learn Balance \n" +
                        "4 => Learn Debt \n" +
                        "5 => Pay debt\n" +
                        "6 => Exit \n");
                userChoice = input.nextInt();
                switch (userChoice){
                    case 1:

                        addBalance(clientAccNum,500,clientBallance);
                    case 2:
                        //Do withdraw
                    case 3:
                        //Do Learn Balance
                    case 4:
                        //Do Learn Debt
                    case 5:
                        //Do Pay Debt
                }


            }while (userChoice != 6);
                System.out.println("Thank you for using our ATM");



    }
    public static void addBalance(int accountNum, double valueToAdd,double clientBal) throws SQLException{
        Connection connection= null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        double totalBal = valueToAdd + clientBal;
        try {
            connection = helper.getConnection();
            String sql = "update user set balance = ? WHERE accountNumber= ? ";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,totalBal);
            statement.setInt(2,accountNum);
            int result = statement.executeUpdate();
            System.out.println("Guncellendi");
        }catch (SQLException e){
            helper.showErrorMess(e);
        }

    }
    public static ArrayList<User> bringUser(String accountNum, String password) throws SQLException  {
        ArrayList<User> users = new ArrayList<>();

        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();

                resultSet = statement.executeQuery("select * from user Where accountNumber =" + accountNum + " AND password ="+password);
                    while(resultSet.next()){
                        users.add(
                            new User(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("surname"),
                                    resultSet.getString("password"),
                                    resultSet.getInt("accountNumber"),
                                    resultSet.getDouble("balance")
                            )
                        );
                    }

        } catch (SQLException e) {
            System.out.println(helper.showErrorMess(e));
        }finally {
            connection.close();
        }
        return users;


    }
}