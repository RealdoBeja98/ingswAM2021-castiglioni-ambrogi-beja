package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.GameDontExistErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameStartedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotYourTurnErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowCurrentBoardMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ForwardMessages.CurrentPlayerMessage;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import it.polimi.ingsw.Utilities.CloseCommunicationChannel;
import it.polimi.ingsw.Utilities.IntegerToString;

import java.io.*;
import java.net.Socket;

/**
 * Class of client handle
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private Game game;
    private String nickname;

    /**
     * This class represents the player loby and entrance parameters
     * @param socket
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    private boolean createNewGame(String line, BufferedReader in, PrintWriter out){
        boolean create = false;
        try {
            create = creatingGame(line, in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return create;
    }

    private boolean joinExistingGame(String line, BufferedReader in, PrintWriter out){
        boolean old = false;
        try {
            old = joiningGame(line, in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return old;
    }

    private boolean waitUntilStartingGame(BufferedReader in, PrintWriter out){
        if (!game.getStarted()) {
            WakeUpThread wut = new WakeUpThread(in, out);
            Thread t = new Thread(wut);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (wut.getReturnValue() == 1) {
                CloseCommunicationChannel.f(in, out, nickname, game, socket);
                try {
                    game.removePlayer(nickname);
                } catch (GameEndedException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    private void sendStartingMessages(PrintWriter out){
        out.println(new GameStartServiceMessage(game));
        out.println(new CurrentPlayerMessage(game.getTurn().getCurrentPlayer().getNickname()));
    }

    private PingPong launchPingPong(BufferedReader in, PrintWriter out){
        PingPong pingPong = new PingPong(in, out, game, socket, nickname);
        Thread pingPongThread = new Thread(pingPong);
        pingPongThread.start();
        return pingPong;
    }

    private boolean scanf(BufferedReader in, PrintWriter out, PingPong pingPong) throws IOException {
        String line = in.readLine();
        if(line == null){
            String whoQuited = nickname;
            forward(whoQuited + " quit", out);
            pingPong.stop();
            return false;
        }

        switch (line){
            case "quit":
                out.println("quit");
                try {
                    game.removePlayer(nickname);
                } catch (GameEndedException e) {
                    e.printStackTrace();
                }
                forward(nickname + " quit", out);
                CloseCommunicationChannel.f(in, out, nickname, game, socket);
                pingPong.stop();
                return false;
            case "GAME_ENDED":
                CloseCommunicationChannel.f(in, out, nickname, game, socket);
                pingPong.stop();
                return false;
            case "NOTIFY_PB_ALL":
                Message.sendMessage(out, new ShowCurrentBoardMessage());
                break;
            case "pong":
                pingPong.update();
                break;
            default:
                if (!game.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
                    Message.sendMessage(out, new NotYourTurnErrorMessage());
                } else {
                    Message message = Message.fromString(line);
                    message.execute(game, out);
                }
                break;
        }
        return true;
    }

    private void informPlayerCrashed(PrintWriter out){
        forward(nickname + " crashed", out);
        try {
            game.removePlayer(nickname);
        } catch (GameEndedException gameEndedException) {
            gameEndedException.printStackTrace();
        }
    }

    /**
     * This method sets all the parameters that a player needs
     * to type to enter a specific game
     */
    public void run() {
        try {

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
            if(line.equals("-n")) {
                if(!createNewGame(line, in, out)){
                    return;
                }
            }
            if(line.equals("-o")) {
                if(!joinExistingGame(line, in, out)){
                    return;
                }
            }
            if(!waitUntilStartingGame(in, out)){
                return;
            }

            sendStartingMessages(out);
            PingPong pingPong = launchPingPong(in, out);

            while (true) {
                try {
                    if(!scanf(in, out, pingPong)){
                        return;
                    }
                } catch (IOException e) {
                    informPlayerCrashed(out);
                    break;
                }
            }
            CloseCommunicationChannel.f(in, out, nickname, game, socket);
            pingPong.stop();

        } catch (Exception e){
            System.out.println("Unexpected problem");
        }
    }

    /**
     * This method does the creating of a game with the parameters that the player has given, and
     * prints it to the console
     * @param in reads the message which comes from the socket
     * @param out sends message to a socket
     * @return addingPlayer the player which was successfully entered the game
     * @throws IOException
     */
    private boolean creatingGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Creating a new game...");
        line = in.readLine();
        int numPL = IntegerToString.f(line);
        game = new Game(numPL);
        System.out.println("Created game number: " + game.getGameIndex());
        return addingPlayer(in, out);
    }

    /**
     * This method let
     * @param line
     * @param in reads the message which comes from the socket
     * @param out sends message to a socket
     * @return
     * @throws IOException
     */
    private boolean joiningGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        line = in.readLine();
        int numGM = IntegerToString.f(line);
        System.out.println("Joining a game...");
        game = Game.get(numGM);
        try{
            System.out.println("Someone joined game number: " + game.getGameIndex());
        }catch(NullPointerException e){
            System.out.println("Game doesn't exist, rejecting the player");
            Message.sendMessage(out, new GameDontExistErrorMessage());
            return false;
        }
        return addingPlayer(in, out);
    }

    /**
     * This methods lets a player entering a specific game with the controlling of
     * his nickname, if it is taken or not.After this letting him to participate
     * @param in reads the message which comes from the socket
     * @param out sends message to a socket
     * @throws IOException
     */
    private boolean addingPlayer(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        out.println(game.getGameIndex());
        line = in.readLine();
        try {
            game.addPlayer(line, out);
            nickname = line;
            out.println("PLAYER_ADDED");
            return true;
        } catch (NameAlreadyRegisteredException e) {
            System.out.println("Name already taken, rejecting the player");
            Message.sendMessage(out, new NameTakenErrorMessage());
            return false;
        } catch (GameAlreadyStartedException e) {
            System.out.println("Game already started, rejecting the player");
            Message.sendMessage(out, new GameStartedErrorMessage());
            return false;
        }
    }

    /**
     * Is handy for the forward messages
     * @param message forward message
     * @param toExclude
     */
    private void forward(String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

}
