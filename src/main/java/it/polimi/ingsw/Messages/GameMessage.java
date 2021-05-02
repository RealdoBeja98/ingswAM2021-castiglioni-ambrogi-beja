package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;

import java.io.PrintWriter;

public abstract class GameMessage extends Message{

    protected void forward(Game game, String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

}
