package bankingapplication3;

public interface Account {
    public void deposit(double amount);
    public void withdraw(double amount);
    public double getBalance();
    public String getAccountType();
    public String getName();
    public int getNumber();
}
