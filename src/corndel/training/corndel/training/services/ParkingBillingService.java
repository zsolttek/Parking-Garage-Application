package corndel.training.corndel.training.services;

import corndel.training.models.*;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;

public class ParkingBillingService {

    private static Banking bank = new BankWithAverageAPR();

    private static float ONE_HOUR_PARKING_COST = 2.50F;
    private static float TWO_HOURS_PARKING_COST = 6.50F;
    private static float THREE_HOURS_PARKING_COST = 10.00F;
    private static float EXTRA_HOUR_COST = 1.99F;
    private static float DISCOUNT_FOR_SMALL_VEHICLES = 10.00F; //percentage
    private static float EXTRA_CHARGE_FOR_LARGE_VEHICLES = 20.00F; //percentage
    private static float PENALTY_IF_DRIVER_UNABLE_TO_PAY = 35.00F;
    private static float PENALTY_FOR_PARKING_AT_OVERZISED_SPACE_WITH_1 = 10.00F;  // 1 level above the category
    private static float PENALTY_FOR_PARKING_AT_OVERZISED_SPACE_WITH_2 = 20.00F; // 2 levels above the category

    public static Banking getBank() {
        return bank;
    }

    public static long calculateParkingDuration(AbstractVehicle vehicle) {
        return (ChronoUnit.MINUTES.between(vehicle.getParkingTime(), vehicle.getLeavingTime()));
    }

    public static float calculateCharge(AbstractVehicle vehicle) {

        long minutesParked = calculateParkingDuration(vehicle);
        int hoursParked = (int) ((minutesParked / 60) + 1);
        float cost;

        if (hoursParked == 1) {
            cost =  ONE_HOUR_PARKING_COST;
        } else if (hoursParked == 2) {
            cost =  TWO_HOURS_PARKING_COST;
        } else if (hoursParked == 3) {
            cost = THREE_HOURS_PARKING_COST;
        } else {
            int remainingHours = hoursParked - 3;
            cost =  (THREE_HOURS_PARKING_COST + (remainingHours * EXTRA_HOUR_COST));
        }

        cost = applyDiscountOrCharge(vehicle, cost);
        cost = format(cost);

        return cost; //float with 2 decimals
    }

    private static float format(float cost) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return (Float.parseFloat((df.format(cost))));
    }

    private static float applyDiscountOrCharge(AbstractVehicle vehicle, float cost) {
        cost = validateParkingVehicleCategory(vehicle, cost);
        return (validateSpaceOccupiedByParkingVehicle(vehicle, cost));
    }

    private static float validateSpaceOccupiedByParkingVehicle(AbstractVehicle vehicle, float cost) {
        String spaceSize;

        if(vehicle.getSpace() != null) {
            spaceSize = vehicle.getSpace().getSize();
        } else {
            return cost;
        }

        if(vehicle instanceof Motorbike) {
            if(spaceSize.equals("normal")) {
                return (cost + PENALTY_FOR_PARKING_AT_OVERZISED_SPACE_WITH_1);
            } else if (spaceSize.equals("large")) {
                return (cost + PENALTY_FOR_PARKING_AT_OVERZISED_SPACE_WITH_2);
            }
        } else if(vehicle instanceof Car && spaceSize.equals("large")) {
            return (cost + PENALTY_FOR_PARKING_AT_OVERZISED_SPACE_WITH_1);
        } else {
            return cost;
        }

        return cost;
    }

    private static float validateParkingVehicleCategory(AbstractVehicle vehicle, float cost) {
        if(vehicle instanceof Motorbike) {
            return (cost - (cost / 100 * DISCOUNT_FOR_SMALL_VEHICLES));
        } else if(vehicle instanceof Van) {
            return (cost + (cost / 100 * EXTRA_CHARGE_FOR_LARGE_VEHICLES));
        } else {
            return cost;
        }
    }


    public static void chargeDriver(AbstractVehicle vehicle) {
        Driver driver = vehicle.getDriver();
        float cost = calculateCharge(vehicle);

        if(driver.canPayParkingCost(cost)) {
            driver.payParkingCost(cost);
            vehicle.setParkingCharge(cost);
        } else  {
            float costWithPenalty = cost + PENALTY_IF_DRIVER_UNABLE_TO_PAY;
            driver.payParkingCostWithPenalty(costWithPenalty);
            vehicle.setParkingCharge(costWithPenalty);
        }
    }
}
