package it.polimi.ingsw.PersonalBoard.Faith;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.Player;
import java.util.ArrayList;

/**
 * This Class represents the faith track associate to a player
 */
public class FaithTrack {

    private int faithMarker;
    private FavorTiles[] favorTiles;
    private final int gameIndex;

    /**
     * Constructor method of this class
     */
    public FaithTrack(int gameIndex){
        this.gameIndex = gameIndex;
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
        if(faithMarker >= 24){
            faithMarker = 24;
        }
        if (faithMarker >= 8 && favorTiles[0] == FavorTiles.COVERED){
            popeState(0);
        }

        if (faithMarker >= 16 && favorTiles[1] == FavorTiles.COVERED) {
            popeState(1);
        }

        if (faithMarker >= 24 && favorTiles[2] == FavorTiles.COVERED) {
            popeState(2);
            Game.get(gameIndex).endGame();
        }
    }

    /**
     * This method represents a Vatican Report, for each player turn or ditch their favor tile
     * @param n: number of the occurring Vatican Report
     */
    private void popeState(int n){
        int threshold = 5;
        if(n == 1){
            threshold = 12;
        }
        if(n == 2){
            threshold = 19;
        }
        for(FaithTrack i : faithTrackOfAllPlayers()){
            if(i.faithMarker >= threshold){
                i.favorTiles[n] =
                        FavorTiles.TURNED;
            }
            else{
                i.favorTiles[n] =
                        FavorTiles.DITCH;
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
            bonusFavorTiles += 2;
        }
        if(favorTiles[1] == FavorTiles.TURNED){
            bonusFavorTiles += 3;
        }
        if(favorTiles[2] == FavorTiles.TURNED){
            bonusFavorTiles += 4;
        }
        if(faithMarker >= 24){
            return 20 + bonusFavorTiles;
        }
        else if(faithMarker >= 21){
            return 16 + bonusFavorTiles;
        }
        else if(faithMarker >= 18){
            return 12 + bonusFavorTiles;
        }
        else if(faithMarker >= 15){
            return 9 + bonusFavorTiles;
        }
        else if(faithMarker >= 12){
            return 6 + bonusFavorTiles;
        }
        else if(faithMarker >= 9){
            return 4 + bonusFavorTiles;
        }
        else if(faithMarker >= 6){
            return 2 + bonusFavorTiles;
        }
        else if(faithMarker >= 3){
            return 1 + bonusFavorTiles;
        }
        else{
            return 0;
        }
    }

    /**
     * This method advances by 1 slot all the player aside the specified one
     * @param discardingPlayer: is the player who has discarded a resource
     */
    public void allOtherPlayersGoOn(Player discardingPlayer){
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
