package it.polimi.ingsw.PersonalBoard.Faith;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Game.Player;
import java.util.ArrayList;

public class FaithTrack {
    private int faithMarker;
    private final FavorTiles[] favorTiles;
    private final ArrayList<Player> playerList;

    public FaithTrack(ArrayList<Player> playerList){
        this.playerList = playerList;
        faithMarker = 0;
        favorTiles = new FavorTiles[3];
        for(int i = 0; i < 3; i++){
            favorTiles[i] = FavorTiles.COVERED;
        }
    }

    public int getFaithMarker(){
        return faithMarker;
    }

    public FavorTiles[] getFavorTiles(){
        return favorTiles;//chiedere al prof se fare la clone o va bene così
    }

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
            arrived();
        }

    }
    private void popeState(int n){
        int treshold = 5;
        if(n == 1){
            treshold = 12;
        }
        if(n == 2){
            treshold = 19;
        }

        for(Player name : playerList){
            if(name.getPersonalBoard().getFaithTrack().faithMarker >= treshold){
                name.getPersonalBoard().getFaithTrack().favorTiles[n] =
                        FavorTiles.TURNED;
            }
            else{
                name.getPersonalBoard().getFaithTrack().favorTiles[n] =
                        FavorTiles.DITCH;
            }
        }
    }

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

    private void arrived(){
        Game.getInstance().endGame();
    }


}
