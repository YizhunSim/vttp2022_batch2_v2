package day02_workshop;

public class Main {
  public static void main (String[] args){
    // Happy flow - Test Case 1
    BankAccount bc = new BankAccount("Yizhun");
    bc.deposit(100000);
    bc.withdraw(50000);
    System.out.println("Account balance is: $" + bc.retrieveAccountBalance());;

     // Happy flow - Test Case 2
    BankAccount bc2 = new BankAccount("YizhunS", 500000);
    bc2.deposit(20000);
    bc2.withdraw(10000);
    System.out.println("Account balance is: $" + bc2.retrieveAccountBalance());;

    //  // Happy flow - Test Case 3
    // FixedDepositAccount fda = new FixedDepositAccount("Zhun");
    // fda.deposit(100000);
    // fda.withdraw(40000);
    // System.out.println("Account balance is: " + fda.retrieveAccountBalance());;

    // // Happy flow - Test Case 4
    // FixedDepositAccount fda2 = new FixedDepositAccount("ZhunSim", 1000000);
    // fda2.deposit(20000);
    // fda2.withdraw(10000);
    // System.out.println("Account balance is: " + fda2.retrieveAccountBalance());;
  }
}
