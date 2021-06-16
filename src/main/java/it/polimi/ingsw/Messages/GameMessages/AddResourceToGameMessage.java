package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AddedResourceToForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AdvanceFaithTrackForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class AddResourceToGameMessage extends GameMessage {

    private LeaderWarehouse leaderWarehouse;
    private int place;

    /**
     * Constructor of class game message
     * @param leaderWarehouse instance of leader ware house
     * @param place position
     */
    public AddResourceToGameMessage(LeaderWarehouse leaderWarehouse, int place){
        identifier = "ADD_RESOURCE_TO";
        this.leaderWarehouse = leaderWarehouse;
        this.place = place;
    }

    /**
     * This method is to send to the player a message with the next resources from the market
     * @param game: game instance
     * @param out: sends message to socket
     */
    private void sendNextMarbleFromTheMarket(Game game, PrintWriter out){
        int size = game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().size();
        if(size >= 1){
            out.println("Marble from the market to add: " + game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().get(0));
        }
    }

    /**
     * This method is the second part of the method execute
     * @param game game instance
     * @param out sends message to socket
     * @param currentPlayer the nickname of the current player
     * @param whichResourceToAdd the resource to add
     * @throws OccupiedSlotExtraStorageLeaderCardException if you select an ExtraStorageLeaderCard yet occupied
     * @throws PositionAlreadyOccupiedException if you select a position yet occupied of the WarehouseDepots
     * @throws UnexpectedFaithMarbleException if it's unexpectly found you are going to place a white marble
     * @throws ActionNotAllowedException
     * @throws NoResourceToAddException if the list marblesFromTheMarket, in witch there are all the resources to add, is empty
     * @throws DifferentStorageException if you select an ExtraStorageLeaderCard of another type of the resource to add
     * @throws ResourceAlreadyPlacedException if you place in WarehouseDepots a type of resource you yet placed in another shelf
     * @throws DifferentResourceInThisShelfException if you place in WarehouseDepots the resource in a shelf where there is yet another resource of another type
     * @throws UnexpectedWhiteMarbleException if it's unexpectly found you are going to place a faith marble
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     */
    private void continueExecute(Game game, PrintWriter out, String currentPlayer, Resource whichResourceToAdd) throws OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, UnexpectedFaithMarbleException, ActionNotAllowedException, NoResourceToAddException, DifferentStorageException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, IndexOutOfWarehouseDepotsException {
        Message.sendMessage(out, new ConfirmedActionMessage());
        System.out.println(identifier);
        switch (leaderWarehouse){
            case DISCARD:
                forwardAll(game, new AdvanceFaithTrackForwardMessage(currentPlayer));
                break;
            default: game.getTurn().addResource(leaderWarehouse, place);
                forwardAll(game, new AddedResourceToForwardMessage(currentPlayer, leaderWarehouse, whichResourceToAdd, place));
                break;
        }
        sendNextMarbleFromTheMarket(game, out);
    }

    /**
     * This method represents the sending of a correct message
     * @param game game instance
     * @param out sends message to socket
     */
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
            }
            continueExecute(game, out, currentPlayer, whichResourceToAdd);
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        if(leaderWarehouse == LeaderWarehouse.DISCARD){
            return identifier + " " + leaderWarehouse;
        } else {
            return identifier + " " + leaderWarehouse + " " + place;
        }
    }

}
