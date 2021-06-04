package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the class: Turn
 */
public class TurnTest {

    /**
     * Creates a game with 4 players
     */
    private int crateGame(){
        Game game = new Game(4);
        try {
            game.addPlayer("a");
            game.addPlayer("b");
            game.addPlayer("c");
            game.addPlayer("d");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            e.printStackTrace();
        }
        return game.getGameIndex();
    }

    /**
     * Creates a solo player game
     */
    private int crateSoloGame(){
        Game game = new Game(1);
        try {
            game.addPlayer("a");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            e.printStackTrace();
        }
        return game.getGameIndex();
    }

    private void doFirstTurn(Turn turn) throws GameEndedException, PositionInvalidException, ActionNotAllowedException {
        for(int i = 0; i < 4; i++){
            turn.selectThoCardsToKeep(1, 2);
        }
    }

    /**
     * This method tests a normal turn of a player
     */
    @Test
    void exampleTurnTest(){
        try{
            Turn turn = new Turn(crateGame());
            doFirstTurn(turn);
            turn.chooseNoActionLeaderCard();
            turn.selectNormalAction(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET);
            turn.takeResourcesFromTheMarket(RowColumn.ROW, 1);
            try{
                for(int i = 0; i < 4; i++){
                    turn.addResource(LeaderWarehouse.DISCARD);
                }
            } catch (ActionNotAllowedException e){}
            turn.chooseNoActionLeaderCard();
            turn.endTurn();
        } catch (Exception e){
            fail();
        }
    }

    /**
     * This method tests a turn in witch you do an action not allowed
     */
    @Test
    void actionNotAllowedTest(){
        try{
            Turn turn = new Turn(crateGame());
            doFirstTurn(turn);
            turn.chooseNoActionLeaderCard();
            assertThrows(ActionNotAllowedException.class, () -> turn.chooseDiscardLeaderCard());
        } catch (Exception e){
            fail();
        }
    }

    /**
     * This method tests discarding a leader card
     */
    @Test
    void discardLeaderCardTest(){
        try{
            Turn turn = new Turn(crateGame());
            doFirstTurn(turn);
            turn.chooseDiscardLeaderCard();
            turn.discardLeaderCard(1);
            turn.chooseNoActionLeaderCard();
            turn.selectNormalAction(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET);
            turn.takeResourcesFromTheMarket(RowColumn.ROW, 1);
            try{
                for(int i = 0; i < 4; i++){
                    turn.addResource(LeaderWarehouse.DISCARD);
                }
            } catch (ActionNotAllowedException e){}
            assertThrows(ActionNotAllowedException.class, () -> turn.chooseDiscardLeaderCard());
            turn.chooseNoActionLeaderCard();
            turn.endTurn();
        } catch (Exception e){
            fail();
        }
    }

    /**
     * This method tests discarding a leader card
     */
    @Test
    void discardLeaderCard2Test(){
        try{
            Turn turn = new Turn(crateGame());
            doFirstTurn(turn);
            turn.chooseDiscardLeaderCard();
            turn.discardLeaderCard(2);
            turn.chooseNoActionLeaderCard();
            turn.selectNormalAction(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET);
            turn.takeResourcesFromTheMarket(RowColumn.ROW, 1);
            try{
                for(int i = 0; i < 4; i++){
                    turn.addResource(LeaderWarehouse.DISCARD);
                }
            } catch (ActionNotAllowedException e){}
            assertThrows(ActionNotAllowedException.class, () -> turn.chooseDiscardLeaderCard());
            turn.chooseNoActionLeaderCard();
            turn.endTurn();
        } catch (Exception e){
            fail();
        }
    }

    /**
     * This method tests drawing an action token in a single player
     */
    @Test
    void drawSoloActionTokenTest(){
        try{
            Turn turn = new Turn(crateSoloGame());
            turn.selectThoCardsToKeep(1,2);
            turn.chooseDiscardLeaderCard();
            turn.discardLeaderCard(1);
            turn.chooseNoActionLeaderCard();
            turn.selectNormalAction(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET);
            turn.takeResourcesFromTheMarket(RowColumn.ROW, 1);
            try{
                for(int i = 0; i < 4; i++){
                    turn.addResource(LeaderWarehouse.DISCARD);
                }
            } catch (ActionNotAllowedException e){}
            assertThrows(ActionNotAllowedException.class, () -> turn.chooseDiscardLeaderCard());
            turn.chooseNoActionLeaderCard();
            turn.drawSoloActionToken();
            turn.endTurn();
        } catch (Exception e){
            fail();
        }
    }

}
