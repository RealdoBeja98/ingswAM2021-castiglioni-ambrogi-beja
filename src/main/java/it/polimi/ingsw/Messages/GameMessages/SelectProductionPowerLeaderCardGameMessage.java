package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoProductionLeaderCardException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoPLCErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class SelectProductionPowerLeaderCardGameMessage extends GameMessage {

    private int leaderCardPosition;

    public SelectProductionPowerLeaderCardGameMessage(int leaderCardPosition){
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectProductionPowerLeaderCard(leaderCardPosition);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NoProductionLeaderCardException e) {
            out.println(new NoPLCErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "SELECT_PRODUCTION_POWER_LEADER_CARD";
    }

}
