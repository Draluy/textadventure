package models;

import services.tracery.TraceryService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by draluy on 16/08/2017.
 */
public class Room {
    private Map<Direction, Room> exits = new HashMap<>();

    public String doorDescription, roomDescription;

    public Room (){
        roomDescription = TraceryService.instance.parse("room_description");
        doorDescription = TraceryService.instance.parse("porte_description");
    }

    public Map<Direction, Room> getExits() {
        return exits;
    }

    public void addExit(Direction dir) {
        exits.put(dir, null);
    }
}
