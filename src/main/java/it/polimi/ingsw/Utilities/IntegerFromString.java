package it.polimi.ingsw.Utilities;

/**
 * class of integer from string
 */
public class IntegerFromString {

    /**
     * This method is to transform a string in a integer
     * @param str: the string to transform
     * @return the integer; of type int
     * @throws IllegalArgumentException if this operation fails
     */
    public static int f(String str){
        try{
            return Integer.parseInt(str);
        } catch(NumberFormatException ex) {
            throw new IllegalArgumentException();
        }
    }

}
