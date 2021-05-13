package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.GameStartedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NameTakenErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotYourTurnErrorMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessages.CurrentPlayerMessage;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private Game game;
    private String nickname;

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
        if (!game.getStarted()) {
            WakeUpThread wut = new WakeUpThread(in, out);
            Thread t = new Thread(wut);
            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(wut.getReturnValue() == 1){
                closeCommunicationChannel(in, out);
                try {
                    game.removePlayer(nickname);
                } catch (GameEndedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        out.println(new GameStartServiceMessage(game));
        out.println(new CurrentPlayerMessage(game.getTurn().getCurrentPlayer().getNickname()));

        while (true) {
            try {
                line = in.readLine();
                if (line.equals("quit")) {
                    out.println("quit");
                    try {
                        game.removePlayer(nickname);
                    } catch (GameEndedException e) {
                        e.printStackTrace();
                    }
                    forward(nickname + " quit", out);
                    closeCommunicationChannel(in, out);
                    return;
                }
                else if (line.equals("GAME_ENDED")){
                    closeCommunicationChannel(in, out);
                    return;
                }
                else if (!game.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
                    Message.sendMessage(out, new NotYourTurnErrorMessage());
                }
                else {
                    Message message = Message.fromString(line);
                    message.execute(game, out);
                    }
            } catch (IOException e) {
                forward(nickname + " crashed", out);
                try {
                    game.removePlayer(nickname);
                } catch (GameEndedException gameEndedException) {
                    gameEndedException.printStackTrace();
                }
                break;
            }
        }
        closeCommunicationChannel(in, out);
    }

    private void closeCommunicationChannel(BufferedReader in, PrintWriter out) {
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
        System.out.println("Player: " + nickname + " quit game " + game.getGameIndex());
    }

    private int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            throw new IllegalArgumentException();
        }
    }

    private boolean creatingGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Creating a new game...");
        line = in.readLine();
        int numPL = atoi(line);
        game = new Game(numPL);
        System.out.println("Created game number: " + game.getGameIndex());
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

    private void forward(String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }
}
