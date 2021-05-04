package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class DifferentStorageTypeErrorMessage extends ErrorMessage {

    public DifferentStorageTypeErrorMessage(){
        identifier = "ERROR_DIFFERENT_STORAGE_TYPE";
    }

    @Override
    public String toString(){
        return "Error: you've selected a different storage type!";
    }

}
