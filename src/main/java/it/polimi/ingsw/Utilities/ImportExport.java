package it.polimi.ingsw.Utilities;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Market.Marbles.*;

public class ImportExport {

    /**
     * This method exports a Resource to a string
     * @param resource the Resource to export
     * @return a String, of type String
     */
    public static String exportResource(Resource resource){
        if(resource == null){
            return " ";
        }
        switch (resource){
            case FAITH: return  "0";
            case COIN: return "1";
            case STONE: return "2";
            case SERVANT: return "3";
            case SHIELD: return "4";
            case WHITE: return "5";
            default: return "";
        }
    }

    /**
     * This method exports a Type to a string
     * @param type the Type to export
     * @return a String, of type String
     */
    public static String exportType(Type type){
        switch (type){
            case GREEN: return "g";
            case BLUE: return "b";
            case YELLOW: return "y";
            case PURPLE: return "p";
            default: return "";
        }
    }

    /**
     * This method imports a string to a Marble
     * @param resource is the String representing the Marble to import
     * @return a Marble, of type Marble
     */
    public static Marble importMarble(String resource){
        switch (resource){
            case "0": return new Faith();
            case "1": return new Coin();
            case "2": return new Stone();
            case "3": return new Servant();
            case "4": return new Shield();
            case "5": return new White();
            default: return null;
        }
    }

    /**
     * This method imports a string to a Resource
     * @param resource is the String representing the Resource to import
     * @return a Resource, of type Resource
     */
    public static Resource importResource(String resource){
        switch (resource){
            case "0": return Resource.FAITH;
            case "1": return Resource.COIN;
            case "2": return Resource.STONE;
            case "3": return Resource.SERVANT;
            case "4": return Resource.SHIELD;
            case "5": return Resource.WHITE;
            default: return null;
        }
    }

    /**
     * This method imports a string to a Type
     * @param type is the String representing the Type to import
     * @return a Type, of type Type
     */
    public static Type importType(String type){
        switch (type){
            case "g": return Type.GREEN;
            case "b": return Type.BLUE;
            case "y": return Type.YELLOW;
            case "p": return Type.PURPLE;
            default: return null;
        }
    }

}
