package services;

import models.Animal;
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
        final boolean roomHasMonster = room.getMonster() != null;
        displayAnimal(player, player.getDescription().getParsedText());

        if (roomHasMonster) {
            displayAnimal(room.getMonster(), room.getMonster().getDescription().getConstTokens().get("monstre"));
        }

        out.println(room.getRoomDescription().getParsedText());
        if (roomHasMonster) {
            out.println("Au centre de la pièce se tient " + room.getMonster().getDescription().getConstTokens().get("monstre") + ".");
        }
        displayDirections(player, room);
    }

    private void displayAnimal(Animal animal, String name) {
        PrintStream out = System.out;
        if (name != null) {
            out.println("---------------------------------");
            out.println("|" + name + ": " + animal.getNbLifePoints() + "PV" + String.format("%1$" + (26 - name.length()) + "s", "|"));
            out.println("---------------------------------");
        }
    }

    public void displayDirections(Player player, Room room) {
        PrintStream out = System.out;

        final int size = room.getExits().size();
        String sortiesLabel = getDirectionsLabel(room);
        out.println("Vous apercevez " + size + " sorties. " + sortiesLabel);
        if (room.getMonster() != null) {
            out.println("Vous pouvez également COMBATTRE " + room.getMonster().getDescription().getConstTokens().get("monstre") + ".");
        }
        out.println("Quel est votre choix ?");
    }

    private String getDirectionsLabel(final Room room) {
        StringBuilder result = new StringBuilder();

        for (Door door : room.getExits().keySet()) {
            Direction direction = door.getDirection();
            result.append(direction.equals(Direction.NORTH) || direction.equals(Direction.SOUTH) ?
                    "\nAu " + direction.getValue().toUpperCase() : "\nA l'" + direction.getValue().toUpperCase());

            final Optional<Door> doorAtDirection = room.getExits().keySet()
                    .stream()
                    .filter(door1 -> door1.getDirection().equals(direction))
                    .findAny();

            if (doorAtDirection.isPresent()) {
                result.append(", " + doorAtDirection.get().getDescription());
            } else {
                result.append(".");
            }
        }

        return result.toString();
    }

    public void display(String s) {
        System.out.println(s);
    }
}
