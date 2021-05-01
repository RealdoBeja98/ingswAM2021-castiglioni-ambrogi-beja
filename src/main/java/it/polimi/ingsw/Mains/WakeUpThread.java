package it.polimi.ingsw.Mains;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WakeUpThread implements Runnable {

    BufferedReader in;
    PrintWriter out;

    public WakeUpThread(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String serverMessage;
        out.println("Waiting all the player");
        try {
            while ((serverMessage = in.readLine()) != null) {
                if (serverMessage.equals("wakeup")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the server");
            System.exit(1);
        }
    }
}
