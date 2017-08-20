package services;

import models.Direction;
import models.Door;
import models.Room;
import services.tracery.TraceryService;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by draluy on 16/08/2017.
 */
public class PlanService {

    private PlanService() {
    }


    public static final PlanService instance = new PlanService();

    private boolean shouldBeARoom() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        return randomNum > 0;
    }

    public void generateExitsRand(final Room room) {
        for (Direction dir : Direction.values()) {
            //do not overwrite a direction
            boolean containsDirection = room.getExits().keySet()
                    .stream()
                    .anyMatch(door -> door.getDirection().equals(dir));

            if (!containsDirection && shouldBeARoom()) {
                room.getExits().put(new Door(dir), null);
            }
        }
    }
}
