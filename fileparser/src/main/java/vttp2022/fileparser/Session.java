package vttp2022.fileparser;

import java.io.Console;
import java.util.List;

public class Session {
  private FileParser fp;
  private static final String GRID = "GRID";
  private static final String START = "START";
  private static final String DATA = "DATA";
  private char[][] dimensionArray;
  private int startPositionRow;
  private int startPositionCol;

  public Session(FileParser fileParser){
    this.fp = fileParser;
  }

  public void printFileContents(){
    List<String> contents = fp.getContents();
    for (String c : contents){
      System.out.println(c);
    }
  }

  public void start(){
    List<String> contents = fp.getContents();
    int lineNumber = 1;
    for (String line : contents){
      System.out.println(">>> "+ line);
      String[] terms = line.split(" ");
      String command = terms[0];
      switch (command){
        case GRID:
          System.out.println("This is a line that has GRID: "+ line);
          int t1 = Integer.parseInt(terms[1]);
          int t2 = Integer.parseInt(terms[2]);
          dimensionArray = this.fp.parse(t1, t2);
          populateBlankDimension(dimensionArray);
          showDimensionArray(dimensionArray);
          break;
        case START:
          System.out.println("This is a line that has START: "+ line);
          startPositionRow = Integer.parseInt(terms[1]);
          startPositionCol = Integer.parseInt(terms[2]);
          System.out.println("d1: Start positon is "+ startPositionRow);
          System.out.println("d2: Start position is " + startPositionCol);
          break;
        case DATA:
          // Populate the 2 dimension array
          generateDimensionArray(lineNumber, startPositionRow, startPositionCol);
      }
      lineNumber += 1;
    }
    showDimensionArray(dimensionArray);
  }

  private void generateDimensionArray(int startLineNumber, int row, int col){
    int originalCol = col;
    List<String> contents = fp.getContents();
    System.out.println("generateDimensionArray");
    for (int i = startLineNumber; i < contents.size(); i++){
      String starLine = contents.get(i);
      for (int j = 0; j < starLine.length(); j++){
        char c = starLine.charAt(j);
        System.out.println("Char " + j + " is " + c);
        if (c == '*'){
          dimensionArray[row][col] = '*';
        }
        col += 1;
      }
      row += 1;
      col = originalCol;
      System.out.println("ROW: "+ row);
      System.out.println("COL: " + col);
    }
  }

  private void populateBlankDimension(char[][] dimensionArr){
    // int count = 0;
    for (int i = 0; i < dimensionArr.length; i++){
      for (int j = 0; j < dimensionArr[0].length; j++){
        dimensionArr[i][j] = '.';
        // count += 1;
      }
    }
    // System.out.println("count: " +count);
  }

  private void showDimensionArray(char[][] dimensionArray){
     for (int i = 0; i < dimensionArray.length; i++){
      for (int j = 0; j < dimensionArray[0].length; j++){
        System.out.printf(Character.toString(dimensionArray[i][j]));
      }
      System.out.println();
    }
  }
}
