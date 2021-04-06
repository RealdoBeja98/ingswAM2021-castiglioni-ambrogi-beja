package it.polimi.ingsw.Game;
import it.polimi.ingsw.Table.Table;
import java.util.ArrayList;

public class Game {

    private static Game instance;
    private final Table table = new Table();

    public static Game getInstance(){
        if(Game.instance == null){
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public ArrayList<Player> players = new ArrayList<>();

    public void endGame(){

    }

    public Table getTable(){
        return table;
    }

}
