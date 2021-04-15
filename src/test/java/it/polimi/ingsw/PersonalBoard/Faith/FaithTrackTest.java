package it.polimi.ingsw.PersonalBoard.Faith;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Test class for the class: FaithTrack
 */
public class FaithTrackTest {

    /**
     * This method initializes some player in the game
     */
    @BeforeAll
    static void initPlayer(){
        Game game = Game.getInstance();
        game.addPlayer("Carlo");
        game.addPlayer("Andrea");
        game.addPlayer("Realdo");
    }

    /**
     * This method tests the advancement on the faith track
     */
    @Test
    void testGoOn(){
        Player test = new Player("Andrea");
        test.getPersonalBoard().getFaithTrack().goOn(1);
        assertSame(test.getPersonalBoard().getFaithTrack().getFaithMarker(), 1);
    }


    /**
     * This method tests the advancement on the faith track and the vatican report
     */
    @Test
    void popeStateAndVictoryPointsTest(){
        Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(7);
        Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().goOn(7);
        Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().goOn(2);
        Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(1);
        Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().goOn(2);
        Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().goOn(17);
        assertSame(Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[0], FavorTiles.DITCH);
        assertSame(Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[1], FavorTiles.TURNED);
        assertSame(Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().victoryPoints(), 4);
        assertSame(Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().victoryPoints(), 6);
        assertSame(Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().victoryPoints(), 15);
        Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().goOn(16);
        assertSame(Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.DITCH);
        assertSame(Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(Game.getInstance().getPlayers().get(0).getPersonalBoard().getFaithTrack().victoryPoints(), 26);
        assertSame(Game.getInstance().getPlayers().get(1).getPersonalBoard().getFaithTrack().victoryPoints(), 6);
        assertSame(Game.getInstance().getPlayers().get(2).getPersonalBoard().getFaithTrack().victoryPoints(), 19);
    }

    /**
     * This method tests the getter of the class and their return
     */
    @Test
    void integrityTest(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Andrea"));
        players.add(new Player("Carlo"));
        FaithTrack faithTrack = players.get(0).getPersonalBoard().getFaithTrack();
        FavorTiles[] test = players.get(0).getPersonalBoard().getFaithTrack().getFavorTiles();
        assertSame(faithTrack.getFavorTiles()[0], FavorTiles.COVERED);
        assertSame(faithTrack.getFavorTiles()[1], FavorTiles.COVERED);
        test[0] = FavorTiles.DITCH;
        assertSame(test[0], FavorTiles.DITCH);
        assertSame(faithTrack.getFavorTiles()[0], FavorTiles.COVERED);
        assertSame(faithTrack.getFavorTiles()[1], FavorTiles.COVERED);
    }

    /**
     * This method tests the the advancement on the faith track when an unlucky player give the possibility to other players to advance
     */
    @Test
    void advancementOtherPlayers(){
        //<-- FIXME finish me-->
    }

}
