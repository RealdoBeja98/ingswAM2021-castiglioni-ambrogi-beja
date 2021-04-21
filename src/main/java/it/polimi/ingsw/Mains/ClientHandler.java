package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Exceptions.GameAlreadyStartedException;
import it.polimi.ingsw.Exceptions.NameAlreadyRegisteredException;
import it.polimi.ingsw.Game.Game;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            //Scanner in = new Scanner(socket.getInputStream()); //legge dal canale con client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //scrive sul canale con client
            while (true) {
                //String line = in.nextLine();
                String line = in.readLine();
                if( line.equals("-n")){
                    System.out.println("Starting a new game...");
                    line = in.readLine();
                    int numPL = atoi(line);
                    Game newGame = new Game(numPL);
                    System.out.println("Started game number: " + newGame.getGameIndex());
                    out.println(newGame.getGameIndex());
                    line = in.readLine();
                    try {
                        newGame.addPlayer(line);
                    } catch (NameAlreadyRegisteredException e) {
                        out.println("ERROR_NAME_TAKEN");
                        return;
                    } catch (GameAlreadyStartedException e) {
                        out.println("ERROR_GAME_STARTED");
                        return;
                    }
                }
                if(line.equals("-o")){
                    line = in.readLine();
                    int numGM = atoi(line);
                    System.out.println("Joining a game...");
                    Game joinedGame = Game.get(numGM);
                    System.out.println("Someone joined game number: " + joinedGame.getGameIndex());
                    out.println(joinedGame.getGameIndex());
                    line = in.readLine();
                    try {
                        joinedGame.addPlayer(line);
                    } catch (NameAlreadyRegisteredException e) {
                        out.println("ERROR_NAME_TAKEN");
                        return;
                    } catch (GameAlreadyStartedException e) {
                        out.println("ERROR_GAME_STARTED");
                        return;
                    }
                }
                int i=0;
                while (Game.get(0).getPlayers().get(i) !=null) {
                    System.out.println(Game.get(0).getPlayers().get(i));
                    i++;
                }
                if (line.equals("quit")) {
                    break;
                } else {
                    out.println(line + line);
                }
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            return -1;
        }
    }
}
