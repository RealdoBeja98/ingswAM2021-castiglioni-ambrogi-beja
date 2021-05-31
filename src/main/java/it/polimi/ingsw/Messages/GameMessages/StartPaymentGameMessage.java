package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.Exceptions.YouHaveNotSelectedAnyProductionException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoSelectedPowersErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughRErrorMessage;
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
        } catch (NotEnoughResourcesException e) {
            Message.sendMessage(out, new NotEnoughRErrorMessage());
        } catch (YouHaveNotSelectedAnyProductionException e) {
            Message.sendMessage(out, new NoSelectedPowersErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        }
    }

}
