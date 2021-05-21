package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourceESLCToWarehouseForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class MoveResourceESLCToWEarehouseGameMessage extends GameMessage {

    private int leaderCardPosition;

    public MoveResourceESLCToWEarehouseGameMessage(int leaderCardPosition){
        identifier = "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE";
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            int warehousePosition = game.getTurn().getCurrentPlayer().getSelectedWarehouseDepotsSlot();
            game.getTurn().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new MovedResourceESLCToWarehouseForwardMessage(currentPlayer, leaderCardPosition, warehousePosition));
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
        } catch (IndexOutOfWarehouseDepotsException e){
            Message.sendMessage(out, new InvalidMovementErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }
    @Override
    public String toString(){
        return identifier + " " + leaderCardPosition;
    }
}
