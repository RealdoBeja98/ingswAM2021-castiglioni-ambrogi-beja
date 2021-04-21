https://github.com/GetnaG/ing-sw-2019-abbo-attolini-bentej/blob/master/deliverables/Communication%20Protocol.pdf
\
**Phases**\
Join game phase\
Turn phase\
\
**Join game phase**\
\
{"command" = "Ping\n"}\
{"message" = "Pong\n"}\
\
{"command" = "JoinGame\n", "name" = "name""}\
{"message" = "JoinedGame\n"}\
{"message" = "CreatedGame\n"}\
{"message" = "NameAlreadyUsed\n"}\
\
{"command" = "SelectNumberOfPlayers\n", "NumberOfPlayers" = 4}\
{"message" = "NumberOfPlayersSet"}\
\
**Turn phase**\
\
{"command" = "SendInitialState\n"}\
{"message" = "InitialState", "players" = ["name" = "name", "inkwell" = true, "leaderCardInHand" = ["leaderCard" = ["victoryPoints" = 3, "leaderCardType" = "whatIAm"], "discountLeaderCard" = ["discount" = "resource", "costOfLeaderCard" = ["cost" = "type"]], "extraStorageLeaderCard" = ["costOfLeaderCard" = "resource", "storageType" = "resource"], "productionPowerLeaderCard" = ["costOfLeaderCard" = "type", "requirement" = "resource"], "whiteMarbleLeaderCard" = ["costOfLeaderCard" = ["cost" = "type"], "whiteMarble" = "marble"]]], "marketExtraMarble" = "marble", "marketTray" = [["marble" = "marble"]], "gameIndex" = 0, "leaderDeck" = ["leaderCard" = ["victoryPoints" = 3, "leaderCardType" = "whatIAm"], "discountLeaderCard" = ["discount" = "resource", "costOfLeaderCard" = ["cost" = "type"]], "extraStorageLeaderCard" = ["costOfLeaderCard" = "resource", "storageType" = "resource"], "productionPowerLeaderCard" = ["costOfLeaderCard" = "type", "requirement" = "resource"], "whiteMarbleLeaderCard" = ["costOfLeaderCard" = ["cost" = "type"], "whiteMarble" = "marble"]], "developmentDeck" = [[["developmentCard" = ["cost" = ["cost" = "resource"], "costNumber" = ["cost" = 0], "type" = "type", "level" = 1, "requirements" = ["requirements" = "resource"], "costRequirements" = ["cost" = 0], "products" = ["product" = "resource"], "costProducts" = ["cost" = 0], "victoryPoints" = 1]]]]}\
\
{"command" = "sendActionTokenDeck\n"}\
{"message" = "ActionTokenDeck", "actionTokenDeck" = [actionToken = "type"]}\
