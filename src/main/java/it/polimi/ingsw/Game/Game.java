package it.polimi.ingsw.Game;
import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Exceptions.PlayerDoesNotExistsException;
import it.polimi.ingsw.Table.Table;
import java.util.ArrayList;
import java.util.Collections;

public class Game { //<-- FIXME finish me-->

    private static ArrayList<Game> games = new ArrayList<>();
    private static int numberOfGames = 0;

    public static Game get(int gameIndex){
        for(Game i : games){
            if(i.gameIndex == gameIndex){
                return i;
            }
        }
        return null;
    }

    private Table table;
    private ArrayList<Player> players;
    private Turn turn;
    private int numberOfPlayer;
    private int gameIndex;
    private boolean gameStarted;

    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
        this.gameIndex = numberOfGames;
        numberOfGames = numberOfGames + 1;
        table = new Table(gameIndex);
        players = new ArrayList<>();
        games.add(this);
        gameStarted = false;
    }

    public void addPlayer(String name) throws NameAlreadyRegisteredException, GameAlreadyStartedException {
        if(players.size() == numberOfPlayer){
            throw new GameAlreadyStartedException();
        }
        if(checkInListForNickname(name) == true){
            throw new NameAlreadyRegisteredException();
        }
        players.add(new Player(name, gameIndex));
        if(numberOfPlayer == 1){
            players.get(0).isSinglePlayer();
        }
        if(players.size() == numberOfPlayer){
            gameStarted = true;
            startGame();
        }
    }

    private void startGame(){
        setInkwell();
        turn = new Turn(gameIndex);
        System.out.println("Game: " + gameIndex + " started!");
        //notifyAll();
    }

    private boolean checkInListForNickname(String nameToCheck) {
        for (Player i : players) {
            if (i.getNickname().equals(nameToCheck)) {
                return true;
            }
        }
        return false;
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
        turn.endGame();
    }

    public Table getTable() {
        return table;
    }

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players.clone();
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Turn getTurn() {
        return turn;
    }

    private void setInkwell(){
        ArrayList<Player> copy = (ArrayList<Player>) players.clone();
        Collections.shuffle(copy);
        copy.get(0).setInkwell();
        System.out.println("This player has the inkwell: " + copy.get(0).getNickname());
    }
}