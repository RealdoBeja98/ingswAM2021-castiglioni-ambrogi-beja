package it.polimi.ingsw.Messages.GameMessages;

import com.sun.rowset.internal.Row;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NullEnumException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidEnumErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class TakeResourcesFromTheMarketGameMessage extends GameMessage {

    private RowColumn rowColumn;
    private int place;

    public TakeResourcesFromTheMarketGameMessage(RowColumn rowColumn, int place){
        this.rowColumn = rowColumn;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().takeResourcesFromTheMarket(rowColumn, place);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
            int size = game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().size();
            for (int i = 0; i < size; i++) {
                out.println(game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().get(i));
            }
            //forward("UPDATE_MARKET " + rowColumn + " " + String.valueOf(place), out);<--FIXME forward message-->
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NullEnumException e) {
            out.println(new InvalidEnumErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "TAKE_RESOURCES_FROM_THE_MARKET";
    }

}
