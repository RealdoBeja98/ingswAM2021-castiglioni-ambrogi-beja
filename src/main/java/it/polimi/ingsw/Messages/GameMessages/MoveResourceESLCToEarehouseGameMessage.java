package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class MoveResourceESLCToEarehouseGameMessage extends GameMessage {

    private int leaderCardPosition;

    public MoveResourceESLCToEarehouseGameMessage(int leaderCardPosition){
        identifier = "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE";
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (NotAnExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new NotEsErrorMessage());
        } catch (PositionAlreadyOccupiedException e) {
            Message.sendMessage(out, new OccupiedSlotWDErrorMessage());
        } catch (ResourceAlreadyPlacedException e) {
            Message.sendMessage(out, new ResourceAlreadyPresentOtherShelfErrorMessage());
        } catch (DifferentResourceInThisShelfException e) {
            Message.sendMessage(out, new DifferentResourceAlreadyPresentErrorMessage());
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new EmptySlotEsErrorMessage());
        }
    }

}
