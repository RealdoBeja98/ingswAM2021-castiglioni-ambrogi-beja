package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.ResourceAlreadyPresentOtherShelfErrorMessage;

/**
 * Class of a created exception
 */
public class ResourceAlreadyPlacedException extends MessageException {

    /**
     * Constructor of the class
     */
    public ResourceAlreadyPlacedException(){
        super("This resource is already present in another shelf");
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return ResourceAlreadyPresentOtherShelfErrorMessage; of type ErrorMessage
     */
    @Override
    public ErrorMessage getErrorMessage() {
        return new ResourceAlreadyPresentOtherShelfErrorMessage();
    }

}
