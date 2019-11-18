package corndel.training.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Space> spaces = new ArrayList<>();

    public List<Space> getSpaces() {
        return spaces;
    }

    public void addSpace(Space space) {
        this.spaces.add(space);
    }

}
