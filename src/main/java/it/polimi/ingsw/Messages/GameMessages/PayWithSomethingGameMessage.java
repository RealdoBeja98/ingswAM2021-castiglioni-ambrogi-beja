package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateObtainedMultipleResourceForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public abstract class PayWithSomethingGameMessage extends GameMessage {

    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
        int faith = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.FAITH);
        int coin = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.COIN);
        int servant = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SERVANT);
        int shield = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SHIELD);
        int stone = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.STONE);
        //before game.getTurn().payWithStrongBox(resource); was here
        //before game.getTurn().payWithWarehouseDepots(position); was here
        //before game.getTurn().payWithExtraStorageLeaderCard(leaderCardPosition); was here
        Message.sendMessage(out, new ConfirmedActionMessage());
        System.out.println(identifier);
        forwardAll(game, new UpdateObtainedMultipleResourceForwardMessage(currentPlayer, faith, coin, servant, shield, stone));
    }

}
