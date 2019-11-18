package corndel.training.models;

import java.time.LocalDateTime;

public class Van extends AbstractVehicle implements Parking {

    public Van(String registrationNumber) {
        super(registrationNumber);
    }

    public boolean canPark(Space space) {
        return ((space.getSize().equals("large")) && (space.getHeight().equals("high")) && space.isFree());
    }

    @Override
    public String toString() {
        return ("Van [" + getRegistrationNumber() + "]");
    }

    public void tryToPark(Space space) {
        if(this.canPark(space)) {
            space.setParkingVehicle(this);
            space.setFree(false);
            this.setSpace(space);
            this.setParkingTime(LocalDateTime.now().minusHours(3));
            this.displaySuccessfulParking(space);
        } else {
            this.displayUnsuccessfulParking(space);
        }
    }
}
