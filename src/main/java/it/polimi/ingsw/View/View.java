package it.polimi.ingsw.View;

/**
 * This Class is an abstraction of the graphic interface
 */
public abstract class View {

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
