package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;

/**
 * This Class is an abstraction of the graphic interface
 */
public abstract class View {

    public static View get(){
        if(ClientMain.getGuiSet()){
            return new Gui(ClientMain.getCanvas(), ClientMain.getRoot());
        } else {
            return new Cli();
        }
    }

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showMarket();

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showDevCard();

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showPersonalBoard();

    public abstract void showPBCurrent();
}
