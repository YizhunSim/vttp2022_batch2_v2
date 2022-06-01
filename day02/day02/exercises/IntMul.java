package day02.day02.exercises;

import java.io.Console;

public class IntMul {
  public static void main(String[] args){
    Console cons = System.console();
    int result = 0;
    String input = "";
    boolean isEnded = false;

    while (!isEnded){
      input = cons.readLine("Enter a number. Blank to end: \n");
      if (input.isEmpty()){
        isEnded = true;
      }else{
        result += Integer.parseInt(input);

      }
    }
    System.out.printf("The Sum is %d", result);
  }
}
