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

import java.io.PrintWriter;

public class SelectProductionDevelopmentCardGameMessage extends GameMessage {

    private int position;

    public SelectProductionDevelopmentCardGameMessage(int position){
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectProductionDevelopmentCard(position);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NoDevelopmentCardInThisPositionException e) {
            out.println(new NoDevelopmentCardErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "SELECT_PRODUCTION_DEVELOPMENT_CARD";
    }

}
