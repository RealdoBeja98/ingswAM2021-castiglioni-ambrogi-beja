https://github.com/GetnaG/ing-sw-2019-abbo-attolini-bentej/blob/master/deliverables/Communication%20Protocol.pdf
https://codingjam.it/gson-da-java-a-json-e-viceversa-primi-passi/
https://stackoverflow.com/questions/29965764/how-to-parse-json-file-with-gson
\
PING
PONG
\
{"message" = "InitialState", "players" = ["name" = "name", "inkwell" = true], "marketExtraMarble" = "marble", "marketTray" = [["marble" = "marble"]], "gameIndex" = 0, "leaderDeck" = ["leaderCard" = ["victoryPoints" = 3, "leaderCardType" = "whatIAm"], "discountLeaderCard" = ["discount" = "resource", "costOfLeaderCard" = ["cost" = "type"]], "extraStorageLeaderCard" = ["costOfLeaderCard" = "resource", "storageType" = "resource"], "productionPowerLeaderCard" = ["costOfLeaderCard" = "type", "requirement" = "resource"], "whiteMarbleLeaderCard" = ["costOfLeaderCard" = ["cost" = "type"], "whiteMarble" = "marble"]], "developmentDeck" = [[["developmentCard" = ["cost" = ["cost" = "resource"], "costNumber" = ["cost" = 0], "type" = "type", "level" = 1, "requirements" = ["requirements" = "resource"], "costRequirements" = ["cost" = 0], "products" = ["product" = "resource"], "costProducts" = ["cost" = 0], "victoryPoints" = 1]]]]}\
\
CHOOSE_DISCARD_LEADER_CARD\
CHOOSE_PLAY_LEADER_CARD\
CHOOSE_NO_ACTION_LEADER_CARD\
DISCARD_LEADER_CARD 1\
PLAY_LEADER_CARD 1\
{"message" = "LeaderCardPlayed", "leaderCard" = ["victoryPoints" = 3, "leaderCardType" = "whatIAm"], "discountLeaderCard" = ["discount" = "resource", "costOfLeaderCard" = ["cost" = "type"]], "extraStorageLeaderCard" = ["costOfLeaderCard" = "resource", "storageType" = "resource"], "productionPowerLeaderCard" = ["costOfLeaderCard" = "type", "requirement" = "resource"], "whiteMarbleLeaderCard" = ["costOfLeaderCard" = ["cost" = "type"], "whiteMarble" = "marble"]}\
SELECT_NORMAL_ACTION NormalAction\
TAKE_RESOURCES_FROM_THE_MARKET RowColumn 1\
ADD_RESOURCE_TO LeaderWarehouse 0\
CHANGE_WHITE_MARBLE_WITH 1\
BUY_DEVELOPMENT_CARD
PAY_WITH_STRONGBOX Resource\
PAY_WITH_WAREHOUSE_DEPOTS 0\
PAY_WITH_EXTRA_STORAGE_LEADER_CARD 1\
PLACE_DEVELOPMENT_CARD 1\
SELECT_PRODUCTION_DEVELOPMENT_CARD 1\
SELECT_PRODUCTION_POWER_LEADER_CARD 1\
SELECT_DEFAULT_PRODUCTION_POWER\
OBTAIN_GENERIC_RESOURCE Resource\
START_PAYMENT\
DRAW_SOLO_ACTION_TOKEN\
{"message" = "ACTION_TOKEN_DECK", "actionTokenDeck" = [actionToken = "type"]}\
SELECT_A_WAREHOUSE_DEPOTS_SLOT 0\
MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS 0\
MOVE_RESOURCES_WAREHOUSE_TO_ES_LC 0\
MOVE_RESOURCE_ES_LC_TO_WAREHOUSE 0\
\
--MESSAGGI DI ERRORE--\
ERROR_NOT_YOUR_TURN\
ERROR_NO_CARDS_DISCARD\
ERROR_INVALID_ACTION\
ERROR_ALREADY_DISCARDED_POSITION\
ERROR_GAME_ENDED\
ERROR_TYPO\
ERROR_NO_CARDS_PLAY\
ERROR_REQUIREMENTS\
ERROR_NO_RESOURCE_A\
ERROR_DIFFERENT_STORAGE_TYPE\
ERROR_OCCUPIED_SLOT_LC\
ERROR_OCCUPIED_SLOT_WD\
ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF\
ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT\
ERROR_WHITE_MARBLE\
ERROR_FAITH_MARBLE\
ERROR_NO_WHITE_MARBLE\
ERROR_ALREADY_SELECTED_SOMETHING\
ERROR_NOT_ENOUGH_RESOURCES\
ERROR_EMPTY_DECK\
ERROR_INVALID_POSITION\
ERROR_INVALID_SELECTION\
ERROR_WRONG_RESOURCE\
ERROR_NOT_ENOUGH_R\
ERROR_MISSING_RESOURCE\
ERROR_NOT_STRONGBOX\
ERROR_NO_RESOURCE_P\
ERROR_ALREADY_EMPTY\
ERROR_NOT_ES\
ERROR_EMPTY_SLOT_ES\
ERROR_NO_CARD_OBTAINABLE\
ERROR_NO_PLC\
ERROR_GENERIC_RESOURCE\
ERROR_NO_SELECTED_POWERS\
ERROR_INVALID_MOVEMENT\
ERROR_NOT_ES\
ERROR_INVALID_ENUM\
ERROR_NO_DEVELOPMENT_CARD