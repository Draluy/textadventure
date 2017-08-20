package services;

import models.Direction;
import models.Door;
import models.Player;
import models.Room;

import java.io.PrintStream;
import java.util.Optional;

/**
 * Created by draluy on 16/08/2017.
 */
public class ScreenService {

    public static final ScreenService instance = new ScreenService();

    private ScreenService() {
    }

    public void display(Player player, Room room) {
        PrintStream out = System.out;

        final int size = room.getExits().size();
        String sortiesLabel = getDirectionsLabel(room);
        out.println(size > 0 ? room.roomDescription+
                "Vous apercevez " + size + " sorties. " + sortiesLabel
                : "Cette pièce n'a pas d'issues.");

        out.println("Quel est votre choix ?");
    }

    private String getDirectionsLabel(final Room room) {
        String result="";

        for (Door door : room.getExits().keySet()) {
            Direction direction = door.getDirection();
            result+= "\nAu "+ direction.getValue();

            final Optional<Door> doorAtDirection = room.getExits().keySet()
                    .stream()
                    .filter(door1 -> door1.getDirection().equals(direction))
                    .findAny();

            if (doorAtDirection.isPresent()){
                result+= ", "+doorAtDirection.get().getDescription();
            }else {
                result+= ".";
            }
        }

        return result;
    }

    public void display(String s) {
        System.out.println(s);
    }
}
