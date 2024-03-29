package it.polimi.ingsw.PersonalBoard.Warehouse;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Utilities.ImportExport;

/**
 * This Class represents the warehouse depots associated to a player
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
     * @throws PositionAlreadyOccupiedException if the position chosen is already occupied
     * @throws ResourceAlreadyPlacedException if the resource passed is present on a different shelf
     * @throws DifferentResourceInThisShelfException if there are different resources types already
     *                                                placed in the chosen shelf
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     */
    public void addResource(Resource r, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, IndexOutOfWarehouseDepotsException {
        if(pos >= 6 || pos < 0){
            throw new IndexOutOfWarehouseDepotsException();
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
                if(pos == 3 && (resource[4] != r && resource[4] != null || resource[5] != r && resource[5] != null)){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 4 && (resource[3] != r && resource[3] != null || resource[5] != r && resource[5] != null)){
                    throw new DifferentResourceInThisShelfException();
                }
                if(pos == 5 && (resource[3] != r && resource[3] != null || resource[4] != r && resource[4] != null)){
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
     * @throws NotAdmittedMovementException if the result is not respecting the warehouse rules
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     */
    public void moveResource(int startPos, int finalPos) throws NotAdmittedMovementException, IndexOutOfWarehouseDepotsException {
        if(finalPos >= 6 || finalPos < 0 || startPos >= 6 || startPos < 0){
            throw new IndexOutOfWarehouseDepotsException();
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
     * This method return the first element not null of the array or return null if all elements of the array are null
     * @param array: the array
     * @param start: the first index to consider of the array
     * @param finish: the index then the last index to consider of the array
     * @return the first element not null of the array or null
     */
    private Resource firstNotNull(Resource[] array, int start, int finish){
        Resource result;
        for(int i = start; i < finish; i++){
            result = array[i];
            if(result != null){
                return result;
            }
        }
        return null;
    }

    /**
     * This method checks if the copied array, which has the element already swapped, respects all the rules
     * @return true if all the condition are verified, of type Boolean
     */
    private Boolean isCorrect(){
        if(resource[1] != resource[2] && resource[1] != null && resource[2] != null){
            return false;
        }
        Resource thirdShelf = firstNotNull(resource, 3, 6);
        if(thirdShelf == null){
            return true;
        }
        return (resource[4] == null || resource[4] == thirdShelf) && (resource[5] == null || resource[5] == thirdShelf);
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
     * @throws EmptySlotYetException : if the slot was empty
     */
    public void removeResource(int pos) throws EmptySlotYetException {
        if(resource[pos] == null){
            throw new EmptySlotYetException();
        }
        resource[pos] = null;
    }

    /**
     * This method exports the WarehouseDepots to a String
     * @return a string with all the WarehouseDepots data, of type String
     */
    public String export(){
        String exportedString = "";
        for(Resource singleResource : resource){
            String toAdd = ImportExport.exportResource(singleResource);
            exportedString = new StringBuilder().append(exportedString).append(toAdd + "!").toString();
        }
        return exportedString;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public WarehouseDepots(String importedString){
        resource = new Resource[6];
        String[] strings = importedString.split("!");
        for(int i = 0; i < 6; i++){
            resource[i] = ImportExport.importResource(strings[i]);
        }
    }

}


