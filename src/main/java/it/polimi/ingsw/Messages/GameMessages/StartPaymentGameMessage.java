package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class StartPaymentGameMessage extends GameMessage {

    /**
     * Constructor of class game message
     */
    public StartPaymentGameMessage(){
        identifier = "START_PAYMENT";
    }

    /**
     * This method is to send to the player a message with the next resources to pay
     * @param game: game instance
     * @param out: sends message to socket
     */
    private void sendNextResourceToPay(Game game, PrintWriter out){
        if(game.getTurn().getCurrentPlayer().somethingToPay()){
            try {
                Message.printOut(out, "Next to pay: " + game.getTurn().getCurrentPlayer().nextToPay().toString());
            } catch (NoResourceToPayException e) {
                e.printStackTrace();
            }
        } else if(game.getTurn().getCurrentPlayer().genericResourcesToObtain()){
            Message.printOut(out, "Generic resource to obtain");
        }
    }

    /**
     * This method represents the sending of a  correct message
     * @param game: game instance
     * @param out: sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().startPayment();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            sendNextResourceToPay(game, out);
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

}
