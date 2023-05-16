package bankingapplication3;

public class CurrentAccount implements Account{
    private int accountNumber;
    private String accountName;
    private double balance;
    private final String accountType = "CurrentAccount";
    private double minimum;
    
    public CurrentAccount(int accountNumber,String accountName,double balance){
        this.accountNumber=accountNumber;
        this.accountName=accountName;
        this.balance=balance;
        this.minimum=1000;
    }
    
    @Override
    public int getNumber(){
        return this.accountNumber;
    }
    
    @Override
    public String getName(){
        return this.accountName;
    }
    
    public double getMinimum(){
        return this.minimum;
    }
    
    public void setMinimum(double minimum){
        this.minimum=minimum;
    }
    
    @Override
    public double getBalance(){
        return this.balance;
    }
    
    @Override
    public String getAccountType(){
        return this.accountType;
    }
    
    @Override
    public void deposit(double amount){
        this.balance+=amount;
    }
    
    @Override
    public void withdraw(double amount){
        this.balance-=amount;
    }
}