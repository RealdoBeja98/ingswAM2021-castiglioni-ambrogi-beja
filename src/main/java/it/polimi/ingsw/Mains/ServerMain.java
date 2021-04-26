package it.polimi.ingsw.Mains;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    static String hostName;
    static int portNumber;

    public static void main( String[] args ) {

        if(args.length==2){
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }else {
            //hostName = Prefs.ReadHostFromJSON();
            //portNumber = Prefs.ReadPortFromJSON();
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                System.out.println("Accepting...");
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket));
                System.out.println("Accepted");
            } catch(IOException e) {
                break;
            }
        }
        executor.shutdown();
    }
}
