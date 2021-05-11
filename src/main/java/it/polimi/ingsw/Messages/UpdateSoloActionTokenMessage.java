package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;

import java.io.PrintWriter;

public class UpdateSoloActionTokenMessage extends Message{

    private ActionToken actionToken;

    public UpdateSoloActionTokenMessage(ActionToken actionToken){
        identifier = "UPDATE_SOLO_ACTION_TOKEN";
        this.actionToken = actionToken;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        switch (actionToken.getWhatIAm()){
            case GREEN:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                break;
            case BLUE:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                break;
            case YELLOW:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                break;
            case PURPLE:
                ClientMain.getPlayerGame().discardDevelopmentDeck(actionToken.getWhatIAm());
                break;
            case BLACKCROSS1:
                ClientMain.getPlayerGame().loernzoGoOn();
                break;
            case BLACKCROSS2:
                ClientMain.getPlayerGame().loernzoGoOn();
                ClientMain.getPlayerGame().loernzoGoOn();
                break;
        }
    }

    @Override
    public String toString(){
        return "UPDATE_SOLO_ACTION_TOKEN" + " "+actionToken;
    }

}
