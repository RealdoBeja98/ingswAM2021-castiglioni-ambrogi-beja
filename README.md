Masters of Renaissance

useful links: 

https://gluonhq.com/products/javafx/
javaFX 15 or above is recommended

https://www.oracle.com/it/java/technologies/javase-downloads.html
java JDK 15 or above is recommended

To start the server:
cd to the folder where the jar is stored, then type in the shell:

java -jar AM04.jar server

This will open a server with default options (ip: localhost, port: 25565).

java -jar AM04.jar server [personalized_ip] [personalized_port]

This will open a server on  the specified ip and port.

To start the client:

java -jar AM04.jar [server_ip] [server_port] [joining_method] [number_of_player/game_number] [nickname] [graphic_method]

joining method:
• -n create a new game
• -o join an existing game
• -r reconnect to an existing game

number_of_player/game_number:
• if the client has been launched with -n this specifies the number of player in the game
• if the client has been launched with -o or -r this specifies the game you want to join

graphic method:
• -GUI to start a graphic interface
• -CLI to start the command line interface

an example to start all the components:

java -jar AM04.jar server (starts the server)
java -jar AM04.jar localhost 25565 -n 2 andrea -CLI (starts the client and initializes a game with 2 players)
java -jar AM04.jar localhost 25565 -o 0 carlo -GUI (join the game 0 created by andrea)

IF THERE ARE TROUBLE RUNNING THE GUI USE:
java --module-path [path to javaFX] --add-modules javafx.controls -jar [path to the jar] [parameters]