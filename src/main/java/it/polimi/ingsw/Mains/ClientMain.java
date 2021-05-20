package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Messages.ErrorMessages.GameDontExistErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameStartedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

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

    private static PlayerGame playerGame;
    private static String currentP;
    private static String clientNick;
    private static boolean guiSet = false;
    private static Group root;
    private static Canvas canvas;


    public static void setRoot(Group root) {
        ClientMain.root = root;
    }

    public static void setCanvas(Canvas canvas) {
        ClientMain.canvas = canvas;
    }



    public static Group getRoot() {
        return root;
    }

    public static Canvas getCanvas() {
        return canvas;
    }
    public static PlayerGame getPlayerGame(){
        return playerGame;
    }

    public static void setPlayerGame(PlayerGame playerGame){
        ClientMain.playerGame = playerGame;
    }

    public static void setCurrentP(String currentP) {
        ClientMain.currentP = currentP;
    }

    public static String getCurrentP() {
        return currentP;
    }

    public static String getClientNick() {
        return clientNick;
    }

    public static boolean getGuiSet(){
        return guiSet;
    }

    public static void main(String[] args) throws IOException {

        ///////
        try {
            ///////

            if (args.length != 6) {
                System.out.println("Invalid number of parameter");
                return;
            }

            String hostName = args[0];
            int portNumber = atoi(args[1]);
            clientNick = args[4];

            if(args[5].equals("-GUI")){
                ClientMain.guiSet = true;
                GuiThread guiThread = new GuiThread();
                Thread guiThreadThread = new Thread(guiThread);
                guiThreadThread.start();
            }

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
                Message lineMessage = Message.fromString(line);
                if (lineMessage.isEqual(new GameDontExistErrorMessage())) {
                    (new GameDontExistErrorMessage()).execute();
                    return;
                }else{
                    System.out.println("Joining game number: " + line);
                }

                line = in.readLine();
                lineMessage = Message.fromString(line);
                if (lineMessage.isEqual(new NameTakenErrorMessage())) {
                    (new NameTakenErrorMessage()).execute();
                    return;
                } else if (lineMessage.isEqual(new GameStartedErrorMessage())) {
                    (new GameStartedErrorMessage()).execute();
                    return;
                } else if (line.equals("You have the inkwell!")) {
                    System.out.println("Joined the game!");
                    System.out.println("You have the inkwell!");
                    line = in.readLine();
                } else {
                    System.out.println("Joined the game!");
                }

                (new Thread() {

                    @Override
                    public void run() {
                        String serverMessage;

                        try {
                            while ((serverMessage = in.readLine()) != null) {
                                if (serverMessage.equals("quit")) {
                                    break;
                                } else if (serverMessage.equals("wakeup")) {
                                    out.println("wakeup");
                                } else if (serverMessage.equals("GAME_ENDED")) {
                                    out.println("GAME_ENDED");
                                    break;
                                } else if (serverMessage.equals("ping")) {
                                    out.println("pong");
                                } else {
                                    Message messageServerMessage = Message.fromString(serverMessage);
                                    if (messageServerMessage instanceof ServiceMessage) {
                                        messageServerMessage.execute(null, out);
                                    } else if (messageServerMessage instanceof ForwardMessage) {
                                        messageServerMessage.execute(null, null);
                                    } else {
                                        String constQuit = "quit";
                                        //System.out.println("the message is: " + messageServerMessage.toString());///////////////////
                                        String last = messageServerMessage.toString().substring(messageServerMessage.toString().length()-4);
                                        //System.out.println("last is: " + last);////////////////////
                                        if(last.equals(constQuit)){
                                            String whoQuited = messageServerMessage.toString().substring(0, messageServerMessage.toString().length() - 5);
                                            playerGame.quitAPlayer(whoQuited);
                                            System.out.println(messageServerMessage);
                                        } else {
                                            System.out.println(messageServerMessage);
                                        }
                                    }
                                }

                            }
                        } catch (IOException e) {
                            System.err.println("Couldn't get I/O for the connection to the server");
                            System.exit(1);
                        }
                    }
                }).start();

                /*if(guiSet){
                    return;
                }*/

                String clientMessage;
                try {
                    while ((clientMessage = stdIn.readLine()) != null) {
                        if (clientMessage.equals("wakeup")) {

                        } else if (clientMessage.equals("GAME_UPDATE")) {
                            if(ClientMain.getPlayerGame() != null){
                                View w = new Cli();
                                w.showMarket();
                                w.showDevCard();
                                w.showPersonalBoard();
                            }

                        } else {
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

            ///////
        } catch (Exception e){
            System.out.println("questo è un gravissimo problema!!!");
            e.printStackTrace();
        }
        ///////

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
