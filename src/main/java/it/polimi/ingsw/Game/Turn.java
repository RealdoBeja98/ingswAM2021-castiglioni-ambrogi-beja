package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.InWhichStatePlayer;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoLeaderCardToDiscardException;

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

    public void choosePlayLeaderCard() throws ActionNotAllowedException, NoLeaderCardToDiscardException {
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
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD1;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD2;
        }
    }

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

}
