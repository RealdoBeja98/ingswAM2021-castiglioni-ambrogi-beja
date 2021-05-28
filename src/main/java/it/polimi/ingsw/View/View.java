package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import javafx.scene.canvas.GraphicsContext;

/**
 * This Class is an abstraction of the graphic interface
 */
public abstract class View {

    public static View get(){
        if(ClientMain.getGuiSet()){
            return new Gui(ClientMain.getCanvas());
        } else {
            return new Cli();
        }
    }

    public static void printMessage(Message message){
        printMessage(message.toString());
    }

    public static void printMessage(String string){
        System.out.println(string);
        View view = View.get();
        if(view instanceof Gui){
            //System.out.println("preso bene con: " + string);/////
            GraphicsContext gc = ClientMain.getCanvas().getGraphicsContext2D();
            ((Gui) view).drawLittleSquare(gc, 318, 928, "Misc/WhiteBackground.png", 280, 26);
            gc.fillText(string, 320, 945);
        } else {
            //System.out.println("preso male con: " + string);/////
        }
    }

    public abstract void showStartingLC();

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

    public abstract void showSoloActionToken(ActionToken actionToken);

    //<--FIXME--> fineire javadock

}
