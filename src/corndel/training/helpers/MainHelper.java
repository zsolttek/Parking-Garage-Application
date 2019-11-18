package corndel.training.helpers;

import corndel.training.corndel.training.services.RegistrationNumberService;
import corndel.training.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainHelper {

    private static Random random = new Random();

    private MainHelper() { }

    public static List<Motorbike> createNrOfMotorbikes(int number, RegistrationNumberService registrationNumberService) {
        List<Motorbike> motorbikes = new ArrayList<>();

        for(int i = 1; i <= number; i++) {
            Motorbike motorbike = new Motorbike(registrationNumberService.generateRegistrationNumber());
            motorbike.setDriver(new Driver());
            motorbikes.add(motorbike);
        }

        return motorbikes;
    }

    public static List<Car> createNrOfCars(int number, RegistrationNumberService registrationNumberService) {
        List<Car> cars = new ArrayList<>();

        for(int i = 1; i <= number; i++) {
            Car car = new Car(registrationNumberService.generateRegistrationNumber());
            car.setDriver(new Driver());
            cars.add(car);
        }

        return cars;
    }

    public static List<Van> createNrOfVans(int number, RegistrationNumberService registrationNumberService) {
        List<Van> vans = new ArrayList<>();

        for(int i = 1; i <= number; i++) {
            Van van = new Van(registrationNumberService.generateRegistrationNumber());
            van.setDriver(new Driver());
            vans.add(van);
        }

        return vans;
    }

    public static List<Space> getSomeSpaces(Garage garage) {
        List<Space> someSpaces = new ArrayList<>();

        for(Floor floor : garage.getFloors()) {
            int limit = (floor.getSpaces().size() / 4);
            for(int i = 0; i <= limit; i++) {
                someSpaces.add(floor.getSpaces().get(i));
            }
        }

        return someSpaces;
    }

    public static void launch(List<AbstractVehicle> vehicles, List<Space> someSpaces, Garage garage) {
        System.out.println(" ");
        System.out.println("< = = = = = = = = = =        RANDOM PARKING REPORT        = = = = = = = = = = = = = = = >");
        randomParking(vehicles, someSpaces);

        System.out.println(" ");
        System.out.println("< = = = = = = = = = =        RANDOM LEAVING REPORT        = = = = = = = = = = = = = = = >");
        randomLeaving(vehicles, someSpaces);

        System.out.println(" ");
        System.out.println("< = = = = = = = = = =        VEHICLE STATUS REPORT        = = = = = = = = = = = = = = = >");
        for(AbstractVehicle vehicle : vehicles) {
            vehicle.displayStatus();
        }

        System.out.println(" ");
        System.out.println("< = = = = = = = =        VEHICLES PARKING IN THE GARAGE       = = = = = = = = = = = = = >");
        for(AbstractVehicle vehicle : garage.getParkingVehicles()) {
            System.out.println(vehicle + " parking at space with id: " + vehicle.getSpace().getId());
        }

        System.out.println(" ");
        System.out.println("< = = = = = = = = = = =       FREE SPACES LEFT       = = = = = = = = = = = = = = = = = >");
        for(Space space : garage.getFreeSpaces()) {
            System.out.println("Space id: " + space.getId() + " => Size: " + space.getSize() + " | " + "Height: " +
                    space.getHeight());
        }
    }

    private static void randomParking(List<AbstractVehicle> vehicles, List<Space> someSpaces) {
        int size = someSpaces.size();

        for(AbstractVehicle vehicle : vehicles) {
            int idx = random.nextInt(size);
            Space space = someSpaces.get(idx);
            vehicle.tryToPark(space);
        }
    }

    private static void randomLeaving(List<AbstractVehicle> vehicles, List<Space> someSpaces) {
        int size = someSpaces.size();

        for(AbstractVehicle vehicle : vehicles) {
            int idx = random.nextInt(size);
            Space space = someSpaces.get(idx);
            vehicle.tryToLeave(space);
        }
    }
}
