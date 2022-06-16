package vttp2022.fileparser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        FileParser fp = new FileParser(args[0]);
        Session ss = new Session(fp);
        // ss.printFileContents();
        ss.start();
    }
}
