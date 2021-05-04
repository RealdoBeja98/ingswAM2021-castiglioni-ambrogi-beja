package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.AdvanceFaithTrackForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

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
            if(leaderWarehouse == LeaderWarehouse.DISCARD){
                game.getTurn().addResource(LeaderWarehouse.DISCARD);
                Message.sendMessage(out, new ConfirmedActionMessage());
                System.out.println(identifier);
                forward(game, new AdvanceFaithTrackForwardMessage(), out);
            } else {
                game.getTurn().addResource(leaderWarehouse, place);
                Message.sendMessage(out, new ConfirmedActionMessage());
                System.out.println(identifier);
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
