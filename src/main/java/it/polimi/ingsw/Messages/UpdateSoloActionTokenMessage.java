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
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        View w = View.get();
        w.showSoloActionToken(actionToken);
        switch (actionToken.getWhatIAm()){
            case GREEN:
            case BLUE:
            case YELLOW:
            case PURPLE:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                w.showDevCard();
                break;
            case BLACKCROSS2:
                ClientMain.getPlayerGame().lorenzoGoOn();
            case BLACKCROSS1:
                ClientMain.getPlayerGame().lorenzoGoOn();
                w.showPBCurrent();
                break;
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "UPDATE_SOLO_ACTION_TOKEN" + " "+ actionToken;
    }

}
