package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of the update solo action
 */
public class UpdateSoloActionTokenMessage extends Message{

    private ActionToken actionToken;

    /**
     * Constructor of the class
     * @param actionToken token
     */
    public UpdateSoloActionTokenMessage(ActionToken actionToken){
        identifier = "UPDATE_SOLO_ACTION_TOKEN";
        this.actionToken = actionToken;
    }

    /**
     * This method is for updating in solo player, and sending the proper messages from
     * the moves of the player
     * @param game
     * @param out
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        System.out.println(this);
        switch (actionToken.getWhatIAm()){
            case GREEN:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                View w1 = View.get();
                w1.showDevCard();
                break;
            case BLUE:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                View w2 = View.get();
                w2.showDevCard();
                break;
            case YELLOW:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                View w3 = View.get();
                w3.showDevCard();
                break;
            case PURPLE:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                View w4 = View.get();
                w4.showDevCard();
                break;
            case BLACKCROSS1:
                ClientMain.getPlayerGame().loernzoGoOn();
                View w5 = View.get();
                w5.showPBCurrent();
                break;
            case BLACKCROSS2:
                ClientMain.getPlayerGame().loernzoGoOn();
                ClientMain.getPlayerGame().loernzoGoOn();
                View w6 = View.get();
                w6.showPBCurrent();
                break;
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "UPDATE_SOLO_ACTION_TOKEN" + " "+actionToken;
    }

}
