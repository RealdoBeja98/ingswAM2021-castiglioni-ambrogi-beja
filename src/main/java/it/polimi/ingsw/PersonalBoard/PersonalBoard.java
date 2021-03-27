package it.polimi.ingsw.PersonalBoard;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;

public class PersonalBoard {

    private FaithTrack faithTrack = new FaithTrack(Game.getInstance().players);

    public FaithTrack getFaithTrack(){
        return faithTrack;
    }

}
