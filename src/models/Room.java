package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by draluy on 16/08/2017.
 */
public class Room {
    private Map<Direction, Room> exits = new HashMap<>();

    public Map<Direction, Room> getExits() {
        return exits;
    }

    public void addExit(Direction dir) {
        exits.put(dir, null);
    }
}
