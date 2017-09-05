import models.Direction;
import models.Door;
import models.Plan;
import models.Player;
import models.Room;
import services.PlanService;
import services.ScreenService;
import services.input.Action;
import services.input.InputService;

import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Plan.instance.initPlan();
        Player p = new Player();

        ScreenService.instance.display(p, Plan.instance.getCurrentRoom());

        Optional<Action> userInput = Optional.empty();
        while (!userInput.isPresent()) {

            //User input
            userInput = InputService.instance.getUserInput();

            if (userInput.isPresent() && userInput.get().equals(Action.EXIT)) {
                ScreenService.instance.display("Sortie de l'application.");
                return;
            }

            final Room currentRoom = Plan.instance.getCurrentRoom();


            //update model
            if (userInput.isPresent() && userInput.get().isDirection()) {
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
            }

            if (userInput.isPresent() && userInput.get().equals(Action.FIGHT)) {
                Plan.instance.getCurrentRoom().killMonster();
            }


            //display screen

            if (userInput.isPresent() && userInput.get().isDirection()) {
                ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
            } else if (userInput.isPresent() && userInput.get().equals(Action.FIGHT)) {
                ScreenService.instance.display("Vous tuez votre adversaire. ");
                ScreenService.instance.displayDirections(p, Plan.instance.getCurrentRoom());
            } else {
                ScreenService.instance.display("Choix invalide!");
            }

            //reset user input
            userInput = Optional.empty();
        }
    }
}
