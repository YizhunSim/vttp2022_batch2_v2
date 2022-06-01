package day02_workshop;

public class FixedDepositAccount extends BankAccount{
  private float interest = 3;
  private int durationInMonths = 6;

  public FixedDepositAccount(String accountHolderName) {
    super(accountHolderName);
  }

  public FixedDepositAccount(String accountHolderName, float accountBalance) {
    super(accountHolderName, accountBalance);
  }

  public FixedDepositAccount(String accountHolderName, float accountBalance, float interest){
    super(accountHolderName, accountBalance);
    this.interest = interest;
  }

   public FixedDepositAccount(String accountHolderName, float accountBalance, float interest, int duration){
      // super(accountHolderName, accountBalance);
      this(accountHolderName, accountBalance, interest);
      // this.interest = interest;
      this.durationInMonths = duration;
  }

  @Override
  public void deposit (float amountToDeposit){
    System.out.println("No Operation Performed");
    return;
  }

  @Override
  public void withdraw (float amountToWithdraw){
    System.out.println("No Operation Performed");
    return;
  }

  @Override
  public float getAccountBalance(){
    return super.getAccountBalance() * this.interest;
  }

}
