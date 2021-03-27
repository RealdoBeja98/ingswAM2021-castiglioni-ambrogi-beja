package it.polimi.ingsw.Game;

import it.polimi.ingsw.PersonalBoard.PersonalBoard;

public class Player {

    private String nickname;
    private PersonalBoard personalBoard = new PersonalBoard();

    public Player(String nickname){
        this.nickname = nickname;
    }

    public PersonalBoard getPersonalBoard(){
        return personalBoard;
    }

}
