package it.polimi.ingsw.View;

/**
 * This Class is an abstraction of the graphic interface
 */
public abstract class View {

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showMarket(int index);

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showDevCard(int index);

    /**
     *abstract method: see implementation in son classes for the details
     */
    public abstract void showPersonalBoard(int index);
}
