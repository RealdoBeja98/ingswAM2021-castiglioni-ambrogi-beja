package it.polimi.ingsw.Game;

import java.util.ArrayList;

public class Game {

    private static Game istance;

    public static Game getInstance(){
        if(Game.istance == null){
            Game.istance = new Game();
        }
        return Game.istance;
    }

    public ArrayList<Player> players = new ArrayList<>();

    public void endGame(){

    }

}
