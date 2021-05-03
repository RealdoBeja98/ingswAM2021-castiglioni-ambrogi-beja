package it.polimi.ingsw.Mains;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WakeUpThread implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private int returnValue;

    public WakeUpThread(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
        returnValue = 0;
    }

    @Override
    public void run() {
        String serverMessage;
        out.println("Waiting all the player");
        try {
            while ((serverMessage = in.readLine()) != null) {
                if (serverMessage.equals("wakeup")) {
                    return;
                }else if (serverMessage.equals("quit")){
                    out.println("quit");
                    returnValue = 1;
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Client crashed");
            returnValue = 1;
            return;
        }
    }

    public int getReturnValue(){
        return returnValue;
    }
}
