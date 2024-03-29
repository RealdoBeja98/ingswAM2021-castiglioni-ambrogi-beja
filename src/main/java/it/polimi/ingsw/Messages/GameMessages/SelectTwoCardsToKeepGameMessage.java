package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.MessageException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.LocalMain;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.SelectedTwoCardsToKeepForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class SelectTwoCardsToKeepGameMessage extends GameMessage {

    private int card1;
    private int card2;

    /**
     * Constructor of class game message
     * @param card2: card2
     * @param card1:  card1
     */
    public SelectTwoCardsToKeepGameMessage(int card1, int card2){
        identifier = "SELECT_TWO_CARDS_TO_KEEP";
        this.card1 = card1;
        this.card2 = card2;
    }

    /**
     * This method represents the sending of a  correct message
     * @param game: game instance
     * @param out: sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            forwardAll(game, new SelectedTwoCardsToKeepForwardMessage(currentPlayer, card1, card2));
            game.getTurn().selectThoCardsToKeep(card1, card2);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            if(LocalMain.getIsLocal()){
                View.get().showMarket();
                View.get().showDevCard();
                View.get().showPersonalBoard();
            }
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + card1 + " " + card2;
    }

}
