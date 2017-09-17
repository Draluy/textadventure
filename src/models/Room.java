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
    private int x,y;

    private final static int MAX_DANGER = 10;

    public Room(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (x != room.x) return false;
        if (y != room.y) return false;
        if (!exits.equals(room.exits)) return false;
        if (!roomDescription.equals(room.roomDescription)) return false;
        return objects.equals(room.objects);
    }

    @Override
    public int hashCode() {
        int result = exits.hashCode();
        result = 31 * result + roomDescription.hashCode();
        result = 31 * result + objects.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "exits=" + exits +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
