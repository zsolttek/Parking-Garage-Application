package corndel.training.models;

import java.time.LocalDateTime;

public class Car extends AbstractVehicle implements Parking {

    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    public boolean canPark(Space space) {
        return (!(space.getSize().equals("small")) && !(space.getHeight().equals("low")) && space.isFree());
    }

    @Override
    public String toString() {
        return ("Car [" + getRegistrationNumber() + "]");
    }

    public void tryToPark(Space space) {
        if(this.canPark(space)) {
            space.setParkingVehicle(this);
            space.setFree(false);
            this.setSpace(space);
            this.setParkingTime(LocalDateTime.now().minusHours(2));
            this.displaySuccessfulParking(space);
        } else {
            this.displayUnsuccessfulParking(space);
        }
    }
}
