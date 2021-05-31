package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithWarehouseDepotsForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateObtainedMultipleResourceForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class PayWithWarehouseDepotsGameMessage extends PayWithSomethingGameMessage {

    private int position;

    /**
     * Constructor of class game message
     * @param position integer
     */
    public PayWithWarehouseDepotsGameMessage(int position){
        identifier = "PAY_WITH_WAREHOUSE_DEPOTS";
        this.position = position;
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
            super.execute(game, out);
            game.getTurn().payWithWarehouseDepots(position);//
            forwardAll(game, new PayedWithWarehouseDepotsForwardMessage(currentPlayer, position));//
        } catch (WrongPaymentException e) {
            Message.sendMessage(out, new WrongResourceErrorMessage());
        } catch (EmptySlotYetException e) {
            Message.sendMessage(out, new AlreadyEmptyErrorMessage());
        } catch (NoResourceToPayException e) {
            Message.sendMessage(out, new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
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
