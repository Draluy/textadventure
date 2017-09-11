package services;

import models.Animal;
import models.Direction;
import models.Door;
import models.Player;
import models.Room;
import services.tracery.TraceryResult;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by draluy on 16/08/2017.
 */
public class ScreenService {

    public static final ScreenService instance = new ScreenService();
    private PrintStream out = System.out;

    private ScreenService() {
    }

    public void display(Player player, Room room) {
        final boolean roomHasMonster = room.getMonster() != null;

        //Display players
        displayAnimal(player, player.getDescription().getParsedText());
        if (roomHasMonster) {
            displayAnimal(room.getMonster(), room.getMonster().getDescription().getConstTokens().get("monstre"));
        }

        //Display descriptions
        out.println(room.getRoomDescription().getParsedText());
        if (roomHasMonster) {
            out.println("Au centre de la pièce se tient " + room.getMonster().getDescription().getConstTokens().get("monstre") + ".");
        }

        //Display actions
        displayDirections(player, room);
        displayActions(room.getRoomDescription(), roomHasMonster ? room.getMonster().getDescription() : null);

        out.println("Quel est votre choix ?");
    }

    private void displayActions(TraceryResult roomDescription, TraceryResult monsterDescription) {
        final Map<String, String> tokens = new HashMap<>();
        tokens.putAll(roomDescription.getConstTokens());
        if (monsterDescription != null) {
            tokens.putAll(monsterDescription.getConstTokens());
        }

        tokens.forEach((key, value) -> {
            switch (key) {
                case "meuble":
                    out.println("Vous pouvez FOUILLER " + roomDescription.getConstTokens().get("meuble") + ".");
                    break;
                case "monstre":
                    out.println("Vous pouvez également COMBATTRE " + monsterDescription.getConstTokens().get("monstre") + ".");
                    break;
            }
        });
    }

    private void displayAnimal(Animal animal, String name) {
        if (name != null) {
            out.println("---------------------------------");
            out.println("|" + name + ": " + animal.getNbLifePoints() + "PV" + String.format("%1$" + (26 - name.length()) + "s", "|"));
            out.println("---------------------------------");
        }
    }

    public void displayDirections(Player player, Room room) {
        final int size = room.getExits().size();
        String sortiesLabel = getDirectionsLabel(room);
        out.println("Vous apercevez " + size + " sorties. " + sortiesLabel);
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
