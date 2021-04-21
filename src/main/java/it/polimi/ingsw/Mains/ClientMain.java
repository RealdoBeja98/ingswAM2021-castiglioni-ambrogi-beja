package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 1234;
        try (
                Socket echoSocket = new Socket(hostName, portNumber); //creo socket e mi connetto alla .accept
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);//scrive sul canale main
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); // legge dal canale sopra
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //scanf
        ) {
            int i = 0;
            String init;
            while(i<=2){
                init = args[i];
                out.println(init);
                i++;
            }
            String line = in.readLine();
            System.out.println("Joining game number: " + line);
            line = in.readLine();
            if(line.equals("ERROR_NAME_TAKEN")){
                System.out.println("Name already taken, please chose a different one!");
                return;
            }
            if(line.equals("ERROR_GAME_STARTED")){
                System.out.println("Game already started, please chose a different one!");
                return;
            }
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                System.out.println("ho letto " + userInput);
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
