package models;

import services.tracery.TraceryService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by draluy on 16/08/2017.
 */
public class Room {
    private Map<Door, Room> exits = new HashMap<>();

    public String roomDescription;

    public Room (){
        roomDescription = TraceryService.instance.parse("room_description");
    }

    public Map<Door, Room> getExits() {
        return exits;
    }
}
