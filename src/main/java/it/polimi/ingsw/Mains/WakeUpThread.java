package it.polimi.ingsw.Mains;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class implements a thread that put to sleep a client till all the player are connected
 */
public class WakeUpThread implements Runnable {

    private final BufferedReader in;
    private final PrintWriter out;
    private int returnValue;

    /**
     * Constructor method of this class
     */
    public WakeUpThread(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
        returnValue = 0;
    }

    /**
     * This method overrides the classic run method, and implement a small communication
     * with a client that is in the lobby
     */
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
            returnValue = 1;
        }
    }

    /**
     * Getter of the parameter returnValue
     * @return a 1 if the player wrote quit while in the lobby, of type int
     */
    public int getReturnValue(){
        return returnValue;
    }
}
