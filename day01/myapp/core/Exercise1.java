package myapp.core;

import java.io.Console;

public class Exercise1 {
  public static void main(String[] args){
    Console cons = System.console();

    System.out.println("This is a calculator:");
    String operandInput1 = cons.readLine("Operand 1: ").trim();
    String operandInput2 = cons.readLine("Operand 2: ").trim();
    String operator = cons.readLine("Operator: ").trim();
    float result = 0f;

    // if (operator.equals("add")){
    //    result = Integer.parseInt(operandInput1) + Integer.parseInt(operandInput2);
    // }else if (operator.equals("div")){
    //   result = Integer.parseInt(operandInput1) / Integer.parseInt(operandInput2);
    // }
    // else if (operator.equals("sub")){
    //   result = Integer.parseInt(operandInput1) - Integer.parseInt(operandInput2);
    // }
    // else if (operator.equals("mul")){
    //   result = Integer.parseInt(operandInput1) * Integer.parseInt(operandInput2);
    // }
    // System.out.printf("The result is %d", result);

    switch(operator){
      case "add":
        result = (float)(Integer.parseInt(operandInput1) + Integer.parseInt(operandInput2));
        operator = "+";
        //System.out.printf("The result A is %d", result);
        break;
      case "div":
        result = (float)(Integer.parseInt(operandInput1) / Integer.parseInt(operandInput2));
        operator = "/";
        //System.out.printf("The result B is %d", result);
        break;
      case "sub":
        result = (float)(Integer.parseInt(operandInput1) - Integer.parseInt(operandInput2));
        operator = "-";
        //System.out.printf("The result C is %d", result);
        break;
      case "mul":
        result = (float)(Integer.parseInt(operandInput1) * Integer.parseInt(operandInput2));
        operator = "*";
        //System.out.printf("The result D is %d", result);
        break;
      default: System.out.println("Unrecognized operator!");
    }
    System.out.printf("The answer for %s %s %s = %.2f", operandInput1, operator, operandInput2, result);
  }
}
