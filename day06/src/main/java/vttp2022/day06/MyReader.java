package vttp2022.day06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.crypto.CipherInputStream;

public class MyReader {
  public static void main(String[]args) throws IOException{

    // Decorator pattern
    FileInputStream fis = new FileInputStream(args[0]);
    BufferedInputStream bis = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(bis);

    int lines = ois.readInt();
    System.out.printf("There are %d lines in the file", lines);

    for (int i = 0; i < lines; i++){
      String line = ois.readUTF();
      System.out.printf("%d: %s\n", i, line);
    }

    fis.close();
    ois.close();
  }


}
