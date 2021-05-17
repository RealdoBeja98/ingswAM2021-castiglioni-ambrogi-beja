package it.polimi.ingsw.Mains;

import it.polimi.ingsw.Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;


public class PingPong implements Runnable{

    private BufferedReader in;
    private PrintWriter out;
    private Game game;
    private Socket socket;
    private String nickname;
    private boolean exist = true;
    long lastUpdate;

    public PingPong(BufferedReader in, PrintWriter out, Game game, Socket socket, String nickname){
        this.in = in;
        this.out = out;
        this.game = game;
        this.socket = socket;
        this.nickname = nickname;
        lastUpdate = (new Timestamp(System.currentTimeMillis())).getTime();
    }

    public void update(){
        lastUpdate = (new Timestamp(System.currentTimeMillis())).getTime();
    }

    public void stop(){
        exist = false;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(exist == false){
                return;
            }
            //System.out.println("I exist");
            out.println("ping");
            long now = (new Timestamp(System.currentTimeMillis())).getTime();
            if(now - lastUpdate > 7000){
                forward(nickname + " crashed", out);
                closeCommunicationChannel(in, out);
                stop();
            }
        }
    }

    private void closeCommunicationChannel(BufferedReader in, PrintWriter out) {
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

    private void forward(String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

}
