package models;

import services.tracery.TraceryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by draluy on 16/08/2017.
 */
public class Room {
    private final Map<Door, Room> exits = new HashMap<>();

    private String roomDescription;
    private Monster monster = null;

    private final static int MAX_DANGER = 10;

    public Room() {
        roomDescription = TraceryService.instance.parse("room_description");
        int dangerosity = new Random().nextInt(MAX_DANGER + 1);

        if (dangerosity > 8){
            monster = new Monster(TraceryService.instance.parse("nom_monstre"));
        }
    }

    public Map<Door, Room> getExits() {
        return exits;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public Monster getMonster() {
        return monster;
    }
}
