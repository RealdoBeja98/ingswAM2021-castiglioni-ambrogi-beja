package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class SelectAWarehouseDepotsSlotGameMessage extends GameMessage {

    private int position;

    public SelectAWarehouseDepotsSlotGameMessage(int position){
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectAWarehouseDepotsSlot(position);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "SELECT_A_WAREHOUSE_DEPOTS_SLOT";
    }

}
