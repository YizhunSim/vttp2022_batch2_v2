package vttp2022.day01_workshop;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  public static void main (String[] args){
    System.out.println("Welcome to your shopping cart");
    List<String> shoppingList = new ArrayList<>();
    Console cons = System.console();
    Boolean isEnded = false;
    while (!isEnded){
      String userInput = cons.readLine();
      String[] parseCommand = userInput.split(" |, ");
      // for (String in : parseCommand){
      //   System.out.println(in);
      // }
      switch (parseCommand[0]){
        case "list":
          if (shoppingList.size() == 0){
            System.out.println("Your cart is empty");
          } else{
            for (int idx = 0; idx < shoppingList.size(); idx++){
              System.out.printf("%d. %s \n", idx + 1, shoppingList.get(idx));
            }
          }
          break;
        case "add":
          for (int i = 1; i < parseCommand.length; i++){
            String itemToAdd = parseCommand[i];
            if (!shoppingList.contains(itemToAdd)){
              shoppingList.add(itemToAdd);
              System.out.printf("%s added to cart \n", itemToAdd);
            } else{
              System.out.println("Item: " + itemToAdd + " already exist!");
            }
          }
          break;
        case "delete":
          int itemIdxToBeDeleted = Integer.parseInt(parseCommand[1]) - 1;

          if (itemIdxToBeDeleted < shoppingList.size()){
            String itemToBeRemoved = shoppingList.get(itemIdxToBeDeleted);
            shoppingList.remove(itemIdxToBeDeleted);
            System.out.printf("%s removed from cart \n", itemToBeRemoved);
          } else{
            System.out.println("Incorrect item index");
          }
          break;
        case "bye":
          isEnded = true;
          System.out.println("Bye bye! Thank you for shopping");
          break;
        default:
          System.out.println("Unrecognised Command!. Please try again");
      }

    }
  }
}
