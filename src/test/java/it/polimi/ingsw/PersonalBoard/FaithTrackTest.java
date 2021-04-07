package it.polimi.ingsw.PersonalBoard;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class FaithTrackTest {

    @BeforeAll
    public static void initPlayer(){
        Game game = Game.getInstance();
        game.addPlayer("Carlo");
        game.addPlayer("Andrea");
        game.addPlayer("Realdo");
    }

    /**
     * We are testing moving on the faith track
     */
    @Test
    public void testGoOn(){
        Player test = new Player("Andrea");
        test.getPersonalBoard().getFaithTrack().goOn(1);
        assertSame(test.getPersonalBoard().getFaithTrack().getFaithMarker(), 1);
    }


    /**
     * We are testing again pope state and victory points
     */
    @Test
    public void popeStateAndVictoryPointsTest(){
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

    @Test
    public void integrityTest(){
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
}
