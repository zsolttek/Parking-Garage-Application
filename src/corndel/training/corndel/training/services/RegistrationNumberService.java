package corndel.training.corndel.training.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegistrationNumberService {

    private static RegistrationNumberService registrationNumberService;
    private String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                                  "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                                  "y", "z"};
    private List<String> registeredPlates = new ArrayList<>();
    private String currentPlate = null;
    public List<String> getRegisteredPlates() { return registeredPlates; }

    private RegistrationNumberService() { }

    public static RegistrationNumberService getRegistrationNumberService() {
        if(registrationNumberService == null) {
            registrationNumberService = new RegistrationNumberService();
        }
        return registrationNumberService;
    }

    //format CV15PBR
    public String generateRegistrationNumber() {
        int idx;
        boolean flag = true;

        while(flag) {
            List<String> currentPlateBuilder = new ArrayList<>();

            for (int i = 1; i <= 7; i++) {
                if (i == 3 || i == 4) { // number
                    int number = generateRandomNumber(10);
                    currentPlateBuilder.add(String.valueOf(number));
                } else { // char
                    idx = generateRandomNumber(alphabet.length);
                    currentPlateBuilder.add(alphabet[idx].toUpperCase());
                }
            }

            currentPlate = String.join("", currentPlateBuilder);

            if(!exists(currentPlate)) {
                flag = false;
            }
        }

        registeredPlates.add(currentPlate);
        return registeredPlates.get(registeredPlates.size()-1);
    }

    private boolean exists(String newPlate) {
        if(registeredPlates.size() == 0) { return false; }

        for(String plate : registeredPlates) {
            if(plate.equals(newPlate)) {
                return true;
            }
        }

        return false;
    }

    private int generateRandomNumber(int n) {
        // generates a random number between 0 and n-1
        Random rand = new Random();
        return rand.nextInt(n);
    }
}
