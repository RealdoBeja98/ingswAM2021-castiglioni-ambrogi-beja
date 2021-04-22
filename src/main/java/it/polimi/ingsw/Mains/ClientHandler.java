package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Exceptions.NoLeaderCardToDiscardException;
import it.polimi.ingsw.Game.Game;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Game game;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line.equals("-n")) {
            boolean create = false;
            try {
                create = creatingGame(line, in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!create) {
                return;
            }
        }
        if (line.equals("-o")) {
            boolean old = false;
            try {
                old = joiningGame(line, in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!old) {
                return;
            }
        }
        /*if (!game.isGameStarted()) { <--FIXME-->
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        while (true) {
            //controllo turno
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (line.equals("quit")) {
                break;
            } else {
                String[] message = line.split(" ");

                if(message[0].equals("CHOOSE_DISCARD_LEADER_CARD")){
                    try {
                        game.getTurn().chooseDiscardLeaderCard();
                        System.out.println("change to player state");
                    } catch (NoLeaderCardToDiscardException e) {
                        out.println("ERROR_NO_CARDS");
                    } catch (ActionNotAllowedException e) {
                        out.println("ERROR_INVALID_ACTION");
                    }
                }

                if(message[0].equals("SELECT_NORMAL_ACTION")){
                    try {
                        game.getTurn().selectNormalAction(NormalAction.valueOf(message[1]));
                    } catch (ActionNotAllowedException e) {
                        out.println("ERROR_INVALID_ACTION");
                    }
                }

                if(message[0].equals("TAKE_RESOURCES_FROM_THE_MARKET")){
                    try {
                        game.getTurn().takeResourcesFromTheMarket(RowColumn.valueOf(message[1]), atoi(message[2]));
                    } catch (ActionNotAllowedException e) {
                        out.println("ERROR_INVALID_ACTION");
                    }
                }


                out.println(line);
            }
        }

        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Player quit the game");
        return;
    }

    private int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            return -1;
        }
    }

    private boolean creatingGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Starting a new game...");
        line = in.readLine();
        int numPL = atoi(line);
        game = new Game(numPL);
        System.out.println("Started game number: " + game.getGameIndex());
        return addingPlayer(in, out);
    }



    private boolean joiningGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        line = in.readLine();
        int numGM = atoi(line);
        System.out.println("Joining a game...");
        game = Game.get(numGM);
        System.out.println("Someone joined game number: " + game.getGameIndex());
        return addingPlayer(in, out);
    }

    private boolean addingPlayer(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        out.println(game.getGameIndex());
        line = in.readLine();
        try {
            game.addPlayer(line);
            out.println("PLAYER_ADDED");
            return true;
        } catch (NameAlreadyRegisteredException e) {
            System.out.println("Name already taken, rejecting the player");
            out.println("ERROR_NAME_TAKEN");
            return false;
        } catch (GameAlreadyStartedException e) {
            System.out.println("Game already started, rejecting the player");
            out.println("ERROR_GAME_STARTED");
            return false;
        }
    }
}
