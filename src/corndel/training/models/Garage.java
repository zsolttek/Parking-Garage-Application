package corndel.training.models;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private Floor floor1;
    private Floor floor2;
    private Floor floor3;
    private List<Floor> floors = new ArrayList<>();
    private List<AbstractVehicle> parkingVehciles;

    public Garage(Floor floor1, Floor floor2, Floor floor3) {
        this.floor1 = floor1;
        this.floor2 = floor2;
        this.floor3 = floor3;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<AbstractVehicle> getParkingVehicles() {
        List<AbstractVehicle> currentlyParkingVehicles = new ArrayList<>();

        for(Floor floor : getFloors()) {
            for(Space space : floor.getSpaces()) {
                if(!space.isFree()) {
                    currentlyParkingVehicles.add(space.getParkingVehicle());
                }
            }
        }

        return currentlyParkingVehicles;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public List<Space> getFreeSpaces() {
        List<Space> freeSpaces = new ArrayList<>();
        for(Floor floor : floors) {
            for(Space space : floor.getSpaces()) {
                if(space.isFree()) {
                    freeSpaces.add(space);
                }
            }
        }
        return freeSpaces;
    }
}
