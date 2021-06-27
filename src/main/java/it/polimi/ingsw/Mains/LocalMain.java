package it.polimi.ingsw.Mains;

import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class representing the client for a single game without connection
 */
public class LocalMain {

    private static boolean isLocal = false;
    private static String nickname;
    private static final Object lock = new Object();
    private static GuiThread myGuiThread;

    /**
     * This method is to notify LocalMain after a wait
     */
    public static void notifyLocalMain(){
        synchronized (LocalMain.lock){
            LocalMain.lock.notifyAll();
        }
    }

    /**
     * this method is to know if the player in playing a solo game in local without connection
     * @return if the player in playing a solo game in local without connection or not; of type boolean
     */
    public static boolean getIsLocal(){
        return isLocal;
    }

    /**
     * This method is to get the nickname of the player
     * @return the nickname of the player; of type String
     */
    public static String getNickname(){
        return nickname;
    }

    /**
     * This method is to initialize the local server
     */
    private void initializeLocalServer(){
        Game game = new Game(1);
        try {
            game.addPlayer(nickname);
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            e.printStackTrace();
        }
        GameStartServiceMessage gameStartServiceMessage = new GameStartServiceMessage(game);
        gameStartServiceMessage.execute(game, null);
    }

    /**
     * This method is to execute a command from the standard input
     * @param command: the command to execute, of type string
     */
    private void executeCommand(String command){
        Message message = Message.fromString(command);
        if(message instanceof GameMessage){
            message.execute(Game.get(0), null);
        }
    }

    /**
     * This method is to view an update cli of the game
     */
    private void cliGameUpdate(){
        View w = new Cli();
        w.showMarket();
        w.showDevCard();
        w.showPersonalBoard();
    }

    /**
     * This method is to initialize the gui
     */
    private void initializeGui(){
        GuiThread guiThread = new GuiThread();
        myGuiThread = guiThread;
        Thread guiThreadThread = new Thread(guiThread);
        guiThreadThread.start();
    }

    /**
     * This is the main method of this class
     * @param args: the arguments for this main method
     */
    public void main(String[] args){
        nickname = args[1];
        isLocal = true;
        if(args[2].equals("-GUI")){
            initializeGui();
            synchronized (LocalMain.lock){
                while (ClientMain.getCanvas() == null){
                    try {
                        LocalMain.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        initializeLocalServer();
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //scanf
        boolean continueToRead = true;
        while (continueToRead){
            String readString = null;
            try {
                readString = stdIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(readString.equals("quit")){
                if(myGuiThread != null){
                    GuiThread.closeWindow();
                }
                return;
            }
            if(readString.equals("GAME_UPDATE")){
                if(ClientMain.getPlayerGame() != null){
                    cliGameUpdate();
                }
            } else {
                executeCommand(readString);
            }
        }
    }

}
