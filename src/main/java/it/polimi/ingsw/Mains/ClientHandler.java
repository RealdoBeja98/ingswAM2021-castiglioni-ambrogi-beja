package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private Game game;
    private String nickname;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    private void forward(String message, PrintWriter toExclude){
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line.equals("-n")) {
            boolean create = false;
            try {
                create = creatingGame(line, in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!create) {
                return;
            }
        }
        if (line.equals("-o")) {
            boolean old = false;
            try {
                old = joiningGame(line, in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!old) {
                return;
            }
        }
        /*if (!game.isGameStarted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        System.out.println("sleeping");
        /*
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("woke up"); //blocco per testare, rimuovere dopo wait fix also dobbiamo ridurre cpu usage


        while (true) { //questo pezzo consuma troppo
            if (game.getTurn().getCurrentPlayer().getNickname().equals(nickname) && game.getTurn().getDisplay()) {
                out.println("It's your turn!");
                game.getTurn().setDisplay();
            }

            try {
                if (in.ready()) {
                    line = in.readLine();
                    if (line.equals("quit")) {
                        forward(nickname+" quited", out);
                        break;
                    } else if (!game.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
                        out.println("ERROR_NOT_YOUR_TURN");
                    } else {
                        String[] message = line.split(" ");

                        if (message[0].equals("CHOOSE_DISCARD_LEADER_CARD")) {
                            try {
                                game.getTurn().chooseDiscardLeaderCard();
                                out.println("CONFIRMED_ACTION");
                                System.out.println("CHOOSE_DISCARD_LEADER_CARD");
                            } catch (NoLeaderCardToDiscardException e) {
                                out.println("ERROR_NO_CARDS_DISCARD");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            }
                        } else if (message[0].equals("DISCARD_LEADER_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().discardLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("DISCARD_LEADER_CARD");
                            } catch (AlreadyDiscardedThisLeaderCardException e) {
                                out.println("ERROR_ALREADY_DISCARDED_POSITION");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("CHOOSE_PLAY_LEADER_CARD")) {
                            try {
                                game.getTurn().choosePlayLeaderCard();
                                out.println("CONFIRMED_ACTION");
                                System.out.println("CHOOSE_PLAY_LEADER_CARD");
                            } catch (NoLeaderCardToPlayException e) {
                                out.println("ERROR_NO_CARDS_PLAY");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            }
                        } else if (message[0].equals("PLAY_LEADER_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().playLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("PLAY_LEADER_CARD");
                            } catch (NotSatisfiedRequirementsForThisLeaderCardException e) {
                                out.println("ERROR_REQUIREMENTS");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("CHOOSE_NO_ACTION_LEADER_CARD")) {
                            try {
                                game.getTurn().chooseNoActionLeaderCard();
                                out.println("CONFIRMED_ACTION");
                                System.out.println("CHOOSE_NO_ACTION_LEADER_CARD");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            }
                        } else if (message[0].equals("SELECT_NORMAL_ACTION")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().selectNormalAction(NormalAction.valueOf(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("SELECT_NORMAL_ACTION");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("TAKE_RESOURCES_FROM_THE_MARKET")) {
                            try {
                                checkLength(message, 3);
                                game.getTurn().takeResourcesFromTheMarket(RowColumn.valueOf(message[1]), atoi(message[2]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("TAKE_RESOURCES_FROM_THE_MARKET");
                                int size = game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().size();
                                for (int i = 0; i < size; i++) {
                                    out.println(game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().get(i));
                                }
                                forward("UPDATE_MARKET "+message[1]+" "+message[2], out);
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            } catch (NullEnumException e) {
                                out.println("ERROR_INVALID_ENUM");
                            }
                        } else if (message[0].equals("ADD_RESOURCE_TO")) {
                            try {
                                boolean weAre = false;
                                try {
                                    checkLength(message, 1);
                                } catch (IllegalArgumentException e){
                                    weAre = true;
                                }
                                if(weAre == false){
                                    throw new IllegalArgumentException();
                                }
                                if(message[1].equals("DISCARD")){
                                    checkLength(message, 2);
                                    game.getTurn().addResource(LeaderWarehouse.DISCARD, 0);
                                    out.println("CONFIRMED_ACTION");
                                    System.out.println("ADD_RESOURCE_TO");
                                    forward("ADVANCE_FAITH_TRACK", out);
                                } else {
                                    checkLength(message, 3);
                                    game.getTurn().addResource(LeaderWarehouse.valueOf(message[1]), atoi(message[2]));
                                    out.println("CONFIRMED_ACTION");
                                    System.out.println("ADD_RESOURCE_TO");
                                }
                            } catch (NoResourceToAddException e) {
                                out.println("ERROR_NO_RESOURCE_A");
                            } catch (DifferentStorageException e) {
                                out.println("ERROR_DIFFERENT_STORAGE_TYPE");
                            } catch (OccupiedSlotExtraStorageLeaderCardException e) {
                                out.println("ERROR_OCCUPIED_SLOT_LC");
                            } catch (PositionAlreadyOccupiedException e) {
                                out.println("ERROR_OCCUPIED_SLOT_WD");
                            } catch (ResourceAlreadyPlacedException e) {
                                out.println("ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF");
                            } catch (DifferentResourceInThisShelfException e) {
                                out.println("ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT");
                            } catch (UnexpectedWhiteMarbleException e) {
                                out.println("ERROR_WHITE_MARBLE");
                            } catch (UnexpectedFaithMarbleException e) {
                                out.println("ERROR_FAITH_MARBLE");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("CHANGE_WHITE_MARBLE_WITH")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().changeWhiteMarbleWith(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("CHANGE_WHITE_MARBLE_WITH");
                            } catch (NoWhiteMarbleException e) {
                                out.println("ERROR_NO_WHITE_MARBLE");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            } catch (NoWhiteMarbleLeaderCardException e) {
                                out.println("ERROR_NO_WHITE_MARBLE");
                            }
                        } else if (message[0].equals("BUY_DEVELOPMENT_CARD")) {
                            try {
                                checkLength(message, 3);
                                game.getTurn().buyADevelopmentCard(atoi(message[1]), atoi(message[2]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("BUY_DEVELOPMENT_CARD");
                                forward("UPDATE_DEVELOPMENT_CARD "+message[1]+" "+message[2], out);
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (SelectedADevelopmentCardYetException e) {
                                out.println("ERROR_ALREADY_SELECTED_SOMETHING");
                            } catch (NotAbleToBuyThisDevelopmentCardException e) {
                                out.println("ERROR_NOT_ENOUGH_RESOURCES");
                            } catch (DrawnFromEmptyDeckException e) {
                                out.println("ERROR_EMPTY_DECK");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (NotAbleToPlaceThisDevelopmentCardException e) {
                                out.println("ERROR_INVALID_SELECTION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("PAY_WITH_STRONGBOX")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().payWithStrongBox(Resource.valueOf(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("PAY_WITH_STRONGBOX");
                            } catch (WrongPaymentException e) {
                                out.println("ERROR_WRONG_RESOURCE");
                            } catch (NegativeResourceException e) {
                                out.println("ERROR_MISSING_RESOURCE");
                            } catch (NotAResourceForStrongBoxException e) {
                                out.println("ERROR_NOT_STRONGBOX");
                            } catch (NoResourceToPayException e) {
                                out.println("ERROR_NO_RESOURCE_P");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("PAY_WITH_WAREHOUSE_DEPOTS")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().payWithWarehouseDepots(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("PAY_WITH_WAREHOUSE_DEPOTS");
                            } catch (WrongPaymentException e) {
                                out.println("ERROR_WRONG_RESOURCE");
                            } catch (EmptySlotYetException e) {
                                out.println("ERROR_ALREADY_EMPTY");
                            } catch (NoResourceToPayException e) {
                                out.println("ERROR_NO_RESOURCE_P");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("PAY_WITH_EXTRA_STORAGE_LEADER_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().payWithExtraStorageLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("PAY_WITH_EXTRA_STORAGE_LEADER_CARD");
                            } catch (NotAnExtraStorageLeaderCardException e) {
                                out.println("ERROR_NOT_ES");
                            } catch (WrongPaymentException e) {
                                out.println("ERROR_WRONG_RESOURCE");
                            } catch (EmptySlotExtraStorageLeaderCardException e) {
                                out.println("ERROR_EMPTY_SLOT_ES");
                            } catch (NoResourceToPayException e) {
                                out.println("ERROR_NO_RESOURCE_P");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("PLACE_DEVELOPMENT_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().placeDevelopmentCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("PLACE_DEVELOPMENT_CARD");
                            } catch (NoDevelopmentCardToObtainException e) {
                                out.println("ERROR_NO_CARD_OBTAINABLE");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("SELECT_PRODUCTION_DEVELOPMENT_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().selectProductionDevelopmentCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("SELECT_PRODUCTION_DEVELOPMENT_CARD");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            } catch (NoDevelopmentCardInThisPositionException e) {
                                out.println("ERROR_NO_DEVELOPMENT_CARD");
                            }
                        } else if (message[0].equals("SELECT_PRODUCTION_POWER_LEADER_CARD")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().selectProductionPowerLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("SELECT_PRODUCTION_POWER_LEADER_CARD");
                            } catch (NoProductionLeaderCardException e) {
                                out.println("ERROR_NO_PLC");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("SELECT_DEFAULT_PRODUCTION_POWER")) {
                            try {
                                game.getTurn().selectDefaultProductionPower();
                                out.println("CONFIRMED_ACTION");
                                System.out.println("SELECT_DEFAULT_PRODUCTION_POWER");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            }
                        } else if (message[0].equals("OBTAIN_GENERIC_RESOURCE")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().obtainGenericResource(Resource.valueOf(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("OBTAIN_GENERIC_RESOURCE");
                            } catch (NoGenericResourceToObtainException e) {
                                out.println("ERROR_GENERIC_RESOURCE");
                            } catch (NotAResourceForStrongBoxException e) {
                                out.println("ERROR_NOT_STRONGBOX");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("START_PAYMENT")) {
                            try {
                                game.getTurn().startPayment();
                                out.println("CONFIRMED_ACTION");
                                System.out.println("START_PAYMENT");
                            } catch (NotEnoughResourcesException e) {
                                out.println("ERROR_NOT_ENOUGH_R");
                            } catch (YouHaveNotSelectedAnyProductionException e) {
                                out.println("ERROR_NO_SELECTED_POWERS");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            } catch (GameEndedException e) {
                                out.println("ERROR_GAME_ENDED");
                            }
                        } else if (message[0].equals("DRAW_SOLO_ACTION_TOKEN")) {
                            try {
                                ActionToken actionToken = game.getTurn().drawSoloActionToken();
                                out.println("UPDATE_SOLO_ACTION_TOKEN "+actionToken);
                                System.out.println("DRAW_SOLO_ACTION_TOKEN");
                            } catch (ActionNotAllowedException e) {
                                out.println("ERROR_INVALID_ACTION");
                            }
                        } else if (message[0].equals("SELECT_A_WAREHOUSE_DEPOTS_SLOT")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().selectAWarehouseDepotsSlot(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("SELECT_A_WAREHOUSE_DEPOTS_SLOT");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().moveResourcesInWarehouseDepots(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS");
                            } catch (NotAdmittedMovementException e) {
                                out.println("ERROR_INVALID_MOVEMENT");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("MOVE_RESOURCES_WAREHOUSE_TO_ES_LC")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("MOVE_RESOURCES_WAREHOUSE_TO_ES_LC");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (NotAnExtraStorageLeaderCardException e) {
                                out.println("ERROR_NOT_ES");
                            } catch (EmptySlotYetException e) {
                                out.println("ERROR_ALREADY_EMPTY");
                            } catch (OccupiedSlotExtraStorageLeaderCardException e) {
                                out.println("ERROR_OCCUPIED_SLOT_LC");
                            } catch (DifferentStorageException e) {
                                out.println("ERROR_DIFFERENT_STORAGE_TYPE");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else if (message[0].equals("MOVE_RESOURCE_ES_LC_TO_WAREHOUSE")) {
                            try {
                                checkLength(message, 2);
                                game.getTurn().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(atoi(message[1]));
                                out.println("CONFIRMED_ACTION");
                                System.out.println("MOVE_RESOURCE_ES_LC_TO_WAREHOUSE");
                            } catch (PositionInvalidException e) {
                                out.println("ERROR_INVALID_POSITION");
                            } catch (NotAnExtraStorageLeaderCardException e) {
                                out.println("ERROR_NOT_ES");
                            } catch (PositionAlreadyOccupiedException e) {
                                out.println("ERROR_OCCUPIED_SLOT_WD");
                            } catch (ResourceAlreadyPlacedException e) {
                                out.println("ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF");
                            } catch (DifferentResourceInThisShelfException e) {
                                out.println("ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT");
                            } catch (EmptySlotExtraStorageLeaderCardException e) {
                                out.println("ERROR_EMPTY_SLOT_ES");
                            } catch (IllegalArgumentException e) {
                                out.println("ERROR_TYPO");
                            }
                        } else {
                            out.println("ERROR_TYPO");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Player quit the game");
        return;
    }

    private void checkLength(String[] message, int n) {
        if(message.length != n){
            throw new IllegalArgumentException();
        }
    }

    private int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            throw new IllegalArgumentException();
        }
    }

    private boolean creatingGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Creating a new game...");
        line = in.readLine();
        int numPL = atoi(line);
        game = new Game(numPL);
        System.out.println("Created game number: " + game.getGameIndex());
        return addingPlayer(in, out);
    }



    private boolean joiningGame(String line, BufferedReader in, PrintWriter out) throws IOException {
        line = in.readLine();
        int numGM = atoi(line);
        System.out.println("Joining a game...");
        game = Game.get(numGM);
        System.out.println("Someone joined game number: " + game.getGameIndex());
        return addingPlayer(in, out);
    }

    private boolean addingPlayer(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        out.println(game.getGameIndex());
        line = in.readLine();
        try {
            game.addPlayer(line, this);
            game.addSocket(out);
            nickname = line;
            out.println("PLAYER_ADDED");
            return true;
        } catch (NameAlreadyRegisteredException e) {
            System.out.println("Name already taken, rejecting the player");
            out.println("ERROR_NAME_TAKEN");
            return false;
        } catch (GameAlreadyStartedException e) {
            System.out.println("Game already started, rejecting the player");
            out.println("ERROR_GAME_STARTED");
            return false;
        }
    }

}
