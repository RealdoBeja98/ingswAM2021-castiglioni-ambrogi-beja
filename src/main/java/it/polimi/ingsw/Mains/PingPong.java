package it.polimi.ingsw.Mains;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class PingPong implements Runnable{

    private BufferedReader in;
    private PrintWriter out;
    private boolean exist = true;

    public PingPong(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
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
            //System.out.println("esisto");
            //out.println("ping");
            if(exist == false){
                return;
            }
        }
    }

}
