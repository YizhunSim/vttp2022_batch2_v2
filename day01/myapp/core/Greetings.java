package myapp.core;

import java.io.Console;

public class Greetings {
  public static void main(String[] args){
    Console cons = System.console();

    String name = cons.readLine("What is your name? ");
    System.out.printf("Hello %s. Nice to meet you. \n", name.toUpperCase());

  }
}
