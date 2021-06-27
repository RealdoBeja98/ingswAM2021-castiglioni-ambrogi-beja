package it.polimi.ingsw.View;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.LocalMain;
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

    /**
     * This message prints the message in view
     * @param message message to print
     */
    public static void printMessage(Message message){
        if(LocalMain.getIsLocal()){
            if(Game.get(0).getTurn().getGameEnded()){
                return;
            }
        }
        printMessage(message.toString());
    }

    /**
     * This method prints the string
     * @param string string to print
     */
    public static void printMessage(String string){
        System.out.println(string);
        View view = View.get();
        if(view instanceof Gui){
            String cons1 = "Joining game number: ";
            String cons2 = "quit";
            GraphicsContext gc = ClientMain.getCanvas().getGraphicsContext2D();
            boolean quitCase = false;
            if(string.length() > cons2.length() && cons2.equals(string.substring(string.length()-cons2.length()))){
                ((Gui) view).reloadView();
                quitCase = true;
            } else {
                ((Gui) view).drawLittleSquare(gc, 318, 931, "Misc/WhiteBackground.png", 280, 26);
                gc.fillText(string, 320, 948);
            }
            if((string.length() > cons1.length() && cons1.equals(string.substring(0, cons1.length()))) || quitCase){
                ((Gui) view).drawLittleSquare(gc, 318, 967, "Misc/WhiteBackground.png", 150, 26);
                gc.fillText(string, 320, 984);
            }
        }
    }

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showStartingLC();

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showMarket();

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showDevCard();

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showPersonalBoard();

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showPBCurrent();

    /**
     * abstract method: see implementation in son classes for the details
     */
    public abstract void showSoloActionToken(ActionToken actionToken);

}
