package client;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args)
            throws IOException {

        String username = "zhun";
        String host = "localhost";
        int port = 3000;
        // fred@localhost:3000
        if (args.length == 1) {
           String[] terms = args[0].split("[@:]");
           username = terms[0];
           host = terms[1];
           port = Integer.parseInt(terms[2]);

        }

        //Connect to the server
//        System.out.printf("Connect to server %s on port %d\n", host, port);
        Socket sock = new Socket(host, port);
        System.out.println("Connected to shopping cart server at " + host + " on " + username + " port " + port);

        NetworkIO netIO = new NetworkIO(sock);
        Console cons = System.console();
        String req = "";
        String resp = "";

        // Initialise repository to server
        netIO.write(username);
        resp = netIO.read();
        System.out.printf("%s\n", resp);

        while (!req.trim().equals("exit")) {
            req = cons.readLine("> ");
            netIO.write(req);
            resp = netIO.read();
            System.out.printf("%s\n", resp);
        }

        netIO.close();
        sock.close();

        System.out.println("Terminating client...");
    }

}