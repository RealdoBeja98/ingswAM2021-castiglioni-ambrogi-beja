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

//<--FIXME fare un parser che vede che modalità, porta e ip

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
