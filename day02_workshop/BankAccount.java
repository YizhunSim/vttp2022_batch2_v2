package day02_workshop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private float accountBalance;
    private List<String> transactions;
    private Boolean isAccountOpened = false;
    private LocalDateTime accountCreationDate;
    private LocalDateTime accountClosingDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public BankAccount(String accountHolderName){
      this.setAccountNumber();

      this.setAccountHolderName(accountHolderName);
      this.setAccountBalance(0);

      this.isAccountOpened = true;

      this.transactions = new ArrayList<>();

      System.out.println("Account is created: " + accountHolderName);
    }

    public BankAccount (String accountHolderName, float accountBalance){
      this.setAccountNumber();

      this.setAccountHolderName(accountHolderName);
      this.setAccountBalance(accountBalance);

      this.setAccountCreationDate(LocalDateTime.now());

      this.isAccountOpened = true;

      this.transactions = new ArrayList<>();

      System.out.println("Account is created: " + this.getAccountHolderName() + ". Account balance: " + this.getAccountBalance());
    }

    public void deposit (float amountToDeposit){
      if (isValidDepositTransaction(amountToDeposit)){
        float newAccountBalance = this.getAccountBalance() + amountToDeposit;
        this.setAccountBalance(newAccountBalance);
        String depositSuccessAcknowledgementMsg = "Deposit of $" + amountToDeposit + " at " + LocalDateTime.now().format(formatter);
        this.transactions.add(depositSuccessAcknowledgementMsg);

        System.out.println(transactions.get(this.transactions.size() - 1));
      } else{
        throw new IllegalArgumentException("Transaction failed!. Failed to deposit money into bank account: " + this.getAccountHolderName());
      }
    }

    public void withdraw (float amountToWithdraw){
      if (isValidWithdrawTransaction(amountToWithdraw)){
        float newAccountBalance = this.getAccountBalance() - amountToWithdraw;
        this.setAccountBalance(newAccountBalance);
        String withdrawalSuccessAcknowledgementMsg = "Withdrawal of $" + amountToWithdraw + " at " + LocalDateTime.now().format(formatter);
        this.transactions.add(withdrawalSuccessAcknowledgementMsg);

        System.out.println(transactions.get(this.transactions.size() - 1));
      } else{
        throw new IllegalArgumentException("Transaction failed!. Failed to withdraw money from bank account: " + this.getAccountHolderName());
      }
    }

    public float retrieveAccountBalance (){
      return this.getAccountBalance();
    }

    private boolean isValidDepositTransaction(float amountToDeposit){
      if (isAccountOpened && amountToDeposit > 0){
        return true;
      } else{
        return false;
      }
    }

    // Helper method to check if valid withdraw transaction
    private boolean isValidWithdrawTransaction(float amountToWithdraw){
      if (isAccountOpened && amountToWithdraw > 0 &&  amountToWithdraw > this.getAccountBalance()){
        return false;
      } else{
        return true;
      }
    }

    public LocalDateTime getAccountClosingDate() {
      return accountClosingDate;
    }

    public void setAccountClosingDate(LocalDateTime accountClosingDate) {
      this.accountClosingDate = accountClosingDate;
    }

    public LocalDateTime getAccountCreationDate() {
      return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
      this.accountCreationDate = accountCreationDate;
    }

    public Boolean getIsAccountClosed() {
      return isAccountOpened;
    }

    public void setIsAccountClosed(Boolean isAccountOpened) {
      this.isAccountOpened = isAccountOpened;
    }

    public List<String> getTransactions() {
      return transactions;
    }

    public void setTransactions(List<String> transactions) {
      this.transactions = transactions;
    }

    public float getAccountBalance() {
      return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
      this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
      return accountNumber;
    }

    public void setAccountNumber() {
      Random random = new Random();
      int num = random.nextInt(100000);
      String formattedAccountNumber = String.format("%05d", num);
      this.accountNumber = formattedAccountNumber;
    }

    public String getAccountHolderName() {
      return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName){
      this.accountHolderName = accountHolderName;
    }


    
}
