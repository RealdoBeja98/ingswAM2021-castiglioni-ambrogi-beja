package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class MoveResourceESLCToEarehouseGameMessage extends GameMessage {

    private int leaderCardPosition;

    public MoveResourceESLCToEarehouseGameMessage(int leaderCardPosition){
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(leaderCardPosition);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NotAnExtraStorageLeaderCardException e) {
            out.println(new NotEsErrorMessage());
        } catch (PositionAlreadyOccupiedException e) {
            out.println(new OccupiedSlotWDErrorMessage());
        } catch (ResourceAlreadyPlacedException e) {
            out.println(new ResourceAlreadyPresentOtherShelfErrorMessage());
        } catch (DifferentResourceInThisShelfException e) {
            out.println(new DifferentResourceAlreadyPresentErrorMessage());
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            out.println(new EmptySlotEsErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE";
    }

}
