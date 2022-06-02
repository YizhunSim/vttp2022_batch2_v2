package day04;

import java.util.UUID;

public class BankAccount {
  private String accountId = UUID.randomUUID().toString().substring(0, 8);
  private String accountHolderName;

  public BankAccount(String bankAcountHolderName){
    this.accountHolderName = bankAcountHolderName;
  }

  public String getName() {
    return accountHolderName;
  }
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  public void setName(String name) {
    this.accountHolderName = name;
  }
  
}
