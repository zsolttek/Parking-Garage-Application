package corndel.training.models;

import java.time.LocalDateTime;

public class Motorbike extends AbstractVehicle implements Parking {

    public Motorbike(String registrationNumber) {
        super(registrationNumber);
    }

    public boolean canPark(Space space) {
        return space.isFree();
    }

    @Override
    public String toString() {
        return ("Motorbike [" + getRegistrationNumber() + "]");
    }

    public void tryToPark(Space space) {
        if(this.canPark(space)) {
            space.setParkingVehicle(this);
            space.setFree(false);
            this.setSpace(space);
            this.setParkingTime(LocalDateTime.now().minusHours(1));
            this.displaySuccessfulParking(space);
        } else {
            this.displayUnsuccessfulParking(space);
        }
    }
}
