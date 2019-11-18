package corndel.training.models;

import corndel.training.corndel.training.services.ParkingBillingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractVehicle {

    private String registrationNumber;
    private Space space;
    private LocalDateTime parkingTime;
    private LocalDateTime leavingTime;
    private float parkingCharge = 00.00F;
    private Driver driver;

    AbstractVehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setSpace(Space space) { this.space = space; }
    public Space getSpace() { return space; }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public LocalDateTime getParkingTime() { return parkingTime; }
    public void setParkingTime(LocalDateTime parkingTime) { this.parkingTime = parkingTime; }
    public LocalDateTime getLeavingTime() { return leavingTime; }
    public void setLeavingTime(LocalDateTime leavingTime) { this.leavingTime = leavingTime; }
    public Driver getDriver() { return driver; }
    public void setDriver(Driver driver) { this.driver = driver; }
    public float getParkingCharge() { return parkingCharge; }
    public void setParkingCharge(float parkingCharge) {
        this.parkingCharge = parkingCharge;
    }

    private boolean canLeave(Space space) {
        return (space.getParkingVehicle() == this);

    }

    private void leave(Space space) {
        if(space.getParkingVehicle() == this) {
            space.setParkingVehicle(null);
            space.setFree(true);
        }
    }

    public void displaySuccessfulParking(Space space) {
        System.out.println(this + " parked to the space with id: " + space.getId() + " (size: " + space.getSize() +
                " | height: " + space.getHeight() + "). Parking Date/Time: " + formatDateTime(this.getParkingTime()));
    }

    public void displayUnsuccessfulParking(Space space) {
        if(space.isFree()) {
            System.out.println(this + " couldn't park to the space with id: " + space.getId() +
                    " (size: " + space.getSize() + " | height: " + space.getHeight() +
                    ") => Size/Height is too small/low " + "for this vehicle.");
        } else {
            System.out.println("Space with id: " + space.getId() + " is not free. => " + this + " couldn't park there!");
        }
    }

    private void displaySuccessfulLeaving(Space space) {
        System.out.println(this + " left the space with id: " + space.getId() + ". Leaving Date/Time: : " +
                formatDateTime(this.getLeavingTime()) + ". Parking cost: £" + this.getParkingCharge() +
                " (duration: " + ((ParkingBillingService.calculateParkingDuration(this) / 60) + 1) + " hours)."
                + " Driver's balance: £" + this.getDriver().getBalance() + " | debt: £" + this.getDriver().getDebt() );
    }

    private void displayUnsuccessfulLeaving(Space space) {
            System.out.println(this + " can't leave the space with id: " + space.getId() +
                    " => it's not parking there!");
    }

    public void displayStatus() {
        if(this.getSpace() == null) {
            System.out.println(this + " it's not parking in the garage.");
        } else {
            System.out.println(this + " is parking at the space with id: " + space.getId());
        }
    }

    public void tryToPark(Space space) {
        System.out.println("This line is left to help with debugging => it should not print, as this method" +
                "should be overwritten by the sub-classes");
    }

    public void tryToLeave(Space space) {
        if(this.canLeave(space)) {
            this.setLeavingTime(LocalDateTime.now());
            ParkingBillingService.chargeDriver(this);
            this.leave(space);
            this.setSpace(null);
            space.setParkingVehicle(null);
            this.displaySuccessfulLeaving(space);
        } else {
            this.displayUnsuccessfulLeaving(space);
        }
    }

    private String formatDateTime(LocalDateTime leavingTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return leavingTime.format(formatter);
    }

}
