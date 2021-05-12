package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.AddedResourceToForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AdvanceFaithTrackForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlayedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class AddResourceToGameMessage extends GameMessage {

    private LeaderWarehouse leaderWarehouse;
    private int place;

    public AddResourceToGameMessage(LeaderWarehouse leaderWarehouse, int place){
        identifier = "ADD_RESOURCE_TO";
        this.leaderWarehouse = leaderWarehouse;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            Resource whichResourceToAdd;
            try {
                whichResourceToAdd = game.getTurn().getCurrentPlayer().whichResourceToAdd().getWhatIAm();
            } catch (NoMarbleToAddFromTheMarketException e) {
                whichResourceToAdd = null;
            }
            if(leaderWarehouse == LeaderWarehouse.DISCARD){
                game.getTurn().addResource(LeaderWarehouse.DISCARD);
                Message.sendMessage(out, new ConfirmedActionMessage());
                System.out.println(identifier);
                forwardAll(game, new AdvanceFaithTrackForwardMessage(currentPlayer));
            } else {
                game.getTurn().addResource(leaderWarehouse, place);
                Message.sendMessage(out, new ConfirmedActionMessage());
                System.out.println(identifier);
                forwardAll(game, new AddedResourceToForwardMessage(currentPlayer, leaderWarehouse, whichResourceToAdd, place));
            }
        } catch (NoResourceToAddException e) {
            Message.sendMessage(out, new NoResourceAErrorMessage());
        } catch (DifferentStorageException e) {
            Message.sendMessage(out, new DifferentStorageTypeErrorMessage());
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new OccupiedSlotLCErrorMessage());
        } catch (PositionAlreadyOccupiedException e) {
            Message.sendMessage(out, new OccupiedSlotWDErrorMessage());
        } catch (ResourceAlreadyPlacedException e) {
            Message.sendMessage(out, new ResourceAlreadyPresentOtherShelfErrorMessage());
        } catch (DifferentResourceInThisShelfException e) {
            Message.sendMessage(out, new DifferentResourceAlreadyPresentErrorMessage());
        } catch (UnexpectedWhiteMarbleException e) {
            Message.sendMessage(out, new WhiteMarbleErrorMessage());
        } catch (UnexpectedFaithMarbleException e) {
            Message.sendMessage(out, new FaithMarbleErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
