package it.polimi.ingsw.View;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Market.Marbles.Marble;

public class Cli extends View{

    @Override
    public void showMarket(int index) {
        Marble[][] visualize = Game.get(index).getTable().getMarket().getMarketTray();
        System.out.print("╔");
        for(int i = 1; i < 8; i++){
            System.out.print("═");
        }
        System.out.println("╗");
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
        System.out.print("╠");
        for(int i = 1; i < 8; i++){
            System.out.print("═");
        }
        System.out.println("╣");
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
        System.out.print("╠");
        for(int i = 1; i < 8; i++){
            System.out.print("═");
        }
        System.out.println("╣");
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
        System.out.print("╚");
        for(int i = 1; i < 8; i++){
            System.out.print("═");
        }
        System.out.println("╝");
        Marble extra = Game.get(index).getTable().getMarket().getExtraMarble();
        System.out.println("Extra: " + resourceToColorASCI(extra.getWhatIAm()));
    }

    @Override
    public void showDevCard(int index) {
        DevelopmentCard[][] visualize = Game.get(index).getTable().getDevelopmentDeck().visualize();
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < 4; i++){
                int tot = 0;
                System.out.print("║");
                Type type = visualize[j][i].getType();
                Resource[] cost = visualize[j][i].getCost();
                int[] costN = visualize[j][i].getCostNumber();
                for (int n = 0; n < cost.length; n++){
                    System.out.print(costN[n] + resourceToColorASCI(cost[n]));
                }
                System.out.print(typeToColorASCI(type));
                Resource[] requirements = visualize[j][i].getRequirements();
                int[] costRequirements = visualize[j][i].getCostRequirements();
                for (int n = 0; n < requirements.length; n++){
                    System.out.print(costRequirements[n] + resourceToColorASCI(requirements[n]));
                }
                System.out.print(typeToColorASCI(type));
                Resource[] products = visualize[j][i].getProducts();
                int[] costProducts = visualize[j][i].getCostProducts();
                for (int n = 0; n < products.length; n++){
                    System.out.print(costProducts[n] + resourceToColorASCI(products[n]));
                }
                System.out.print(typeToColorASCI(type));
                System.out.print(visualize[j][i].getVictoryPoints() + "VP");
                if(visualize[j][i].getVictoryPoints() >= 10){
                    tot = ((cost.length + requirements.length + products.length) * 2) + 7;
                }
                else{
                    tot = ((cost.length + requirements.length + products.length) * 2) + 6;
                }
                while(tot < 25){
                    System.out.print(" ");
                    tot++;
                }
                System.out.print("║");
            }
            System.out.println();
        }

    }

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

    private String typeToColorASCI(Type t){
        switch (t){
            case GREEN:
                return (Color.ANSI_YELLOW.escape() + "/" + Color.ANSI_WHITE.escape());
            case BLUE:
                return (Color.ANSI_RED.escape() + "/" + Color.ANSI_WHITE.escape());
            case PURPLE:
                return (Color.ANSI_PURPLE.escape() + "/" + Color.ANSI_WHITE.escape());
            case YELLOW:
                return (Color.ANSI_CYAN.escape() + "/" + Color.ANSI_WHITE.escape());
            default: return null;
        }
    }

}
