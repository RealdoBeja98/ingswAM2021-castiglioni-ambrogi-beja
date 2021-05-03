package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class AddResourceToGameMessage extends GameMessage {

    private LeaderWarehouse leaderWarehouse;
    private int place;

    public AddResourceToGameMessage(LeaderWarehouse leaderWarehouse, int place){
        this.leaderWarehouse = leaderWarehouse;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().addResource(leaderWarehouse, place);
            if(leaderWarehouse == LeaderWarehouse.DISCARD){
                out.println(new ConfirmedActionMessage());
                System.out.println(this);
                forward(game, "ADVANCE_FAITH_TRACK", out);
            } else {
                out.println(new ConfirmedActionMessage());
                System.out.println(this);
            }
        } catch (NoResourceToAddException e) {
            out.println(new NoResourceAErrorMessage());
        } catch (DifferentStorageException e) {
            out.println(new DifferentStorageTypeErrorMessage());
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            out.println(new OccupiedSlotLCErrorMessage());
        } catch (PositionAlreadyOccupiedException e) {
            out.println(new OccupiedSlotWDErrorMessage());
        } catch (ResourceAlreadyPlacedException e) {
            out.println(new ResourceAlreadyPresentOtherShelfErrorMessage());
        } catch (DifferentResourceInThisShelfException e) {
            out.println(new DifferentResourceAlreadyPresentErrorMessage());
        } catch (UnexpectedWhiteMarbleException e) {
            out.println(new WhiteMarbleErrorMessage());
        } catch (UnexpectedFaithMarbleException e) {
            out.println(new FaithMarbleErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "ADD_RESOURCE_TO";
    }

}
