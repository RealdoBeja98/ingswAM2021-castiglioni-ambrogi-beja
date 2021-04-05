package it.polimi.ingsw.PersonalBoard.Warehouse;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Enums.Resource;

/**
 * This Class represents the warehouse depots associate to a player
 */
public class WarehouseDepots {

    private Resource[] resource;

    /**
     * Constructor method of this class
     */
    public WarehouseDepots() {
        resource = new Resource[6];
    }

    /**
     * Getter of the parameter resource
     * @return a copy of the original array resource[], of type Resource[]
     */
    public Resource[] getResource(){
        WarehouseDepots copy = copy();
        return copy.resource;
    }

    /**
     * This method receives a resource and a position, and, after checking if the slot is free, the presence of the
     * same resource in another shelf and the presence of different resources on the same shelf, adds the first into the
     * array resource in the slot pointed by the second
     * @param r: type of resource to be added
     * @param pos: position of the array
     * @throws PositionAlreadyOccupiedException: if the position chosen is already occupied
     * @throws ResourceAlreadyPlacedException: if the resource passed is present on a different shelf
     * @throws DifferentResourceInThisShelfException: if there are different resources types already
     *                                                placed in the chosen shelf
     */
    public void addResource(Resource r, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException {
        if(pos >= 7 || pos < 0){
            throw new IndexOutOfBoundsException();
        }
        if(resource[pos] != null){
            throw new PositionAlreadyOccupiedException();
        }
        else{
            if(pos == 0 && (resource[1] == r || resource[2] == r || resource[3] == r || resource[4] == r || resource[5] == r)){
                throw new ResourceAlreadyPlacedException();
            }
            else if((pos == 1 || pos == 2) && (resource[0] == r || resource[3] == r || resource[4] == r || resource[5] == r)){
                throw new ResourceAlreadyPlacedException();
            }
            else if((pos == 3 || pos == 4 || pos == 5) && (resource[0] == r || resource[1] == r || resource[2] == r)){
                throw new ResourceAlreadyPlacedException();
            }else{
                if(pos == 1 && resource[2] != r && resource[2] != null){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 2 && resource[1] != r && resource[1] != null){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 3 && resource[4] != r && resource[4] != null && resource[5] != r && resource[5] != null){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 4 && resource[3] != r && resource[3] != null && resource[5] != r && resource[5] != null){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 5 && resource[3] != r && resource[3] != null && resource[4] != r && resource[4] != null){
                    throw new DifferentResourceInThisShelfException();
                }
                resource[pos] = r;
            }
        }
    }

    /**
     * This method receives two position, and, after checking if the move is possible on a copy, exchanges the resource present
     * in the first with the second
     * @param startPos: represents the first position
     * @param finalPos: represents the second position
     * @throws NotAdmittedMovementException: if the result is not respecting the warehouse rules
     */
    public void moveResource(int startPos, int finalPos) throws NotAdmittedMovementException {
        if(finalPos >= 7 || finalPos < 0 || startPos >= 7 || startPos < 0){
            throw new IndexOutOfBoundsException();
        }
        WarehouseDepots copy = copy();
        Resource temp = copy.resource[finalPos];
        copy.resource[finalPos] = copy.resource[startPos];
        copy.resource[startPos] = temp;
        if(copy.isCorrect()){
            resource[finalPos] = resource[startPos];
            resource[startPos] = temp;
        } else {
            throw new NotAdmittedMovementException();
        }
    }

    /**
     * This method checks if the copied array, which has the element already swapped, respects all the rules
     * @return true if all the condition are verified, of type Boolean
     */
    private Boolean isCorrect(){
        if(resource[1] != resource[2]){
            if(resource[1] != null && resource[2] != null){
                return false;
            }
        }
        Resource thirdShelf = resource[3];
        if(thirdShelf == null){
            thirdShelf = resource[4];
        }
        if(thirdShelf == null){
            thirdShelf = resource[5];
        }
        if(thirdShelf == null){
            return true;
        }
        if((resource[4] == null || resource[4] == thirdShelf) && (resource[5] == null || resource[5] == thirdShelf)){
            return true;
        }
        return false;
    }

    /**
     * This method creates a copy of this class
     * @return a copy, of type WarehouseDepots
     */
    private WarehouseDepots copy(){
        WarehouseDepots result = new WarehouseDepots();
        result.resource = this.resource.clone();
        return result;
    }

    /**
     * This method removes the resource in the pointed position
     * @param pos: position of the array
     * @throws YetEmptySlotException: if the slot was yet empty
     */
    public void removeResource(int pos) throws YetEmptySlotException{
        if(resource[pos] == null){
            throw new YetEmptySlotException();
        }
        resource[pos] = null;
    }

}


