package it.polimi.ingsw.Game;
import it.polimi.ingsw.Table.Table;
import java.util.ArrayList;

public class Game {

    private static Game instance = null;
    private final Table table;
    private ArrayList<Player> players;

    private Game(){
        table = new Table();
        players = new ArrayList<>();
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void addPlayer(String name){
        players.add(new Player(name));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void endGame(){

    }

    public Table getTable(){
        return table;
    }

}
