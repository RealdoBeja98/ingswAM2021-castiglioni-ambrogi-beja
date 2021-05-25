package it.polimi.ingsw.Mains;

import java.io.IOException;

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
                    clientMain.main(ClientLauncher.arguments);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
