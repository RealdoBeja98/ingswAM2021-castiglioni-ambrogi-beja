package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoDevelopmentCardInThisPositionException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoDevelopmentCardErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class SelectProductionDevelopmentCardGameMessage extends GameMessage {

    private int position;

    /**
     * Constructor of class game message
     * @param position position
     */
    public SelectProductionDevelopmentCardGameMessage(int position){
        identifier = "SELECT_PRODUCTION_DEVELOPMENT_CARD";
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
            game.getTurn().selectProductionDevelopmentCard(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (NoDevelopmentCardInThisPositionException e) {
            Message.sendMessage(out, new NoDevelopmentCardErrorMessage());
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
