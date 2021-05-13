package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourcesWarehouseToESLCForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class MoveResourcesWarehouseToESLCGameMessage extends GameMessage {

    private int leaderCardPosition;

    public MoveResourcesWarehouseToESLCGameMessage(int leaderCardPosition){
        identifier = "MOVE_RESOURCES_WAREHOUSE_TO_ES_LC";
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            int warehousePosition = game.getTurn().getCurrentPlayer().getSelectedWarehouseDepotsSlot();
            game.getTurn().moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new MovedResourcesWarehouseToESLCForwardMessage(currentPlayer, leaderCardPosition, warehousePosition));
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (NotAnExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new NotEsErrorMessage());
        } catch (EmptySlotYetException e) {
            Message.sendMessage(out, new AlreadyEmptyErrorMessage());
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new OccupiedSlotLCErrorMessage());
        } catch (DifferentStorageException e) {
            Message.sendMessage(out, new DifferentStorageTypeErrorMessage());
        }
    }

}
