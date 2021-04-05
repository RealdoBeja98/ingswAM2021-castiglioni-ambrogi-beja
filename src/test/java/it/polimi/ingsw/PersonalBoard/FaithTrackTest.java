package it.polimi.ingsw.PersonalBoard;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.Enums.FavorTiles;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class FaithTrackTest {

    /**
     * We are testing moving on the faith track
     */
    @Test
    public void testGoOn(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Carlo"));
        FaithTrack faithTrack = players.get(0).getPersonalBoard().getFaithTrack();
        faithTrack.goOn(1);
        assertSame(faithTrack.getFaithMarker(), 1);
    }

    /**
     * We are testing pope state and victory points
     */
    @Test//test da seguire soltanto una volta?
    public void popeStateAndVictoryPointsTest(){
        ArrayList<Player> backupPlayers = Game.getInstance().players;
        Game.getInstance().players = new ArrayList<Player>();
        ArrayList<Player> players = Game.getInstance().players;
        players.add(new Player("Carlo"));
        players.add(new Player("Andrea"));
        players.add(new Player("Realdo"));
        FaithTrack faithTrackCarlo = players.get(0).getPersonalBoard().getFaithTrack();
        faithTrackCarlo.goOn(3);
        FaithTrack faithTrackAndrea = players.get(1).getPersonalBoard().getFaithTrack();
        faithTrackAndrea.goOn(6);
        FaithTrack faithTrackRealdo = players.get(2).getPersonalBoard().getFaithTrack();
        faithTrackRealdo.goOn(8);
        assertSame(faithTrackCarlo.getFavorTiles()[0], FavorTiles.DITCH);
        assertSame(faithTrackAndrea.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackRealdo.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackCarlo.victoryPoints(), 1);
        assertSame(faithTrackAndrea.victoryPoints(), 4);
        assertSame(faithTrackRealdo.victoryPoints(), 4);
        Game.getInstance().players = backupPlayers;
    }

    /**
     * We are testing again pope state and victory points
     */
    @Test
    public void popeStateAndVictoryPointsTest2(){
        ArrayList<Player> backupPlayers = Game.getInstance().players;
        Game.getInstance().players = new ArrayList<Player>();
        ArrayList<Player> players = Game.getInstance().players;
        players.add(new Player("Carlo"));
        players.add(new Player("Andrea"));
        players.add(new Player("Realdo"));
        FaithTrack faithTrackCarlo = players.get(0).getPersonalBoard().getFaithTrack();
        FaithTrack faithTrackAndrea = players.get(1).getPersonalBoard().getFaithTrack();
        FaithTrack faithTrackRealdo = players.get(2).getPersonalBoard().getFaithTrack();
        faithTrackCarlo.goOn(7);
        faithTrackAndrea.goOn(7);
        faithTrackRealdo.goOn(2);
        faithTrackCarlo.goOn(1);
        faithTrackAndrea.goOn(2);
        faithTrackRealdo.goOn(17);
        assertSame(faithTrackCarlo.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackAndrea.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackRealdo.getFavorTiles()[0], FavorTiles.DITCH);
        assertSame(faithTrackCarlo.getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(faithTrackAndrea.getFavorTiles()[1], FavorTiles.DITCH);
        assertSame(faithTrackRealdo.getFavorTiles()[1], FavorTiles.TURNED);
        assertSame(faithTrackCarlo.victoryPoints(), 4);
        assertSame(faithTrackAndrea.victoryPoints(), 6);
        assertSame(faithTrackRealdo.victoryPoints(), 15);
        faithTrackCarlo.goOn(16);
        assertSame(faithTrackCarlo.getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(faithTrackAndrea.getFavorTiles()[2], FavorTiles.DITCH);
        assertSame(faithTrackRealdo.getFavorTiles()[2], FavorTiles.TURNED);
        assertSame(faithTrackCarlo.victoryPoints(), 26);
        assertSame(faithTrackAndrea.victoryPoints(), 6);
        assertSame(faithTrackRealdo.victoryPoints(), 19);
        Game.getInstance().players = backupPlayers;
    }

}
