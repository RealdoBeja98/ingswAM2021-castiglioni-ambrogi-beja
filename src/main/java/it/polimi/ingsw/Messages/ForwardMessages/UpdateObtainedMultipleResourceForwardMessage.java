package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class UpdateObtainedMultipleResourceForwardMessage extends ForwardMessage {

    private String nickname;
    private int faith;
    private int coin;
    private int stone;
    private int servant;
    private int shield;

    /**
     * Constructor of the class
     * @param coin coin
     * @param faith faith
     * @param nickname name of the player
     * @param shield shield
     * @param servant servant
     * @param stone stone
     */
    public UpdateObtainedMultipleResourceForwardMessage(String nickname, int faith, int coin, int stone, int servant, int shield){
        identifier = "UPDATE_MULTIPLE_RESOURCES";
        this.nickname = nickname;
        this.faith = faith;
        this.coin = coin;
        this.stone = stone;
        this.servant = servant;
        this.shield = shield;
    }

    /**
     * This method lets you to have multiple resources when you chose from the market, and go 1 forward if you
     * obtained a faith
     * @param game
     * @param out
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().addStrongBox(nickname, Resource.COIN, coin);
        ClientMain.getPlayerGame().addStrongBox(nickname, Resource.STONE, stone);
        ClientMain.getPlayerGame().addStrongBox(nickname, Resource.SERVANT, servant);
        ClientMain.getPlayerGame().addStrongBox(nickname, Resource.SHIELD, shield);
        if(faith >= 1){
            ClientMain.getPlayerGame().goOnFaithTrack(nickname, faith);
        }
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + faith + " " + coin + " " + stone + " " + servant + " " + shield;
    }

}
