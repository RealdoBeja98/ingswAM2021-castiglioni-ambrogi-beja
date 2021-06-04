package it.polimi.ingsw.Mains;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Utilities.CloseCommunicationChannel;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;

/**
 * This class represent the handshake and the action of
 * disconnecting and reconnecting of a player
 */
public class PingPong implements Runnable{

    private static final long timeToWaitBetweenTwoPingPong = 2000;
    private static final long timeToWaitBeforeClosingCommunicationChannel = 7000;
    private BufferedReader in;
    private PrintWriter out;
    private Game game;
    private Socket socket;
    private String nickname;
    private boolean exist = true;
    long lastUpdate;

    /**
     * Constructor of the class PingPong
     * @param in reads message from the socket
     * @param out sends message to the socket
     * @param game game instance
     * @param socket socket instance
     * @param nickname name of a specific player
     */
    public PingPong(BufferedReader in, PrintWriter out, Game game, Socket socket, String nickname){
        this.in = in;
        this.out = out;
        this.game = game;
        this.socket = socket;
        this.nickname = nickname;
        lastUpdate = (new Timestamp(System.currentTimeMillis())).getTime();
    }

    /**
     * This methods comes handy to update the time of the moment
     */
    public void update(){
        lastUpdate = (new Timestamp(System.currentTimeMillis())).getTime();
    }

    /**
     * This method sets exist to false if player is not in game
     */
    public void stop(){
        exist = false;
    }

    @Override
    public void run() {
        while(true){
            try {
                //<-FIXME--> usare define
                Thread.sleep(timeToWaitBetweenTwoPingPong);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!exist){
                return;
            }
            //System.out.println("I exist");
            out.println("ping");
            long now = (new Timestamp(System.currentTimeMillis())).getTime();
            if(now - lastUpdate > timeToWaitBeforeClosingCommunicationChannel){
                forward(nickname + " crashed", out);
                CloseCommunicationChannel.f(in, out, nickname, game, socket);
                stop();
            }
        }
    }

    /**
     * This method represents the forward messages
     * @param message
     * @param toExclude
     */
    private void forward(String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

}
