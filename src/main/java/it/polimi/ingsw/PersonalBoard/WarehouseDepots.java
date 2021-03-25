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
}


