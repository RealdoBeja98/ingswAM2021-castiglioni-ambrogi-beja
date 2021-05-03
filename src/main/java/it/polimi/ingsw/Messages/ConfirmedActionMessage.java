package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;

import java.io.PrintWriter;

public class ConfirmedActionMessage extends Message{

    @Override
    public void execute(Game game, PrintWriter out) {
        System.out.println(this);
    }

    @Override
    public String toString(){
        return "CONFIRMED_ACTION";
    }

}
