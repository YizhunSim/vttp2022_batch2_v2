package vttp2022.day02;

import java.util.LinkedList;
import java.util.List;

public class DataStructure {
  public static void main (String[] args){
    List<Integer> intList = new LinkedList<Integer>();
    intList.add(Integer.parseInt("10"));
    intList.add(20);
    intList.add(30);
    intList.add(40);
    intList.add(2, 50);
    System.out.println(intList);

    for (int i = 0; i < intList.size(); i++){
      System.out.printf("%d\n", intList.get(i));
    }
  }
}
