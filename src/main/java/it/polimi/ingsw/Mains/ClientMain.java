package it.polimi.ingsw.Mains;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class represents the client of the game
 */
public class ClientMain {

    public static void main(String[] args) throws IOException {

        String hostName = args[0];
        int portNumber = atoi(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber); //creo socket e mi connetto alla .accept
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);//scrive sul canale main
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); // legge dal canale sopra
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //scanf

        ) {

            int i = 2;
            String init;
            while (i <= 4) {
                init = args[i];
                out.println(init);
                i++;
            }
            String line = in.readLine();
            System.out.println("Joining game number: " + line);
            line = in.readLine();
            if (line.equals("ERROR_NAME_TAKEN")) {
                System.out.println("Name already taken, please chose a different one!");
                return;
            } else if (line.equals("ERROR_GAME_STARTED")) {
                System.out.println("Game already started, please chose a different one!");
                return;
            } else if (line.equals("You have the inkwell!")){
                System.out.println("Joined the game!");
                System.out.println("You have the inkwell!");
                line = in.readLine();
            } else{
                System.out.println(line);
                System.out.println("Joined the game!");
            }

            (new Thread(){

                @Override
                public void run() {
                    String serverMessage;

                    try {
                        while ((serverMessage = in.readLine()) != null) {
                            if (serverMessage.equals("quit")) {
                                break;
                            }
                            else if (serverMessage.equals("wakeup")) {
                                out.println("wakeup");
                            }
                            else{
                                System.out.println(serverMessage);
                            }

                        }
                    } catch (IOException e) {
                        System.err.println("Couldn't get I/O for the connection to the server");
                        System.exit(1);
                    }
                }
            }).start();

            String clientMessage;
            try {
                while ((clientMessage = stdIn.readLine()) != null) {
                    if (clientMessage.equals("wakeup")){

                    }
                    else{
                        out.println(clientMessage);
                        if (clientMessage.equals("quit")) {
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to the keyboard");
                System.exit(1);
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

    private static int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            return -1;
        }
    }

}
