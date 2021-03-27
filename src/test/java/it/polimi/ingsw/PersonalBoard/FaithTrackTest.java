package it.polimi.ingsw.PersonalBoard;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Faith.FavorTiles;
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
    @Test
    public void popeStateAndVictoryPointsTest(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Carlo"));
        players.add(new Player("Andrea"));
        players.add(new Player("Realdo"));
        FaithTrack faithTrackCarlo = players.get(0).getPersonalBoard().getFaithTrack();
        faithTrackCarlo.goOn(3);
        FaithTrack faithTrackAndrea = players.get(1).getPersonalBoard().getFaithTrack();
        faithTrackAndrea.goOn(6);
        FaithTrack faithTrackRealdo = players.get(2).getPersonalBoard().getFaithTrack();
        faithTrackRealdo.goOn(8);
        System.out.println(faithTrackCarlo.getFavorTiles()[1].toString());
        assertSame(faithTrackCarlo.getFavorTiles()[0], FavorTiles.DITCH);
        assertSame(faithTrackAndrea.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackRealdo.getFavorTiles()[0], FavorTiles.TURNED);
        assertSame(faithTrackCarlo.victoryPoints(), 1);
        assertSame(faithTrackAndrea.victoryPoints(), 4);
        assertSame(faithTrackRealdo.victoryPoints(), 4);
    }

}
