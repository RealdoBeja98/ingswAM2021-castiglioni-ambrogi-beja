package it.polimi.ingsw.Mains;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the server of the game
 */
public class ServerMain {

    static String hostName;
    static int portNumber;

    /**
     * main
     * @param args: args[0] is the ip, args[1] is the port
     */
    public static void main( String[] args ) {

        if(args.length==2){
            hostName = args[0];
            System.out.println(hostName);
            portNumber = Integer.parseInt(args[1]);

        }else {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("src/main/resources/ServerInit.json")) {
                Object obj = jsonParser.parse(reader);
                JSONObject j = (JSONObject) ((JSONArray)obj).get(0);
                hostName = (String) (j.get("IP"));
                System.out.println(hostName);
                portNumber = (int) (long) (j.get("PORT"));
                System.out.println(portNumber);
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: couldn't find the ServerInit file");
                return;
            } catch (IOException e) {
                System.out.println("ERROR: failed to initialize server");
                return;
            } catch (ParseException e) {
                System.out.println("ERROR: couldn't find the correct fields in the file");
                return;
            }
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
