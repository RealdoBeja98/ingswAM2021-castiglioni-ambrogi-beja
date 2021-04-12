package it.polimi.ingsw.Game;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Exceptions.PlayerDoesNotExistsException;
import it.polimi.ingsw.Table.Table;
import java.util.ArrayList;

public class Game { //<-- FIXME finish me-->

    private static Game instance = null;
    private final Table table;
    private ArrayList<Player> players;

    private Game() {
        table = new Table();
        players = new ArrayList<>();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public void checkInListForNickname(String nameToCheck) throws NameAlreadyRegisteredException {
        for (Player i : players) {
            if (i.getNickname() == nameToCheck) {
                throw new NameAlreadyRegisteredException();
            }
        }

    }

    public Player getPlayerByNickname(String getPlayerNickname) throws PlayerDoesNotExistsException {
        for(Player i : players){
            if(i.getNickname() == getPlayerNickname){
                return i;
            }
        }
        throw new PlayerDoesNotExistsException();
    }

    public void endGame() {
    }

    public Table getTable() {
        return table;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}