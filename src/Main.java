import models.Animal;
import models.Direction;
import models.Door;
import models.Object;
import models.ObjectType;
import models.Plan;
import models.Player;
import models.Room;
import services.ScreenService;
import services.input.Action;
import services.input.InputService;
import services.tracery.TraceryResult;

import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Player p = new Player();
        p.setDescription("Player");

        ScreenService.instance.display(p, Plan.instance.getCurrentRoom());

        Optional<Action> userInput = Optional.empty();
        while (!userInput.isPresent()) {

            //User input
            userInput = InputService.instance.getUserInput();
            Action action = userInput.orElse(Action.EMPTY);

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
                    Plan.instance.setCurrent(entry.get().getValue());
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
                    break;
            }

            //display screen
            final boolean isEndingReachd = Plan.instance.getCurrentRoom().isEnding();
            if (isEndingReachd){
                action= Action.END;
            }

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
                        ScreenService.instance.display(p, Plan.instance.getCurrentRoom(), "Vous tuez votre adversaire!");
                    }
                    ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
                    break;
                case SEARCH:
                    final Object furniture =  Plan.instance.getCurrentRoom().getObjects().get(ObjectType.FURNITURE);
                    ScreenService.instance.display(p, Plan.instance.getCurrentRoom(), "Votre fouillle ne retourne rien.");
                    break;
                case END:
                    ScreenService.instance.displayEnding(p, Plan.instance.getCurrentRoom());
                    break;
                default:
                    ScreenService.instance.display("Choix invalide!");
                    break;
            }

            //reset user input
            if (!action.equals(Action.END)) {
                userInput = Optional.empty();
            }
        }
    }
}
