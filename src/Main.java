import models.*;
import services.InputService;
import services.PlanService;
import services.ScreenService;

import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Plan.instance.initPlan();
        Player p = new Player();

        ScreenService.instance.display(p, Plan.instance.getCurrentRoom());

        Optional<Map.Entry<Door, Room>> entry = Optional.empty();
        while (!entry.isPresent()) {

            //User input
            final String userInput = InputService.instance.getUserInput();

            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }

            final Room currentRoom = Plan.instance.getCurrentRoom();

            entry = currentRoom.getExits().entrySet()
                    .stream()
                    .filter(doorRoomEntry -> doorRoomEntry.getKey().getDirection().getValue().equalsIgnoreCase(userInput))
                    .findAny();

            //update model
            if (entry.isPresent()) {
                final Direction dir = entry.get().getKey().getDirection();

                if (entry.get().getValue() == null) {
                    final Room nextRoom = new Room();
                    final Door thisDoor = entry.get().getKey();
                    final Door door = new Door(Direction.getOpposite(dir), thisDoor.getDescription());
                    currentRoom.getExits().put(thisDoor, nextRoom);
                    nextRoom.getExits().put(door, currentRoom);
                    PlanService.instance.generateExitsRand(nextRoom);
                    Plan.instance.setCurrent(nextRoom);
                } else {
                    Plan.instance.setCurrent(entry.get().getValue());
                }
            }

            //display screen
            if (!entry.isPresent()) {
                ScreenService.instance.display("Choix invalide!");
            } else {
                ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
            }

            //reset user input
            entry = Optional.empty();
        }
    }
}
