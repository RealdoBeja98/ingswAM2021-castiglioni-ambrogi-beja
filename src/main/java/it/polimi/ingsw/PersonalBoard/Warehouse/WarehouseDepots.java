package it.polimi.ingsw.PersonalBoard.Warehouse;
import it.polimi.ingsw.Resource;

public class WarehouseDepots {
    private Resource[] resource = new Resource[6];

    public WarehouseDepots() {
       for (Resource n : resource){
           n = null;
       }
    }

    public Resource[] getResource(){
        return resource;
    }

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

    public void moveResource(int startPos, int finalPos) throws IndexOutOfBoundsException, NotAdmittedMovementException {
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

    private WarehouseDepots copy(){
        WarehouseDepots result = new WarehouseDepots();
        result.resource = this.resource.clone();
        return result;
    }

    public void removeResource(int pos){
        resource[pos] = null;
    }
}


