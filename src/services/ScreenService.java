package services;

import models.Animal;
import models.Direction;
import models.Door;
import models.ObjectType;
import models.Player;
import models.Room;

import java.io.PrintStream;
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
        final Animal monstre = (Animal) room.getObjects().get(ObjectType.MONSTER);
        final boolean roomHasMonster = room.getObjects().containsKey(ObjectType.MONSTER) && monstre.getNbLifePoints() > 0;
        //Display players
        displayAnimal(player, player.getDescription().getParsedText());
        if (roomHasMonster) {
            displayAnimal((Animal) room.getObjects().get(ObjectType.MONSTER), room.getObjects().get(ObjectType.MONSTER).getDescription().getConstTokens().get("monstre"));
        }

        //Display descriptions
        out.println(room.getRoomDescription().getParsedText());

        if (roomHasMonster) {
            out.println("Au centre de la pièce se tient " + monstre.getDescription().getConstTokens().get("monstre") + ".");
        }

        //Display actions
        displayDirections(player, room);
        displayActions(room);

        out.println("Quel est votre choix ?");
    }

    private void displayActions(final Room room) {
        room.getObjects().entrySet()
                .stream()
                .sorted((o1, o2) -> o1.getKey().compareTo(o1.getKey()))
                .forEach(entry -> {
                    switch (entry.getKey()) {
                        case FURNITURE:
                            out.println("Vous pouvez FOUILLER " + room.getObjects().get(ObjectType.FURNITURE).getDescription().getParsedText() + ".");
                            break;
                        case MONSTER:
                            final Animal monstre = (Animal) room.getObjects().get(ObjectType.MONSTER);
                            if (monstre.getNbLifePoints() > 0) {
                                out.println("Vous pouvez COMBATTRE " + monstre.getDescription().getConstTokens().get("monstre") + ".");
                            }
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
        out.println(s);
    }
}
