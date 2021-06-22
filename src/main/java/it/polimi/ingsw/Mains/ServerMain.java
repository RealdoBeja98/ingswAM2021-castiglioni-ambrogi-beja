package it.polimi.ingsw.Mains;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
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
     * this method sets ip and port using command line parameters or, on their missing, by default ip and port
     * @param args args[0] is the ip, args[1] is the port
     */
    private void setHostAndPort(String[] args){
        if(args.length==3){
            hostName = args[1];
            System.out.println(hostName);
            portNumber = Integer.parseInt(args[2]);
        } else {
            readMyJSONAsText("ServerInit.json");
            /*
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
            */
        }
    }

    /**
     * Helper method for method setHostAndPort
     * @param fname the json file
     */
    private void readMyJSONAsText(String fname) {

        InputStream is = null;
        is = this.getClass().getClassLoader().getResourceAsStream(fname);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = null;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            try {
                line = buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileAsString = sb.toString();
        // eventually.. System.out.println("Contents : " + fileAsString);

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonParser.parse(fileAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject j = (JSONObject) ((JSONArray)obj).get(0);
        hostName = (String) (j.get("IP"));
        System.out.println(hostName);
        portNumber = (int) (long) (j.get("PORT"));
        System.out.println(portNumber);
    }

    /**
     * main
     * @param args: args[0] is the ip, args[1] is the port
     */
    public void main(String[] args) {
        setHostAndPort(args);
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
