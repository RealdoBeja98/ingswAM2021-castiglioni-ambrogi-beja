package it.polimi.ingsw.Utilities;

import it.polimi.ingsw.Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * this class contains a method to close the communication channel of a socket
 */
public class CloseCommunicationChannel {

    /**
     * This method closes the communication for player whose not in the game
     * @param in reads the message coming from socket
     * @param out sends message to socket
     */
    public static void f(BufferedReader in, PrintWriter out, String nickname, Game game, Socket socket) {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Player: " + nickname + " quit game " + game.getGameIndex());
    }

}
