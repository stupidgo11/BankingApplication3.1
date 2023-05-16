package bankingapplication3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private String bankName;
    
    
    public Bank(){
        this.bankName = bankName;
    }
    
    public Bank(String bankName){
        this.bankName = bankName;
    }
    
    public void listAccounts(){
        Connection con = BankConnection.connect();
        
        
        try {
            Statement statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);
            
            while(results.next()) {
                System.out.println(results.getString(1)+" "+results.getString(2)+" "
                        +results.getString(3)+" "+results.getString(4));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void depositMoney(Account account,double amount){
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accBalance = ? where accID =?";
        
        try {
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(Account account,double amount){
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accBalance = ? where accID =?";
        
        try {
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openAcoount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID,accName,accBalance,accType)"
                    + "values(?,?,?,?)";
        try {    
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAcoount(Account account){
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID = ? and where accType?";
        try {
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Account getAccount(int number,String accountType){
        Connection con = BankConnection.connect();
        Account account=null;
        String sql = "select * from account where accID= ? and accType= ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, accountType);
            ResultSet results = preparedStatement.executeQuery();
            
            results.next();
            String accountName=results.getString(2);
            double balance=results.getDouble(3);
            
//            String accountName =results.getString(2);
//            double balance=results.getDouble(3);
            
            if(accountType.equals("SavingsAccount")){
                account = new SavingsAccount(number,accountName,balance);
            }
            if(accountType.equals("CurrentAccount")){
                account = new CurrentAccount(number,accountName,balance);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
}
