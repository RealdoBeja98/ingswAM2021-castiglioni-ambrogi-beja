package it.polimi.ingsw.PersonalBoard;
import it.polimi.ingsw.Resource;

public class WarehouseDepots {
    private Resource[] resource = new Resource[6];

    protected WarehouseDepots() {
       for (Resource n : resource){
           n = null;
       }
    }

    public void addResource(Resource r, int pos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException {
        if(pos >= 7){
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
                resource[pos] = r;
            }
        }
    }

    public void moveResource(int startPos, int finalPos) throws PositionAlreadyOccupiedException, ResourceAlreadyPlacedException {
        if(finalPos >= 7){
            throw new IndexOutOfBoundsException();
        }
        if(resource[finalPos] != null){
            throw new PositionAlreadyOccupiedException();
        }
        else{
            if(startPos == 0 && (finalPos ==1 || finalPos ==2) && ((resource[1] != null && resource[2] == null) || (resource[1] == null && resource[2] != null) || (resource[1] != null && resource[2] != null))) {
                throw new ResourceAlreadyPlacedException();
            } // non prende in considerazione se shelf 1 ha un coin, shelf two has 1 servant and blank, it can't change servant with coin }
            else if((startPos == 1 || startPos == 2) && (finalPos == 3 || finalPos == 4 || finalPos == 5) && ((resource[3] != null && resource[4] !=null && resource[5] == null) ||
                    (resource[3] == null && resource[4] != null && resource[5] != null) || (resource[3] != null && resource[4] != null && resource[5] != null) || (resource[3] != null || resource[4] != null || resource[5] != null))){
                throw new ResourceAlreadyPlacedException();
            }// non prende in considerazione se shelf 1 ha un coin, shelf two has 1 servant and blank, it can't change servant with coin
            else if((startPos == 3 || startPos == 4 || startPos == 5) && (finalPos ==1 || finalPos ==2) && ((resource[1] != null && resource[2] == null) || (resource[1] == null && resource[2] != null) || (resource[1] != null && resource[2] != null))){
                throw new ResourceAlreadyPlacedException();
            }// non prende in considerazione se shelf 1 ha un coin, shelf two has 1 servant and blank, it can't change servant with coin
            else{
                resource[finalPos] = resource[startPos];
                resource[startPos] = null;
            }
        }
    }

    public Resource removeResource(int pos){
        Resource temp = resource[pos];
        resource[pos] = null;
        return temp;
    }
}


