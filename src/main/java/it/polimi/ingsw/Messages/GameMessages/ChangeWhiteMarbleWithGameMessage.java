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

import java.io.PrintWriter;

public class ChangeWhiteMarbleWithGameMessage extends GameMessage {

    private int leaderCardPosition;

    public ChangeWhiteMarbleWithGameMessage(int leaderCardPosition){
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().changeWhiteMarbleWith(leaderCardPosition);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NoWhiteMarbleException e) {
            out.println(new NoWhiteMarbleErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NoWhiteMarbleLeaderCardException e) {
            out.println(new NoWhiteMarbleErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "CHANGE_WHITE_MARBLE_WITH";
    }

}
