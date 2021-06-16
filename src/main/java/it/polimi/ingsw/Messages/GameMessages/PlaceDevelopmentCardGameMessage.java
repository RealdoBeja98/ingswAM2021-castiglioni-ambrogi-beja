package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlacedDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class PlaceDevelopmentCardGameMessage extends GameMessage {

    private int position;

    /**
     * Constructor of class game message
     * @param position integer
     */
    public PlaceDevelopmentCardGameMessage(int position){
        identifier = "PLACE_DEVELOPMENT_CARD";
        this.position = position;
    }

    private void sendNextResourceToPay(Game game, PrintWriter out){
        if(game.getTurn().getCurrentPlayer().somethingToPay()){
            try {
                out.println("Next to pay: " + game.getTurn().getCurrentPlayer().nextToPay().toString());
            } catch (NoResourceToPayException e) {
                e.printStackTrace();
            }
        } else if(game.getTurn().getCurrentPlayer().genericResourcesToObtain()){
            out.println("Generic resource to obtain");
        }
    }

    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            DevelopmentCard obtainedDevelopmentCard = game.getTurn().getCurrentPlayer().getObtainedDevelopmentCard();
            game.getTurn().placeDevelopmentCard(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new PlacedDevelopmentCardForwardMessage(currentPlayer, position, obtainedDevelopmentCard.export()));
            sendNextResourceToPay(game, out);
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + position;
    }

}
