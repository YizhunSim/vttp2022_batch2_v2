package vttp2022.day06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

public class IOMain {
  public static void main(String[] args){
    System.out.println(args);
    try{
       InputStream is = new FileInputStream(args[0]);
       ZipInputStream zis = new ZipInputStream(is);

       while (true){
        ZipEntry zip = zis.getNextEntry();
        if (zip != null){
          System.out.printf("Filename: %s, size: %d\n", zip.getName(), zip.getSize());
       } else{
          break;
       }
      }
    } catch (FileNotFoundException ex){
      ex.printStackTrace();
    } catch (ZipException ex){
      ex.printStackTrace();
    } catch (IOException ex){
      ex.printStackTrace();
    }
   
    
  }
}
