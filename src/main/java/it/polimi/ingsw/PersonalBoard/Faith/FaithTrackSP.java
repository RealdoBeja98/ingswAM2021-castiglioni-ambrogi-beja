package it.polimi.ingsw.PersonalBoard.Faith;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.LocalMain;

/**
 * This class represents the FaithTrack of Lorenzo
 */
public class FaithTrackSP extends FaithTrack{

    private static boolean isSetForClient = false;
    private final static int faithTrackCells = 24;

    /**
     * Setter of the class
     */
    public static void setForClient(){
        FaithTrackSP.isSetForClient = true;
    }

    /**
     * Constructor method of this class
     */
    public FaithTrackSP(int gameIndex) {
        super(gameIndex);
    }

    /**
     * Constructor method of this class without gameIndex
     */
    public FaithTrackSP() {
        super();
    }

    /**
     * Constructor of this class
     * @param importedString string
     */
    public FaithTrackSP(String importedString){
        super(importedString);
    }

    /**
     * Lorenzo doesn't go on if the player has reached the last place of the FaithTrack
     * @param n: number of slot the player has to advance
     */
    @Override
    public void goOn(int n){
        if(!FaithTrackSP.isSetForClient){
            int consideringGameIndex = gameIndex;
            if(LocalMain.getIsLocal()){
                consideringGameIndex = 0;
            }
            if(!(Game.get(consideringGameIndex).getPlayers().get(0).getPersonalBoard().getFaithTrack().getFaithMarker() == faithTrackCells)){
                super.goOn(n);
            }
        } else {
            if(!(ClientMain.getPlayerGame().getPlayers().get(0).getFaithTrack().getFaithMarker() == faithTrackCells)){
                super.goOn(n);
            }
        }
    }

}
