package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoWhiteMarbleException;
import it.polimi.ingsw.Exceptions.NoWhiteMarbleLeaderCardException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoWhiteMarbleErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ChangeWhiteMarbleWithGameMessage extends GameMessage {

    private int leaderCardPosition;

    public ChangeWhiteMarbleWithGameMessage(int leaderCardPosition){
        identifier = "CHANGE_WHITE_MARBLE_WITH";
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().changeWhiteMarbleWith(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (NoWhiteMarbleException e) {
            Message.sendMessage(out, new NoWhiteMarbleErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (NoWhiteMarbleLeaderCardException e) {
            Message.sendMessage(out, new NoWhiteMarbleErrorMessage());
        }
    }

}
