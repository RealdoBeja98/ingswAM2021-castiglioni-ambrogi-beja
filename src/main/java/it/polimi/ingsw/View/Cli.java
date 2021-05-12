package it.polimi.ingsw.View;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Market.Marbles.Marble;
import java.util.ArrayList;

/**
 * This Class is the representation of the command line interface
 */
public class Cli extends View{

    /**
     * This method show the player the state of the market
     */
    @Override
    public void showMarket() {
        Marble[][] visualize = ClientMain.getPlayerGame().getMarket().getMarketTray();
        System.out.println("╔═══════╗");
        int j = 0;
        for (int i = 0; i <= 8; i++) {
            if (i % 2 == 0) {
                System.out.print("║");
            } else {
                System.out.print(resourceToColorASCI(visualize[0][j].getWhatIAm()));
                j++;
            }
        }
        System.out.println();
        System.out.println("╠═══════╣");
        j = 0;
        for (int i = 0; i <= 8; i++) {
            if (i % 2 == 0) {
                System.out.print("║");
            } else {
                System.out.print(resourceToColorASCI(visualize[1][j].getWhatIAm()));
                j++;
            }
        }
        System.out.println();
        System.out.println("╠═══════╣");

        j = 0;
        for (int i = 0; i <= 8; i++) {
            if (i % 2 == 0) {
                System.out.print("║");
            } else {
                System.out.print(resourceToColorASCI(visualize[2][j].getWhatIAm()));
                j++;
            }
        }
        System.out.println();
        System.out.println("╚═══════╝");
        Marble extra = ClientMain.getPlayerGame().getMarket().getExtraMarble();
        System.out.println("Extra: " + resourceToColorASCI(extra.getWhatIAm()));
    }

    /**
     * This method show the player the state of the card market
     */
    @Override
    public void showDevCard() {

        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        DevelopmentCard[][] visualize = ClientMain.getPlayerGame().getDevelopmentDeck().visualize();
        int j = 0;
        String escapeW = Color.ANSI_WHITE.escape();
        String escapeG = Color.ANSI_GREEN.escape();
        String escapeB = Color.ANSI_BLUE.escape();
        String escapeY = Color.ANSI_YELLOW.escape();
        String escapeP = Color.ANSI_PURPLE.escape();
        System.out.println("║" + escapeG + "╔═════════════════════════╦"+ escapeB + "╦═════════════════════════╦" +
                            escapeY + "╦═════════════════════════╦" + escapeP + "╦═════════════════════════╗" + escapeW + "║");

        for(int z = 0; z < 7; z++){
            if(z % 2 == 0){
                System.out.println("║" + escapeG + "╠═════════════════════════╬"+ escapeB + "╬═════════════════════════╬" +
                                    escapeY + "╬═════════════════════════╬" + escapeP + "╬═════════════════════════╣" + escapeW + "║");
            }
            else{
                System.out.print("║");
                for(int i = 0; i < 4; i++){
                    printDevCard(visualize[j][i]);
                }
                System.out.println("║");
                j++;
            }

        }
        System.out.println("║" + escapeG + "╚═════════════════════════╩"+ escapeB + "╩═════════════════════════╩" +
                escapeY + "╩═════════════════════════╩" + escapeP + "╩═════════════════════════╝" + escapeW + "║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    /**
     * This method show the player the state of all personal boards
     */
    @Override
    public void showPersonalBoard() {
        ArrayList<PlayerGame.PlayerPlayer> players = ClientMain.getPlayerGame().getPlayers();
        for(PlayerGame.PlayerPlayer n : players){
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗║");
            int l = n.getNickname().length();
            System.out.print("║║"+ n.getNickname());
            while(l < 106){
                System.out.print(" ");
                l++;
            }
            System.out.println("║║");
            System.out.println("║╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝║");
            System.out.println("║╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗║");
            System.out.println("║║ Faith Track                                                                                              ║║");
            System.out.println("║╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝║");
            System.out.println("║╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗║");
            int f = n.getFaithTrack().getFaithMarker();
            System.out.print("║║");
            int i = 0;
            while(i < (f * 2)){
                System.out.print(" ");
                i++;
            }
            System.out.print(Color.ANSI_RED.escape() + "x" + Color.ANSI_WHITE.escape());
            while(i < 105){
                System.out.print(" ");
                i++;
            }
            System.out.println("║║");
            System.out.print("║║");
            int j = 0;
            int p = 0;
            while (j < 50){
                if(j % 2 == 0){
                    if(p == 3 || p == 6 || p == 9 || p == 12 || p == 15 || p == 18 || p == 21){
                        System.out.print(Color.ANSI_YELLOW.escape() + "_" + Color.ANSI_WHITE.escape());
                    }else if(p == 8 || p == 16 || p == 24){
                        System.out.print(Color.ANSI_RED.escape() + "_" + Color.ANSI_WHITE.escape());
                    }else{
                        System.out.print("_");
                    }
                    p++;
                }else{
                    System.out.print(" ");
                }
                j++;
            }
            while(j < 106){
                System.out.print(" ");
                j++;
            }
            System.out.println("║║");
            System.out.println("║╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝║");
            System.out.println("║╔════════════════════════════╦═════════════════════════════════════════════════════════════════════════════╗║");
            System.out.println("║║ Warehouse                  ║ Card slot                                                                   ║║");
            System.out.println("║╚════════════════════════════╩═════════════════════════════════════════════════════════════════════════════╝║");
            System.out.println("║╔════════════════════════════╦═════════════════════════╦═════════════════════════╦═════════════════════════╗║");
            Resource[] resource = n.getWarehouseDepots().getResource();
            DevelopmentCard[][] visualize = n.getSlotsDevelopmentCards().getSlot();
            System.out.print("║║");
            int w1 = 0;
            while(w1 <= 12){
                System.out.print(" ");
                w1++;
            }
            if(resource[0] != null){
                System.out.print(resourceToColorASCI(resource[0]));
            }
            else {
                System.out.print(" ");
            }
            while(w1 <= 26){
                System.out.print(" ");
                w1++;
            }
            System.out.print("║");
            for(int col = 0; col < 3; col++){
                if(visualize[0][col] != null){
                    printDevCard(visualize[0][col]);
                }else{
                    int c00 = 0;
                    while(c00 <= 24){
                        System.out.print(" ");
                        c00++;
                    }
                }
                System.out.print("║");
            }
            System.out.println("║");
            System.out.println("║╠════════════════════════════╬═════════════════════════╬═════════════════════════╬═════════════════════════╣║");
            System.out.print("║║");
            int w2 = 0;
            while(w2 <= 11){
                System.out.print(" ");
                w2++;
            }
            if(resource[1] != null){
                System.out.print(resourceToColorASCI(resource[1]) + " ");

            }
            else {
                System.out.print("  ");
            }
            if(resource[2] != null){
                System.out.print(resourceToColorASCI(resource[2]));

            }
            else {
                System.out.print(" ");
            }
            w2 += 2;
            while(w2 <= 26){
                System.out.print(" ");
                w2++;
            }
            System.out.print("║");
            for(int col = 0; col < 3; col++){
                if(visualize[1][col] != null){
                    printDevCard(visualize[1][col]);
                }else{
                    int c00 = 0;
                    while(c00 <= 24){
                        System.out.print(" ");
                        c00++;
                    }
                }
                System.out.print("║");
            }
            System.out.println("║");
            System.out.println("║╠════════════════════════════╬═════════════════════════╬═════════════════════════╬═════════════════════════╣║");
            System.out.print("║║");
            int w3 = 0;
            while(w3 <= 10){
                System.out.print(" ");
                w3++;
            }
            if(resource[3] != null){
                System.out.print(resourceToColorASCI(resource[3]) + " ");
            }
            else {
                System.out.print("  ");
            }
            if(resource[4] != null){
                System.out.print(resourceToColorASCI(resource[4]) + " ");
            }
            else {
                System.out.print("  ");
            }
            if(resource[5] != null){
                System.out.print(resourceToColorASCI(resource[5]));
            }
            else {
                System.out.print(" ");
            }
            w3 += 4;
            while(w3 <= 26){
                System.out.print(" ");
                w3++;
            }
            System.out.print("║");
            for(int col = 0; col < 3; col++){
                if(visualize[1][col] != null){
                    printDevCard(visualize[1][col]);
                }else{
                    int c00 = 0;
                    while(c00 <= 24){
                        System.out.print(" ");
                        c00++;
                    }
                }
                System.out.print("║");
            }
            System.out.println("║");
            System.out.println("║╚════════════════════════════╩═════════════════════════╩═════════════════════════╩═════════════════════════╝║");
            System.out.println("║╔══════════════════════════════════════════════════════╦═══════════════════════════════════════════════════╗║");
            System.out.println("║║ Strongbox                                            ║ Leader card                                       ║║");
            System.out.println("║╚══════════════════════════════════════════════════════╩═══════════════════════════════════════════════════╝║");
            int coin = n.getStrongBox().getCoin();
            int shield = n.getStrongBox().getShield();
            int servant = n.getStrongBox().getServant();
            int stone = n.getStrongBox().getStone();
            LeaderCard[] cardsInHand = n.getCardsInHand();
            LeaderCard[] cardsOnTable = n.getCardsOnTable();
            System.out.println("║╔════════════════════════════╦═════════════════════════╦═══════════════════════════════════════════════════╗║");
            System.out.println("║║ Coin                       ║ Shield                  ║ In hand                                           ║║");
            System.out.println("║╠════════════════════════════╬═════════════════════════╬═════════════════════════╦═════════════════════════╣║");
            System.out.print("║║ " + coin);
            int x;
            if(coin < 10){
                x = 2;
            }else if(coin < 100){
                x = 3;
            }else{
                x = 4;
            }
            while(x <28){
                System.out.print(" ");
                x++;
            }
            System.out.print("║ ");

            System.out.print(shield);
            int xx;
            if(shield < 10){
                xx = 2;
            }else if(shield < 100){
                xx = 3;
            }else{
                xx = 4;
            }
            while(xx <25){
                System.out.print(" ");
                xx++;
            }
            System.out.print("║ ");
            if(cardsInHand[0] != null){
                printLeadCard(cardsInHand[0]);
            } else {
                System.out.print("                        ");
            }

            System.out.print("║ ");

            if(cardsInHand[1] != null){
                printLeadCard(cardsInHand[1]);
            } else {
                System.out.print("                        ");
            }
            System.out.println("║║");
            System.out.println("║╠════════════════════════════╬═════════════════════════╬═════════════════════════╩═════════════════════════╣║");
            System.out.println("║║ Servant                    ║ Stone                   ║ On Table                                          ║║");
            System.out.println("║╠════════════════════════════╬═════════════════════════╬═════════════════════════╦═════════════════════════╣║");
            System.out.print("║║ " + servant);
            int xxx;
            if(servant < 10){
                xxx = 2;
            }else if(servant < 100){
                xxx = 3;
            }else{
                xxx = 4;
            }
            while(xxx <28){
                System.out.print(" ");
                xxx++;
            }
            System.out.print("║ ");

            System.out.print(stone);
            int xxxx;
            if(stone < 10){
                xxxx = 2;
            }else if(stone < 100){
                xxxx = 3;
            }else{
                xxxx = 4;
            }
            while(xxxx <25){
                System.out.print(" ");
                xxxx++;
            }
            System.out.print("║ ");
            if(cardsOnTable[0] != null){
                printLeadCard(cardsOnTable[0]);
            } else {
                System.out.print("                        ");
            }

            System.out.print("║ ");

            if(cardsOnTable[1] != null){
                printLeadCard(cardsOnTable[1]);
            } else {
                System.out.print("                        ");
            }
            System.out.println("║║");
            System.out.println("║╚════════════════════════════╩═════════════════════════╩═════════════════════════╩═════════════════════════╝║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
    }

    @Override
    public void showPBCurrent() {

        //<--FIXME aggiungere la conoscenza del current player per evitare di printare tutte le board-->
    }

    /**
     * This method generates a line with the color of the resource passed
     * @param r: the resource wanted
     * @return a line with the resource wanted, of type String
     */
    private String resourceToColorASCI(Resource r){
        switch (r){
            case COIN:
                return (Color.ANSI_YELLOW.escape() + "C" + Color.ANSI_WHITE.escape());
            case FAITH:
                return (Color.ANSI_RED.escape() + "F" + Color.ANSI_WHITE.escape());
            case SERVANT:
                return (Color.ANSI_PURPLE.escape() + "S" + Color.ANSI_WHITE.escape());
            case SHIELD:
                return (Color.ANSI_CYAN.escape() + "S" + Color.ANSI_WHITE.escape());
            case STONE:
                return (Color.ANSI_GRAY.escape() + "S" + Color.ANSI_WHITE.escape());
            case WHITE:
                return ("W");
            default: return null;
        }
    }

    /**
     * This method generates a color based on the type passed
     * @param t: the type wanted
     * @return a line with the corresponding color, of type String
     */
    private String typeToColorASCI(Type t){
        switch (t){
            case GREEN:
                return (Color.ANSI_GREEN.escape());
            case BLUE:
                return (Color.ANSI_BLUE.escape());
            case YELLOW:
                return (Color.ANSI_YELLOW.escape());
            case PURPLE:
                return (Color.ANSI_PURPLE.escape());
            default: return null;
        }
    }

    /**
     * This method prints a development card
     * @param visualize: the card wanted
     */
    private void printDevCard(DevelopmentCard visualize) {
        String escapeW = Color.ANSI_WHITE.escape();

        int tot;

        Type type = visualize.getType();
        String escapeT = typeToColorASCI(type);
        System.out.print(escapeT + "║" + escapeW);

        Resource[] cost = visualize.getCost();
        int[] costN = visualize.getCostNumber();
        for (int n = 0; n < cost.length; n++) {
            System.out.print(costN[n] + resourceToColorASCI(cost[n]));
        }
        System.out.print(escapeT + "/" + escapeW);
        Resource[] requirements = visualize.getRequirements();
        int[] costRequirements = visualize.getCostRequirements();
        for (int n = 0; n < requirements.length; n++) {
            System.out.print(costRequirements[n] + resourceToColorASCI(requirements[n]));
        }
        System.out.print(escapeT + "/" + escapeW);
        Resource[] products = visualize.getProducts();
        int[] costProducts = visualize.getCostProducts();
        for (int n = 0; n < products.length; n++) {
            System.out.print(costProducts[n] + resourceToColorASCI(products[n]));
        }
        System.out.print(escapeT + "/" + escapeW);
        System.out.print(visualize.getVictoryPoints() + "VP");
        if (visualize.getVictoryPoints() >= 10) {
            tot = ((cost.length + requirements.length + products.length) * 2) + 7;
        } else {
            tot = ((cost.length + requirements.length + products.length) * 2) + 6;
        }
        while (tot < 25) {
            System.out.print(" ");
            tot++;
        }
        System.out.print(escapeT + "║" + escapeW);
    }

    /**
     * This method prints a leader card
     * @param visualize: the card wanted
     */
    private void printLeadCard(LeaderCard visualize){
        String escapeW = Color.ANSI_WHITE.escape();
        switch (visualize.getWhatIAm()) {
            case DISCOUNT:
                DiscountLeaderCard d = (DiscountLeaderCard) visualize;
                Type[] t = d.getCostOfLeaderCard();
                Resource r = d.getDiscount();
                int v = d.getVictoryPoints();
                System.out.print("D: -1" + resourceToColorASCI(r) + "/1" + typeToColorASCI(t[0]) + "x" + escapeW + "1" + typeToColorASCI(t[1]) + "x" + escapeW + "/" + v + "VP         ");
                break;
            case STORAGE:
                ExtraStorageLeaderCard s = (ExtraStorageLeaderCard) visualize;
                Resource sr = s.getCostOfLeaderCard();
                Resource srr = s.getStorageType();
                int sv = s.getVictoryPoints();
                System.out.print("S: 1-1" + resourceToColorASCI(srr) + "/5" + resourceToColorASCI(sr) + "/" + sv + "VP          ");
                break;
            case PRODUCTIONPOWER:
                ProductionPowerLeaderCard p = (ProductionPowerLeaderCard) visualize;
                Type pt = p.getCostOfLeaderCard();
                Resource pr = p.getRequirement();
                int pv = p.getVictoryPoints();
                System.out.print("P: 1?+" + resourceToColorASCI(Resource.FAITH) + "/1" + resourceToColorASCI(pr) + "/1" + typeToColorASCI(pt) + "xx" + escapeW + "/"+ pv + "VP      ");
                break;
            case WHITE:
                WhiteMarbleLeaderCard w = (WhiteMarbleLeaderCard) visualize;
                Type[] wt = w.getCostOfLeaderCard();
                Marble m = w.getWhiteMarble();
                Resource wr = m.getWhatIAm();
                int wv = w.getVictoryPoints();
                System.out.print("W: " + resourceToColorASCI(wr) + "/2" + typeToColorASCI(wt[0]) + "x" + escapeW + "1" + typeToColorASCI(wt[1]) + "x" + escapeW + "/" + wv + "VP           ");
                break;
            default: break;
        }
    }
}
