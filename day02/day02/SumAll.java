package vttp2022.day02;

public class SumAll {
  public static void main (String[] args){
     int result = 0;
    for (int i = 0; i < args.length; i++){
      result += Integer.parseInt(args[i]);
    }
    System.out.printf("The Sum is %d", result);
  }
}
