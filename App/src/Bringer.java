import java.sql.SQLException;
import java.util.ArrayList;

public class Bringer {
    public String nameBringer(int accountNumber, int password) throws SQLException {
        BringUserManager manager = new BringUserManager();
        ArrayList<User> myUser = new ArrayList<>(manager.bring(accountNumber,password));
        return myUser.get(0).name;
    }
    public String surnameBringer(int accountNumber, int password) throws SQLException {
        BringUserManager manager = new BringUserManager();
        ArrayList<User> myUser = new ArrayList(manager.bring(accountNumber,password));
        return myUser.get(0).surname;
    }
    public int accountNumBringer(int accountNumber, int password) throws SQLException {
        BringUserManager manager = new BringUserManager();
        ArrayList<User> myUser = new ArrayList(manager.bring(accountNumber,password));
        return myUser.get(0).accountNumber;
    }

    public double balanceBringer(int accountNumber, int password) throws SQLException {
        BringUserManager manager = new BringUserManager();
        ArrayList<User> myUser = new ArrayList(manager.bring(accountNumber,password));
        return myUser.get(0).balance;
    }
    public double creditBringer(int accountNumber, int password) throws SQLException {
        BringUserManager manager = new BringUserManager();
        ArrayList<User> myUser = new ArrayList(manager.bring(accountNumber,password));
        return myUser.get(0).credit;
    }


}
