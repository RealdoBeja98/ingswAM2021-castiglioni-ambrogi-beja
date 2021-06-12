package it.polimi.ingsw.PersonalBoard.Faith;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.PlayerGame;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * This Class represents the faith track associate to a player
 */
public class FaithTrack {

    private final static int[] popstate = {8, 16, 24};
    private final static int[] thresholds = {5, 12, 19};
    private final static int[] bonusFavourTiles = {2, 3, 4};
    private static final int unusefulGameIndex = -1;
    private int faithMarker;
    private FavorTiles[] favorTiles;
    protected final int gameIndex;
    private static ArrayList<Pair<Integer, Integer>> bonusFaithMarker = null;

    /**
     * This method is to initialize bonusFaithMarker
     */
    private static void initializeBonusFaithMarker(){
        if(bonusFaithMarker != null){
            return;
        }
        bonusFaithMarker = new ArrayList<>();
        bonusFaithMarker.add(new Pair<>(24, 20));
        bonusFaithMarker.add(new Pair<>(21, 16));
        bonusFaithMarker.add(new Pair<>(18, 12));
        bonusFaithMarker.add(new Pair<>(15, 9));
        bonusFaithMarker.add(new Pair<>(12, 6));
        bonusFaithMarker.add(new Pair<>(9, 4));
        bonusFaithMarker.add(new Pair<>(6, 2));
        bonusFaithMarker.add(new Pair<>(3, 1));
    }

    /**
     * Constructor method of this class
     */
    public FaithTrack(int gameIndex){
        FaithTrack.initializeBonusFaithMarker();
        this.gameIndex = gameIndex;
        faithMarker = 0;
        favorTiles = new FavorTiles[3];
        for(int i = 0; i < 3; i++){
            favorTiles[i] = FavorTiles.COVERED;
        }
    }

    /**
     * Constructor method of this class without gameIndex
     */
    public FaithTrack(){
        FaithTrack.initializeBonusFaithMarker();
        this.gameIndex = unusefulGameIndex;
        faithMarker = 0;
        favorTiles = new FavorTiles[3];
        for(int i = 0; i < 3; i++){
            favorTiles[i] = FavorTiles.COVERED;
        }
    }

    /**
     * Getter of the parameter faithMarker
     * @return the faithMarker, of type int
     */
    public int getFaithMarker(){
        return faithMarker;
    }

    /**
     * Getter of the parameter favorTiles
     * @return a copy of the original array favorTiles[], of type FavorTiles[]
     */
    public FavorTiles[] getFavorTiles(){
        FaithTrack copy = copy();
        return copy.favorTiles;
    }

    /**
     * This method returns the list of faith track of the current players
     * @return a list of faithTrack, of type ArrayList of FaithTrack
     */
    private ArrayList<FaithTrack> faithTrackOfAllPlayers(){
        if(gameIndex == unusefulGameIndex){
            return PlayerGame.getAllFaithTrack();
        }
        ArrayList<FaithTrack> result = new ArrayList<>();
        ArrayList<Player> playerList = Game.get(gameIndex).getPlayers();
        if(playerList.size() == 1){
            result.add(this);
            result.add(playerList.get(0).getPersonalBoard().getLorenzoTrack());
            return result;
        }
        for(Player i : playerList){
            result.add(i.getPersonalBoard().getFaithTrack());
        }
        return result;
    }

    /**
     * This method receives the number of position the player has to move on the faith track,
     * then based on the ending position can trigger a Vatican Report and/or the end of the game
     * @param n: number of slot the player has to advance
     */
    public void goOn(int n){
        faithMarker += n;
        if(faithMarker >= popstate[2]){
            faithMarker = popstate[2];
        }
        if (faithMarker >= popstate[0] && favorTiles[0] == FavorTiles.COVERED){
            popeState(0);
        }
        if (faithMarker >= popstate[1] && favorTiles[1] == FavorTiles.COVERED) {
            popeState(1);
        }
        if (faithMarker >= popstate[2] && favorTiles[2] == FavorTiles.COVERED) {
            popeState(2);
            if(gameIndex != unusefulGameIndex){
                Game.get(gameIndex).endGame();
                if(Game.get(gameIndex).getNumberOfPlayer() == 1){
                    Game.get(gameIndex).endGameImmediately();
                }
            }
        }
    }

    /**
     * This method represents a Vatican Report, for each player turn or ditch their favor tile
     * @param n: number of the occurring Vatican Report
     */
    private void popeState(int n){
        int threshold = thresholds[0];
        if(n == 1){
            threshold = thresholds[1];
        }
        if(n == 2){
            threshold = thresholds[2];
        }
        for(FaithTrack i : faithTrackOfAllPlayers()){
            if(i.faithMarker >= threshold){
                i.favorTiles[n] = FavorTiles.TURNED;
            } else{
                i.favorTiles[n] = FavorTiles.DITCH;
            }
        }
    }

    /**
     * This method counts the number of victory points generated by the faith track
     * @return the number of victory points, of type int
     */
    public int victoryPoints(){
        int bonusFavorTiles = 0;
        if(favorTiles[0] == FavorTiles.TURNED){
            bonusFavorTiles += bonusFavourTiles[0];
        }
        if(favorTiles[1] == FavorTiles.TURNED){
            bonusFavorTiles += bonusFavourTiles[1];
        }
        if(favorTiles[2] == FavorTiles.TURNED){
            bonusFavorTiles += bonusFavourTiles[2];
        }
        for(Pair<Integer, Integer> pair : bonusFaithMarker){
            if(faithMarker >= pair.getKey()){
                return pair.getValue() + bonusFavorTiles;
            }
        }
        return 0;
    }

    /**
     * This method advances by 1 slot all the player except the specified one
     * @param discardingPlayer: is the player that has discarded a resource
     */
    public void allOtherPlayersGoOn(Player discardingPlayer){
        if(gameIndex == unusefulGameIndex){
            return;
        }
        ArrayList<Player> playerList = Game.get(gameIndex).getPlayers();
        if(playerList.size() == 1){
            playerList.get(0).getPersonalBoard().getLorenzoTrack().goOn(1);
        } else {
            for(Player i : playerList){
                if(i != discardingPlayer){
                    i.getPersonalBoard().getFaithTrack().goOn(1);
                }
            }
        }
    }

    /**
     * This method creates a copy of this class
     * @return a copy, of type FaithTrack
     */
    private FaithTrack copy(){
        FaithTrack copy = new FaithTrack(gameIndex);
        copy.favorTiles = this.favorTiles.clone();
        return copy;
    }

}
