package corndel.training;

import corndel.training.helpers.FloorHelper;
import corndel.training.corndel.training.services.RegistrationNumberService;
import corndel.training.helpers.MainHelper;
import corndel.training.models.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RegistrationNumberService registrationNumberService = RegistrationNumberService.getRegistrationNumberService();

        Floor floor1 = new Floor();
        Floor floor2 = new Floor();
        Floor floor3 = new Floor();

        FloorHelper.createNrOfSpaces(floor1, 10);
        FloorHelper.createNrOfSpaces(floor2, 20);
        FloorHelper.createNrOfSpaces(floor3, 30);

        Garage garage = new Garage(floor1, floor2, floor3);
        garage.addFloor(floor1);
        garage.addFloor(floor2);
        garage.addFloor(floor3);

        List<Space> someSpaces = MainHelper.getSomeSpaces(garage);

        List<Motorbike> motorbikes = MainHelper.createNrOfMotorbikes(5, registrationNumberService);
        List<Car> cars = MainHelper.createNrOfCars(5, registrationNumberService);
        List<Van> vans = MainHelper.createNrOfVans(5, registrationNumberService);

        List<AbstractVehicle> vehicles = new ArrayList<>();
        vehicles.addAll(motorbikes);
        vehicles.addAll(cars);
        vehicles.addAll(vans);

        // Displays random parking, random leaving and afterwards the status of vehicles and parking garage
        MainHelper.launch(vehicles, someSpaces, garage);

    }

}

