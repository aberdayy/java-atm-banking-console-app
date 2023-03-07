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


                ArrayList<User> activeClient =  bringUser(accountNum, pass);
                String clientName="";
                String clientSurname="";
                int clientAccNum = 0;

                for (User myUser : activeClient){
                    System.out.println("****************************SUCCESFULL LOGIN****************************");
                    clientName = myUser.name;
                    clientSurname = myUser.surname;
                    clientAccNum = myUser.accountNumber;
                    System.out.println("********************************************************************");
                }
                if (clientName.isEmpty()){
                    System.out.println("Wrong account information");

                }else {
                    do {
                        double clientBallance = getBalance(clientAccNum).get(0).balance;

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
                            System.out.println("How much you would like to deposit: ");
                            double value = input.nextDouble();
                            deposit(clientAccNum,value,clientBallance);
                            break;
                        case 2:
                            System.out.println("How much you would like to withdraw: \n" +
                                    "1 => 10 TRY\n" +
                                    "2 => 20 TRY\n" +
                                    "3 => 50 TRY\n" +
                                    "4 => 100 TRY\n" +
                                    "5 => 200 TRY\n" +
                                    "6 => Other : ");
                            int withdrawChoice = input.nextInt();
                            double withdrawValue;
                            switch (withdrawChoice){
                                case 1:
                                    withdrawValue = 10;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }
                                case 2:
                                    withdrawValue = 20;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }
                                case 3:
                                    withdrawValue = 50;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }
                                case 4:
                                    withdrawValue = 100;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }
                                case 5:
                                    withdrawValue = 200;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }
                                case 6:
                                    double userValueInput = input.nextDouble();
                                    withdrawValue = userValueInput;
                                    if (clientBallance>withdrawValue){
                                        withdraw(clientAccNum,withdrawValue,clientBallance);
                                        break;

                                    }else {
                                        System.out.println("Not Enough Balance");
                                        break;

                                    }

                            }
                            break;
                        case 3:
                            double balance = getBalance(clientAccNum).get(0).balance;
                            System.out.println("Balance: "+balance+" TRY");
                            //Do Learn Balance
                            break;
                        case 4:
                            //Do Learn Debt
                            break;
                        case 5:
                            //Do Pay Debt
                            break;
                    }


                }while (userChoice != 6);
                        System.out.println("Thank you for using our ATM");
        }

    }
    public static ArrayList<User> getBalance(int accountNum)throws SQLException{
        ArrayList<User> users = new ArrayList<>();
        Connection connection= null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "Select * from user WHERE accountNumber= ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,accountNum);
            resultSet = statement.executeQuery();
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

        }catch (SQLException e){
            helper.showErrorMess(e);
        }
        return users;
    }
    public static void withdraw(int accountNum,double valueToDeduct,double clientBal) throws SQLException{
        Connection connection= null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        double totalBal = clientBal - valueToDeduct ;
        try {
            connection = helper.getConnection();
            String sql = "update user set balance = ? WHERE accountNumber= ? ";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,totalBal);
            statement.setInt(2,accountNum);
            int result = statement.executeUpdate();
            double balance = getBalance(accountNum).get(0).balance;

            System.out.println("Withdrawed "+ valueToDeduct +" TRY\n" +
                    "Balance remaining: "+balance);
        }catch (SQLException e){
            helper.showErrorMess(e);
        }
    }
    public static void deposit(int accountNum, double valueToAdd,double clientBal) throws SQLException{
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
            System.out.println("Deposited" + valueToAdd+" TRY \nTotal Balance: "+totalBal+" TRY");
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