package day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapMain {
  public static void main(String[] args){

    List<String> names = new ArrayList<>();
    names.add("fred");
    names.add("barney");
    names.add("wilma");
    names.add("betty");

    // BankAccount fred = new BankAccount("fred");
    // BankAccount barney = new BankAccount("barney");
    // BankAccount wilma = new BankAccount("wilma");
    // BankAccount betty = new BankAccount("betty");

    Map<String, BankAccount> accts = new HashMap<>();

    for (String name : names){
      BankAccount bankAccount = new BankAccount(name);
      //        key,                         value
      accts.put(bankAccount.getAccountId(), bankAccount);
    }

    // accts.put(fred.getAccountId(), fred);
    // accts.put(barney.getAccountId(), barney);
    // accts.put(wilma.getAccountId(), wilma);
    // accts.put(betty.getAccountId(), betty);

    System.out.printf("size: %d\n", accts.size());
    // System.out.printf("has fred: %b\n", accts.containsKey("fred".getAccountId()));
    System.out.printf("has pebbles: %b\n", accts.containsKey("pebbles"));

    Set<String> keys = accts.keySet();
    Collection<BankAccount> values = accts.values();

    for (String acctId : keys){
      String accountName = accts.get(acctId).getName();
      System.out.printf("accountId = %s, accountName = %s\n", acctId, accountName);
    }
  }

}
