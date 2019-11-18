package corndel.training.helpers;

import corndel.training.models.Floor;
import corndel.training.models.Space;
import java.util.Random;

public class FloorHelper {

    private static String[] spaceSize = { "small", "normal", "large" };
    private static String[] spaceHeight ={ "low", "average", "high" };
    private static Random random = new Random();

    private FloorHelper() { }

    public static void createNrOfSpaces(Floor floor, int numberOfSpaces) {
        for(int i = 1; i <= numberOfSpaces; i++) {
            int randomIndex1 = random.nextInt(spaceSize.length);
            int randomIndex2 = random.nextInt(spaceHeight.length);
            floor.addSpace(new Space(spaceSize[randomIndex1], spaceHeight[randomIndex2]));
        }
    }
}
