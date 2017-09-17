import models.Animal;
import models.Direction;
import models.Door;
import models.Object;
import models.ObjectType;
import models.Plan;
import models.Player;
import models.Room;
import services.PlanService;
import services.ScreenService;
import services.grid.GridService;
import services.input.Action;
import services.input.InputService;
import services.tracery.TraceryResult;

import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Plan.instance.initPlan();
        Player p = new Player();
        p.setDescription("Player");

        GridService.instance.initGrid();

        ScreenService.instance.display(p, Plan.instance.getCurrentRoom());

        Optional<Action> userInput = Optional.empty();
        while (!userInput.isPresent()) {

            //User input
            userInput = InputService.instance.getUserInput();
            final Action action = userInput.orElse(Action.EMPTY);

            if (action.equals(Action.EXIT)) {
                ScreenService.instance.display("Sortie de l'application.");
                return;
            }

            final Room currentRoom = Plan.instance.getCurrentRoom();

            //update model
            switch (action) {
                case NORTH:
                case SOUTH:
                case EAST:
                case WEST:
                    final Optional<Direction> dir = Direction.fromValue(userInput.get().getValue());

                    final Optional<Map.Entry<Door, Room>> entry = currentRoom.getExits().entrySet()
                            .stream()
                            .filter(doorRoomEntry -> doorRoomEntry.getKey().getDirection().equals(dir.get()))
                            .findAny();

                    if (entry.get().getValue() == null) {
                        final Room nextRoom = new Room();
                        final Door thisDoor = entry.get().getKey();
                        final Door door = new Door(Direction.getOpposite(dir.get()), thisDoor.getDescription());
                        currentRoom.getExits().put(thisDoor, nextRoom);
                        nextRoom.getExits().put(door, currentRoom);
                        PlanService.instance.generateExitsRand(nextRoom);
                        Plan.instance.setCurrent(nextRoom);
                    } else {
                        Plan.instance.setCurrent(entry.get().getValue());
                    }
                    break;
                case FIGHT:
                    final Animal monster = (Animal) Plan.instance.getCurrentRoom().getObjects().get(ObjectType.MONSTER);
                    monster.setNbLifePoints(monster.getNbLifePoints() - 10);
                    break;
                case SEARCH:
                    final String meubleName = Plan.instance.getCurrentRoom().getRoomDescription().getConstTokens().get("meuble");
                    final TraceryResult objectDescription = new TraceryResult();
                    objectDescription.setParsedText(meubleName);
                    final Object object = new Object(objectDescription);
                    //Plan.instance.getCurrentRoom().
                    break;
            }

            //display screen
            switch (action) {
                case NORTH:
                case SOUTH:
                case EAST:
                case WEST:
                    ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
                    break;
                case FIGHT:
                    final Animal monster = (Animal) Plan.instance.getCurrentRoom().getObjects().get(ObjectType.MONSTER);
                    if (monster.getNbLifePoints() <= 0) {
                        ScreenService.instance.display("Vous tuez votre adversaire! ");
                    }
                    ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
                    break;
                case SEARCH:
                    final Object furniture =  Plan.instance.getCurrentRoom().getObjects().get(ObjectType.FURNITURE);
                    ScreenService.instance.display("Votre fouillle ne retourne rien.");
                    ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
                    break;
                default:
                    ScreenService.instance.display("Choix invalide!");
                    break;
            }

            //reset user input
            userInput = Optional.empty();
        }
    }
}
