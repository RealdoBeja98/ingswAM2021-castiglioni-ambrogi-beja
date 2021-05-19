package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;

/**
 * This Class is an abstraction of the graphic interface
 */
public abstract class View {

    private static Gui guiInstance = null;

    protected void setGuiInstance(Gui gui){
        guiInstance = gui;
    }

    public static View get(){
        if(ClientMain.getGuiSet()){
            return guiInstance;
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
