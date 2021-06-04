package it.polimi.ingsw.Mains;

import it.polimi.ingsw.PersonalBoard.Faith.FaithTrackSP;

import java.io.IOException;

/*

if (args.length != 6) {
                System.out.println("Invalid number of parameter");
                return;
            }


int i = 2;
                String init;

while (i <= 4) {
                    init = args[i];
                    out.println(init);
                    i++;
                }

 */

//<--FIXME--> Fare il controllo dei parametri -- Atenzione che c'è una parte che non può essere spostata qui: vedi il FIXME in ClientMain

/**
 * Is the main of client
 * Passes the arguments to other classes and starts the method
 */
public class ClientLauncher {

    private static String[] arguments;

    public static void main(String[] args) {
        ClientLauncher.arguments = args;
        (new Thread() {
            @Override
            public void run() {
                ClientMain clientMain = new ClientMain();
                try {
                    FaithTrackSP.setForClient();
                    clientMain.main(ClientLauncher.arguments);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
