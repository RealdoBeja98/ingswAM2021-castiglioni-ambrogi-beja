package it.polimi.ingsw.Game;
import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Mains.ClientHandler;
import it.polimi.ingsw.Table.Table;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This Class contains all the elements in an instance of the game
 */
public class Game {

    private static final ArrayList<Game> games = new ArrayList<>();
    private static int numberOfGames = 0;
    private final Table table;
    private final ArrayList<Player> players;
    private final ArrayList<PrintWriter> printWriterList;
    private final ArrayList<ClientHandler> handlerList;
    private Turn turn;
    private final int numberOfPlayer;
    private final int gameIndex;
    private boolean started;


    /**
     * Constructor method of this class
     */
    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
        this.gameIndex = numberOfGames;
        numberOfGames = numberOfGames + 1;
        table = new Table(gameIndex);
        players = new ArrayList<>();
        printWriterList = new ArrayList<>();
        handlerList = new ArrayList<>();
        games.add(this);
        started = false;
    }

    /**
     * This method searches for the index of the game and then returns the corresponding one
     * @param gameIndex: the number of the game wanted
     * @return the game wanted, of type Game
     */
    public static Game get(int gameIndex){
        for(Game i : games){
            if(i.gameIndex == gameIndex){
                return i;
            }
        }
        return null;
    }

    /**
     * This method adds a player to the game
     * @param name: the nickname of the player
     * @param printWriter it's for adding the communication channel "out" of the added player
     * @throws NameAlreadyRegisteredException if the name has been already added
     * @throws GameAlreadyStartedException if the game is already started
     */
    public void addPlayer(String name, PrintWriter printWriter) throws NameAlreadyRegisteredException, GameAlreadyStartedException {
        if(players.size() == numberOfPlayer){
            throw new GameAlreadyStartedException();
        }
        if(checkInListForNickname(name)){
            throw new NameAlreadyRegisteredException();
        }
        players.add(new Player(name, gameIndex));
        printWriterList.add(printWriter);
        if(numberOfPlayer == 1){
            players.get(0).isSinglePlayer();
        }
        if(players.size() == numberOfPlayer){
            startGame();
        }
    }

    /**
     * This method adds a player to the game (without passing the PrintWriter socket)
     * @param name: the nickname of the player
     * @throws NameAlreadyRegisteredException if the name has been already added
     * @throws GameAlreadyStartedException if the game is already started
     */
    public void addPlayer(String name) throws NameAlreadyRegisteredException, GameAlreadyStartedException {
        addPlayer(name, null);
    }

    /**
     * This method add the communication channel "out" of the recently added player to a list
     * @param printWriter: the channel to talk with the players
     */
    public void addSocket(PrintWriter printWriter){
        printWriterList.add(printWriter);
    }

    /**
     * This method add the client handler of the recently added player to a list
     * @param handler: the handler to manage the players
     */
    public void addHandler(ClientHandler handler){
        handlerList.add(handler);
    }

    /**
     * This method starts the game when the number of player has been reached
     */
    private void startGame(){
        setInkwell();
        turn = new Turn(gameIndex);
        for(int i = 0; i < (printWriterList.size()-1); i++){
            if(printWriterList.get(i) != null){
                printWriterList.get(i).println("wakeup");
            }
        }
        started = true;
        System.out.println("Game: " + gameIndex + " started!");
    }

    /**
     * This method checks the list to see if a player nickname is already present
     * @param nameToCheck: the name that has to be checked
     * @return true if the name is present, of type boolean
     */
    private boolean checkInListForNickname(String nameToCheck) {
        for (Player i : players) {
            if (i.getNickname().equals(nameToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method terminates the game
     */
    public void endGame() {
        turn.endGame();
    }

    /**
     * Getter of the parameter table
     * @return the table, of type Table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Getter of the parameter players
     * @return a copy of the player list, of type ArrayList of Player
     */
    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>) players.clone();
    }

    /**
     * Getter of the parameter gameIndex
     * @return the number of the current game, of type int
     */
    public int getGameIndex() {
        return gameIndex;
    }

    /**
     * Getter of the parameter turn
     * @return the turn of the current game, of type Turn
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * This method shuffles a copy of the player list and sets the inkwell to the first player
     */
    private void setInkwell(){
        ArrayList<Player> copy = (ArrayList<Player>) players.clone();
        Collections.shuffle(copy);
        copy.get(0).setInkwell();
        System.out.println("This player has the inkwell: " + copy.get(0).getNickname());
    }

    /**
     * Getter of the parameter printWriterList
     * @return the communication channels to the players, of type ArrayList of PrintWriter
     */
    public ArrayList<PrintWriter> getPrintWriterList() {
        return printWriterList;
    }

    public boolean getStarted(){
        return started;
    }

}