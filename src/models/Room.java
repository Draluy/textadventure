package models;

import services.tracery.TraceryResult;
import services.tracery.TraceryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by draluy on 16/08/2017.
 */
public class Room {
    private final Map<Door, Room> exits = new HashMap<>();

    private TraceryResult roomDescription;
    private Map<ObjectType, Object> objects = new HashMap<>();

    private final static int MAX_DANGER = 10;

    public Room() {
        roomDescription = TraceryService.instance.parse("room_description");
        int dangerosity = new Random().nextInt(MAX_DANGER + 1);

        final TraceryResult traceryResult = new TraceryResult();
        traceryResult.setParsedText(roomDescription.getConstTokens().get("meuble"));
        final Object furniture = new Object(traceryResult);
        objects.put(ObjectType.FURNITURE, furniture);

        if (dangerosity > 5) {
            objects.put(ObjectType.MONSTER, new Monster(TraceryService.instance.parse("monster_description")));
        }
    }

    public Map<Door, Room> getExits() {
        return exits;
    }

    public TraceryResult getRoomDescription() {
        return roomDescription;
    }

    public Map<ObjectType, Object> getObjects() {
        return objects;
    }
}
