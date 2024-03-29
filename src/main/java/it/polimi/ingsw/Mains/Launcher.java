package it.polimi.ingsw.Mains;
import java.io.IOException;

/**
 * Is the main of client
 * Passes the arguments to other classes and starts the method
 */
public class Launcher {

    /**
     * This method starts the server
     * @param args string
     */
    private static void startServer(String[] args){
        ServerMain server = new ServerMain();
        server.main(args);
    }

    /**
     * This method starts the client
     * @param args string
     */
    private static void startClient(String[] args){
        ClientMain clientMain = new ClientMain();
        try {
            clientMain.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This message does the atoi to a string
     * @param str: the string to convert
     * @return the corresponding int, of type int
     */
    private static int strToInt(String str){
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
    }

    /**
     * This method checks from the server the string given
     * @param args string
     * @return true or false
     */
    private static boolean checkServerParam(String[] args){
        if(args.length != 1 && args.length != 3){
            System.out.println("Invalid number of parameter!");
            return true;
        }
        try {
            if (strToInt(args[2]) == -1) {
                System.out.println("Invalid port number!");
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Starting server with default ip and port!");
        }
        return false;
    }

    /**
     * This method checks the client parameter
     * @param args string
     * @return true or false
     */
    private static boolean checkClientParam(String[] args){
        if(args.length != 6){
            System.out.println("Invalid number of parameter!");
            return true;
        }
        if(strToInt(args[1]) == -1){
            System.out.println("Invalid port number!");
            return true;
        }
        if(!args[2].equals("-n") && !args[2].equals("-o") && !args[2].equals("-r")){
            System.out.println("Invalid joining choice!");
            return true;
        }
        if(args[2].equals("-n") && (strToInt(args[3]) < 1 || strToInt(args[3]) > 4)){
            System.out.println("Invalid number of player, choose between 1 and 4!");
            return true;
        }
        if(strToInt(args[3]) == -1){
            System.out.println("Invalid game index!");
            return true;
        }
        if(args[4].length() > 20){
            System.out.println("Invalid nickname, too long!");
            return true;
        }
        if(!args[5].equals("-GUI") && !args[5].equals("-CLI")){
            System.out.println("Invalid interface!");
            return true;
        }
        return false;
    }

    /**
     * This method checks the mode given by player
     * @param args string
     * @return true or false
     */
    private static boolean checkLocalParam(String[] args){
        if(!args[2].equals("-GUI") && !args[2].equals("-CLI")){
            System.out.println("Invalid interface!");
            return false;
        }
        return true;
    }

    /**
     * This method starts the local game
     * @param args string
     */
    private static void startLocal(String[] args){
        LocalMain localMain = new LocalMain();
        localMain.main(args);
    }

    /**
     * Main method which player can give all the parameters needed
     * @param args string
     */
    public static void main(String[] args) {
        if(args.length != 0){
            if(args[0].equals("server")){
                if(checkServerParam(args)){
                    return;
                }
                startServer(args);
                return;
            }
        }else{
            System.out.println("Please write the correct number parameters!");
            return;
        }

        if(args[0].equals("local")){
            if(checkLocalParam(args)){
                startLocal(args);
                return;
            }
            System.out.println("Invalid local match");
            return;
        }

        if(checkClientParam(args)){
            return;
        }
        startClient(args);
    }

}
