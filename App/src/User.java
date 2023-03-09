public class User {
    int id;
    String name;
    String surname;
    int password;
    int accountNumber;
    double balance;
    double credit;
    public User(int id, String name, String surname, int password, int accountNumber, double balance, double credit){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.credit = credit;
    }
}
