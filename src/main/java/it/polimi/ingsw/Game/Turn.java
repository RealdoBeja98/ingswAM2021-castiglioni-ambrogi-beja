package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.InWhichStatePlayer;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;

public class Turn {
    private Player currentPlayer;
    private boolean actionLeaderDone = false;
    private InWhichStatePlayer inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;

    public Turn(){
        for(Player i : Game.getInstance().getPlayers()){
            if(i.isInkwell()){
                currentPlayer = i;
            }
        }
    }

    public void chooseDiscardLeaderCard() throws NoLeaderCardToDiscardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone == true){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.countLeaderCardInHand() == 0){
            throw new NoLeaderCardToDiscardException();
        }
        actionLeaderDone = true;
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            inWhichStatePlayer = InWhichStatePlayer.DISCARD_LEADER_CARD1;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            inWhichStatePlayer = InWhichStatePlayer.DISCARD_LEADER_CARD2;
        }
    }

    public void choosePlayLeaderCard() throws ActionNotAllowedException, NoLeaderCardToPlayException {
        if(inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone == true){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.canYouPlayAtLeastALeaderCard() == false){
            throw new NoLeaderCardToPlayException();
        }
        actionLeaderDone = true;
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD1;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD2;
        }
    }

    /*
    public void tapReturn() throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET &&
                inWhichStatePlayer != InWhichStatePlayer.BUY_DEVELOPMENT_CARD &&
                inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
    }
     */

    public void discardLeaderCard(int pos) throws YetDiscardedThisLeaderCardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.DISCARD_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.DISCARD_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.discardLeaderCard(pos);
        if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD1){
            inWhichStatePlayer = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD2){
            if(Game.getInstance().getPlayers().size() == 1){
                inWhichStatePlayer = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                endTurn();
            }
        }
    }

    public void playLeaderCard(int pos) throws NotSatisfiedRequirementsForThisLeaderCardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.playLeaderCard(pos);
        if(currentPlayer.somethingToPay() == false){
            if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD1){
                inWhichStatePlayer = InWhichStatePlayer.SELECT_NORMAL_ACTION;
            }
            if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD2){
                if(Game.getInstance().getPlayers().size() == 1){
                    inWhichStatePlayer = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
                } else {
                    endTurn();
                }
            }
        }
    }

    public void selectNormalAction(NormalAction selectedNormalAction) throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.SELECT_NORMAL_ACTION){
            throw new ActionNotAllowedException();
        }
        switch (selectedNormalAction){
            case TAKE_RESOURCES_FROM_THE_MARKET:
                inWhichStatePlayer = InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET;
            case BUY_DEVELOPMENT_CARD:
                if(currentPlayer.canYouBuyALeaderCard()){
                    inWhichStatePlayer = InWhichStatePlayer.BUY_DEVELOPMENT_CARD;
                } else {
                    throw new ActionNotAllowedException();
                }
            case ACTIVATE_PRODUCTION:
                inWhichStatePlayer = InWhichStatePlayer.ACTIVATE_PRODUCTION;
            default: break;
        }
    }

    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos) throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.takeResourcesFromTheMarket(rowColumn, pos);
    }

    public void addResource(LeaderWarehouse where, int pos) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.addResource(where, pos);
        if(currentPlayer.resourceToAdd() == false){
            inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    public void changeWhiteMarbleWith(int pos) throws ClassCastException, NoWhiteMarbleException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.changeWhiteMarbleWith(pos);
    }



    private void endTurn(){
        actionLeaderDone = false;
        inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        boolean found = false;
        for(Player i : Game.getInstance().getPlayers()){
            if(found == true){
                currentPlayer = i;
                return;
            }
            if(i == currentPlayer){
                found = true;
            }
        }
        currentPlayer = Game.getInstance().getPlayers().get(0);
    }

}
