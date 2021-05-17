package it.polimi.ingsw.Mains;

import java.io.IOException;

public class ClientLauncher {

    public static void main(String[] args) {
        try {
            ClientMain.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
