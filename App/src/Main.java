import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException  {
        //Check user information Database
        Scanner input = new Scanner(System.in);
        Bringer manager = new Bringer();

        System.out.println("********************************************************************");
        System.out.println("HOSGELDINIZ");
        System.out.println("Lutfen hesap numaranizi giriniz: ");
        int accountNum = input.nextInt();
        System.out.println("Lutfen " + accountNum + " numarali hesap icin sifrenizi giriniz: ");
        int pass = input.nextInt();

        //Assignments
        int userChoice;
        String clientName =  manager.nameBringer(accountNum,pass);
        String clientSurname =  manager.surnameBringer(accountNum,pass);
        int accountNumber = manager.accountNumBringer(accountNum,pass);
        double balance = manager.balanceBringer(accountNum,pass);
        double credit = manager.creditBringer(accountNum,pass);

        /* //withdraw options
        int opt1 = 10;
        int opt2 = 20;
        int opt3 = 50;
        int opt4 = 100;
        int opt5 = 200;*/

        if (clientName.isEmpty()){
            System.out.println("Wrong account information");

        }else {
            do {
                System.out.println("Welcome to our bank dear " + clientName +" "+ clientSurname + " please choose what process you would like to proceed\n" +
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
                    System.out.println(OperationValidator.validator(CashOperationsManager.deposit(accountNumber,value,pass)));
                    System.out.println(manager.balanceBringer(accountNum,pass));
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
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdrawValue,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));
                            break;

                        case 2:
                            withdrawValue = 20;
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdrawValue,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));

                            break;
                        case 3:
                            withdrawValue = 50;
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdrawValue,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));

                            break;
                        case 4:
                            withdrawValue = 100;
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdrawValue,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));

                            break;
                        case 5:
                            withdrawValue = 200;
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdrawValue,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));

                            break;
                        case 6:
                            double withdraw_Value = input.nextDouble();
                            System.out.println(OperationValidator.validator(CashOperationsManager.withdraw(accountNumber,withdraw_Value,pass)));
                            System.out.println(manager.balanceBringer(accountNum,pass));

                            break;
                            }
                            break;
                        case 3:
                            System.out.println(manager.balanceBringer(accountNum,pass));
                            break;
                        case 4:
                            System.out.println(manager.creditBringer(accountNum,pass));
                            //Do Learn Debt
                            break;
                        case 5:
                            System.out.println("Your total debt is: "+manager.creditBringer(accountNum,pass) + " TRY");
                            System.out.println("How much you would like to pay?");
                            double valueToDeduct = input.nextInt();
                            System.out.println(OperationValidator.validator(CashOperationsManager.payDebt(accountNumber,valueToDeduct, pass)));
                            break;
                    }


                }while (userChoice != 6);
                        System.out.println("Thank you for using our ATM");
        }

    }
}