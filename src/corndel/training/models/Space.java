package corndel.training.models;

public class Space {
    private String size;
    private String height;
    private boolean free;
    private AbstractVehicle parkingVehicle;
    private int id;
    private static int counter = 1;

    public Space(String size, String height) {
        this.size = size;
        this.height = height;
        this.free = true;
        this.id = counter;
        counter ++;
    }

    public String getSize() {
        return size;
    }
    public String getHeight() {
        return height;
    }
    public int getId() { return id; }
    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
    public AbstractVehicle getParkingVehicle() {
        return parkingVehicle;
    }
    public void setParkingVehicle(AbstractVehicle parkingVehicle) {
        this.parkingVehicle = parkingVehicle;
    }
}
