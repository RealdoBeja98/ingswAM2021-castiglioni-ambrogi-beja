package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Messages.ErrorMessages.GameDontExistErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameStartedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.Messages.UpdateSoloActionTokenMessage;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrackSP;
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

    private static Canvas canvas = null;

    private static ClientMain instance;

    private final static Object lock = new Object();

    public static Object getLock(){
        return ClientMain.lock;
    }

    public static ClientMain getInstance(){
        return ClientMain.instance;
    }

    public static void setCanvas(Canvas canvas) {
        ClientMain.canvas = canvas;
        ClientMain.notifyClientMain();
    }

    public static void notifyClientMain(){
        synchronized (ClientMain.lock){
            ClientMain.lock.notifyAll();
        }
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

    public void main(String[] args) throws IOException {

        ClientMain.instance = this;

        FaithTrackSP.setForClient();

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
                synchronized (ClientMain.lock){
                    while (ClientMain.canvas == null){
                        ClientMain.lock.wait();
                    }
                }
            }

            try (
                    Socket echoSocket = new Socket(hostName, portNumber); //creo socket e mi connetto alla .accept
                    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);//scrive sul canale main
                    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); // legge dal canale sopra
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //scanf
            ) {
                GuiThread.setOut(out);

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
                    View.printMessage("Joining game number: " + line);
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
                    View.printMessage("Joined the game!");
                    if(guiSet){
                        synchronized (ClientMain.lock){
                            while(GuiThread.getIsSetBackground() == false){
                                try {
                                    ClientMain.lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    View.printMessage("You have the inkwell!");
                    line = in.readLine();
                } else {
                    View.printMessage("Joined the game!");
                }

                MessageThread messageThread = new MessageThread(out, in);
                Thread mt = new Thread(messageThread);
                mt.start();

                if(guiSet){
                    mt.join();
                    return;
                }

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

    /**
     * This message does the atoi to a coming string
     * @param str string
     * @return
     */
    private static int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            return -1;
        }
    }

}
