package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Messages.ErrorMessages.GameDontExistErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameStartedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import javafx.scene.canvas.Canvas;
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
    public final static Object lock = new Object();//temporaneamente pubblico: sost con referential getter o con private

    /**
     * Getter of object getLock
     */
    public static Object getLock(){
        return ClientMain.lock;
    }

    /**
     * Getter of clientMain getInstance
     */
    public static ClientMain getInstance(){
        return ClientMain.instance;
    }

    /**
     * Setter of setCanvas
     * @param canvas canvas
     */
    public static void setCanvas(Canvas canvas) {
        ClientMain.canvas = canvas;
        System.out.println("I go to notify ClientMain now that I've set the canvas");//
        ClientMain.notifyClientMain();
    }

    /**
     * This method notifies all the sockets of client main
     */
    public static void notifyClientMain(){
        synchronized (ClientMain.lock){
            System.out.println("ClientMain notifyAll");//
            ClientMain.lock.notifyAll();
        }
    }

    /**
     * Getter of Canvas
     * @return canvas
     */
    public static Canvas getCanvas() {
        return canvas;
    }

    /**
     * Getter of list of players in the game
     * @return number of players
     */
    public static PlayerGame getPlayerGame(){
        return playerGame;
    }

    /**
     * Setter of the settPlayers
     * @param playerGame instance of playerGame
     */
    public static void setPlayerGame(PlayerGame playerGame){
        ClientMain.playerGame = playerGame;
    }

    /**
     * Setter for the current player
     * @param currentP returns the curren player
     */
    public static void setCurrentP(String currentP) {
        ClientMain.currentP = currentP;
    }

    /**
     * Getter of the current player
     * @return returns the current player
     */
    public static String getCurrentP() {
        return currentP;
    }

    /**
     * Getter of nickname of the player
     * @return string of the name
     */
    public static String getClientNick() {
        return clientNick;
    }

    /**
     * Setter of the Gui
     * @return Gui
     */
    public static boolean getGuiSet(){
        return guiSet;
    }

    /**
     * This method initializes the gui ready for the game
     */
    private void initializeGui() throws InterruptedException {
        ClientMain.guiSet = true;
        GuiThread guiThread = new GuiThread();
        Thread guiThreadThread = new Thread(guiThread);
        guiThreadThread.start();
        synchronized (ClientMain.lock){
            System.out.println("ClientMain wait");//
            while (ClientMain.canvas == null){
                ClientMain.lock.wait();
            }
        }
    }

    /**
     * This methods print the message of the joining game number after the player
     * writes the right parameters  to join in
     * @param in reads message coming from the socket
     * @throws IOException error message
     */
    private void joiningGame(BufferedReader in) throws IOException {
        String line = in.readLine();
        Message lineMessage = Message.fromString(line);
        if (lineMessage.isEqual(new GameDontExistErrorMessage())) {
            (new GameDontExistErrorMessage()).execute();
            return;
        } else {
            View.printMessage("Joining game number: " + line);
        }
    }

    /**
     * This method checks if the player has a name which is already taken by
     * another player
     * @param in reads message send by the socket
     * @throws IOException error message
     */
    private void setNameInTheServer(BufferedReader in) throws IOException {
        String line = in.readLine();
        Message lineMessage = Message.fromString(line);
        if (lineMessage.isEqual(new NameTakenErrorMessage())) {
            (new NameTakenErrorMessage()).execute();
            return;
        } else if (lineMessage.isEqual(new GameStartedErrorMessage())) {
            (new GameStartedErrorMessage()).execute();
            return;
        } else if (line.equals("You have the inkwell!")) {
            View.printMessage("Joined the game!");
            View.printMessage("You have the inkwell!");
            line = in.readLine();
        } else {
            View.printMessage("Joined the game!");
        }
    }

    /**
     * This method updates the cli of the game
     */
    private void cliGameUpdate(){
        View w = new Cli();
        w.showMarket();
        w.showDevCard();
        w.showPersonalBoard();
    }

    private void scanf(BufferedReader stdIn, PrintWriter out){
        String clientMessage;
        try {
            while ((clientMessage = stdIn.readLine()) != null) {
                switch (clientMessage){
                    case "wakeup": break;
                    case "GAME_UPDATE":
                        if(!guiSet && ClientMain.getPlayerGame() != null){
                            cliGameUpdate();
                        }
                    default:
                        out.println(clientMessage);
                        if (clientMessage.equals("quit")) {
                            return;
                        }
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the keyboard");
            System.exit(1);
        }
    }

    private void passArgsToServer(String[] args, PrintWriter out){
        int i = 2;
        String init;
        while (i <= 4) {
            init = args[i];
            out.println(init);
            i++;
        }
    }

    /**
     * This is the main method which allows the player
     * to choose between cli or gui version
     * @param args arguments
     * @throws IOException error message
     */
    public void main(String[] args) throws IOException {

        ClientMain.instance = this;

        String hostName = args[0];
        int portNumber = integerToAscii(args[1]);
        clientNick = args[4];

        if (args[5].equals("-GUI")) {
            try {
                initializeGui();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try (
                Socket echoSocket = new Socket(hostName, portNumber); //creo socket e mi connetto alla .accept
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);//scrive sul canale main
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); // legge dal canale sopra
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)) //scanf
        ) {
            GuiThread.setOut(out);

            passArgsToServer(args, out);

            joiningGame(in);

            setNameInTheServer(in);

            MessageThread messageThread = new MessageThread(out, in);
            Thread mt = new Thread(messageThread);
            mt.start();

            if (guiSet) {
                try {
                    mt.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }

            scanf(stdIn, out);

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(2);
        }

    }

    /**
     * This message does the atoi to a coming string
     * @param str string
     * @return
     */
    private static int integerToAscii(String str){
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
    }

}
