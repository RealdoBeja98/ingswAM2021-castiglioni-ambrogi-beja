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
            while(i<=4){
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
            else if(line.equals("ERROR_GAME_STARTED")){
                System.out.println("Game already started, please chose a different one!");
                return;
            }else{
                System.out.println("Joined the game!");
            }

            String userInput;
            String lines;

            while (true) {

                //waitABit();

                if(stdIn.ready()){
                    userInput = stdIn.readLine();
                    out.println(userInput);
                    if (userInput.equals("quit")) {
                        break;
                    }
                    lines = in.readLine();
                    System.out.println(lines);
                    System.out.println("input next command:");
                }

                //waitABit();

                if(in.ready()){
                    lines = in.readLine();
                    System.out.println(lines);

                }

            }
            return;

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

    static private void waitABit(){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
