package it.polimi.ingsw.Game;
import it.polimi.ingsw.Table.Table;
import java.util.ArrayList;

public class Game {

    private static Game istance;
    private Table table = new Table();

    public static Game getInstance(){
        if(Game.istance == null){
            Game.istance = new Game();
        }
        return Game.istance;
    }

    public ArrayList<Player> players = new ArrayList<>();

    public void endGame(){

    }

    public Table getTable(){
        return table;
    }

}
