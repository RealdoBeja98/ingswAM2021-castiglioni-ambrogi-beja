package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.MessageException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.LocalMain;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.UpdateSoloActionTokenMessage;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import it.polimi.ingsw.View.Gui;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class DrawSoloActionTokenGameMessage extends GameMessage {

    /**
     * Constructor of class game message
     */
    public DrawSoloActionTokenGameMessage(){
        identifier = "DRAW_SOLO_ACTION_TOKEN";
    }

    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            ActionToken actionToken = game.getTurn().drawSoloActionToken();
            Message.printOutUpdateSoloActionToken(out, new UpdateSoloActionTokenMessage(actionToken));
            /*
            if(LocalMain.getIsLocal() && View.get() instanceof Gui){
                View w = View.get();
                w.showSoloActionToken(actionToken);
                w.showDevCard();
                w.showPBCurrent();
            }
            */
            System.out.println(identifier);
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

}
