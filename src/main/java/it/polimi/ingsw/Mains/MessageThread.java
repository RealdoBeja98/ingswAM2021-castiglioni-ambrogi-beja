package it.polimi.ingsw.Mains;

import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.Messages.UpdateSoloActionTokenMessage;
import it.polimi.ingsw.View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageThread implements Runnable{

    PrintWriter out;
    BufferedReader in;

    public MessageThread(PrintWriter out, BufferedReader in){
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        String serverMessage;

        try {
            while ((serverMessage = in.readLine()) != null) {
                //<--FIXME--> creare metodo piccolino mainLoop; usare metodo più piccolo
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
                        /*
                        if(ClientMain.getGuiSet()){
                            synchronized (ClientMain.getLock()){
                                while(GuiThread.getIsSetBackground() == false){
                                    try {
                                        ClientMain.getLock().wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        */
                        messageServerMessage.execute(null, out);
                    } else if (messageServerMessage instanceof ForwardMessage) {
                        messageServerMessage.execute(null, null);
                    } else if (messageServerMessage instanceof UpdateSoloActionTokenMessage) {
                        messageServerMessage.execute(null, null);
                    } else {
                        String constQuit = "quit";
                        String last = messageServerMessage.toString().substring(messageServerMessage.toString().length()-4);
                        if(last.equals(constQuit)){
                            String whoQuited = messageServerMessage.toString().substring(0, messageServerMessage.toString().length() - 5);
                            ClientMain.getPlayerGame().quitAPlayer(whoQuited);
                            View.printMessage(messageServerMessage);
                        } else {
                            //System.out.println("VIENE MOSTARTA LA SCRITTA");
                            //System.out.println("Stai andando a printare " + messageServerMessage.toString() + " con guiset: " + ClientMain.getGuiSet());
                            View.printMessage(messageServerMessage);
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the server");
            System.exit(1);
        }
    }

}
