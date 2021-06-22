package it.polimi.ingsw.Mains;

import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.Messages.UpdateSoloActionTokenMessage;
import it.polimi.ingsw.View.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class of Message Thread
 */
public class MessageThread implements Runnable{

    private PrintWriter out;
    private BufferedReader in;
    private final static String constQuit = "quit";
    private final static String constDisconnected = "disconnected";

    /**
     * Constructor of the class
     * @param out sends message to the sockect
     * @param in reads message from the socket
     */
    public MessageThread(PrintWriter out, BufferedReader in){
        this.out = out;
        this.in = in;
    }

    /**
     * This method notifies other players if a player has quited the game
     * @param messageServerMessage server message
     */
    private void anotherPlayerQuit(Message messageServerMessage){
        String whoQuited = messageServerMessage.toString().substring(0, messageServerMessage.toString().length() - 5);
        ClientMain.getPlayerGame().quitAPlayer(whoQuited);
        View.printMessage(messageServerMessage);
    }

    /**
     * This method is occupied to send the messages to the threads
     * @throws IOException error message
     */
    private void mainLoop() throws IOException {
        String serverMessage;
        while ((serverMessage = in.readLine()) != null) {
            switch (serverMessage){
                case "quit": return;
                case "wakeup":
                    out.println("wakeup");
                    break;
                case "GAME_ENDED":
                    out.println("GAME_ENDED");
                    return;
                case "ping":
                    //System.out.println("Now I send a pong!");
                    out.println("pong");
                    break;
                default:
                    Message messageServerMessage = Message.fromString(serverMessage);
                    if (messageServerMessage instanceof ServiceMessage) {
                        messageServerMessage.execute(null, out);
                    } else if (messageServerMessage instanceof ForwardMessage) {
                        messageServerMessage.execute(null, null);
                    } else if (messageServerMessage instanceof UpdateSoloActionTokenMessage) {
                        messageServerMessage.execute(null, null);
                    } else if (messageServerMessage.toString().substring(messageServerMessage.toString().length()-4).equals(constQuit)) {
                        anotherPlayerQuit(messageServerMessage);
                    } else if (messageServerMessage.toString().substring(messageServerMessage.toString().length()-12).equals(constDisconnected)) {
                        //anotherPlayerQuit(messageServerMessage);
                        System.out.println("disconnected here ");
                    }
                    else {
                        View.printMessage(messageServerMessage);
                    }
            }
        }
    }

    /**
     * Overrides the method run
     */
    @Override
    public void run() {
        try {
            mainLoop();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the server");
            System.exit(1);
        }
    }

}
