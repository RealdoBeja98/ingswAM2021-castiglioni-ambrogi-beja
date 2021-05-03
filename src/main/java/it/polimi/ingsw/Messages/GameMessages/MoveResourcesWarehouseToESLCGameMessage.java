package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class MoveResourcesWarehouseToESLCGameMessage extends GameMessage {

    private int leaderCardPosition;

    public MoveResourcesWarehouseToESLCGameMessage(int leaderCardPosition){
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(leaderCardPosition);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NotAnExtraStorageLeaderCardException e) {
            out.println(new NotEsErrorMessage());
        } catch (EmptySlotYetException e) {
            out.println(new AlreadyEmptyErrorMessage());
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            out.println(new OccupiedSlotLCErrorMessage());
        } catch (DifferentStorageException e) {
            out.println(new DifferentStorageTypeErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "MOVE_RESOURCES_WAREHOUSE_TO_ES_LC";
    }

}
