package it.polimi.ingsw.PersonalBoard.Faith;
import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

/**
 * Test class for the class: FaithTrack
 */
public class FaithTrackTest {

    /**
     * This method tests the advancement on the faith track
     */
    @Test
    void testGoOn(){
        Game game = new Game(1);
        try {
            game.addPlayer("Andrea");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        Player test = game.getPlayers().get(0);
        test.getPersonalBoard().getFaithTrack().goOn(1);
        assertSame(test.getPersonalBoard().getFaithTrack().getFaithMarker(), 1);
    }

    /**
     * This method tests the advancement on the faith track, the vatican report and if a player discard a marble
     */
    @Test
    void popeStateAndVictoryPointsTest(){
        Game game = new Game(3);
        try {
            game.addPlayer("Carlo");
            game.addPlayer("Andrea");
            game.addPlayer("Realdo");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        game.getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(7);
        game.getPlayers().get(1).getPersonalBoard().getFaithTrack().goOn(7);
        game.getPlayers().get(2).getPersonalBoard().getFaithTrack().goOn(2);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFaithMarker(), 7);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFaithMarker(), 7);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFaithMarker(), 2);
        game.getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(1);
        game.getPlayers().get(1).getPersonalBoard().getFaithTrack().goOn(2);
        game.getPlayers().get(2).getPersonalBoard().getFaithTrack().goOn(17);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFaithMarker(), 8);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFaithMarker(), 9);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFaithMarker(), 19);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.DITCH);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.TURNED);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().victoryPoints(), 4);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().victoryPoints(), 6);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().victoryPoints(), 15);
        game.getPlayers().get(0).getPersonalBoard().getFaithTrack().allOtherPlayersGoOn(game.getPlayers().get(0));
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFaithMarker(), 8);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFaithMarker(), 10);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFaithMarker(), 20);
        game.getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(16);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFaithMarker(), 24);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFaithMarker(), 10);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFaithMarker(), 20);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.DITCH);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(game.getPlayers().get(0).getPersonalBoard().getFaithTrack().victoryPoints(), 26);
        assertSame(game.getPlayers().get(1).getPersonalBoard().getFaithTrack().victoryPoints(), 6);
        assertSame(game.getPlayers().get(2).getPersonalBoard().getFaithTrack().victoryPoints(), 19);
    }

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        Game game = new Game(2);
        try {
            game.addPlayer("Carlo");
            game.addPlayer("Andrea");
        } catch (NameAlreadyRegisteredException | GameAlreadyStartedException e) {
            fail();
        }
        FaithTrack faithTrack = game.getPlayers().get(0).getPersonalBoard().getFaithTrack();
        FavorTiles[] test = game.getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles();
        assertSame(faithTrack.getFavorTiles()[0], FavorTiles.COVERED);
        assertSame(faithTrack.getFavorTiles()[1], FavorTiles.COVERED);
        test[0] = FavorTiles.DITCH;
        assertSame(test[0], FavorTiles.DITCH);
        assertSame(faithTrack.getFavorTiles()[0], FavorTiles.COVERED);
        assertSame(faithTrack.getFavorTiles()[1], FavorTiles.COVERED);
    }

}
