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

        Optional<Direction> directionOptional = Optional.empty();
        while (!directionOptional.isPresent()) {

            //User input
            final String userInput = InputService.instance.getUserInput();

            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }

            directionOptional = Plan.instance.getCurrentRoom().getExits().keySet()
                    .stream()
                    .filter(room -> room.getDirection().getValue().equalsIgnoreCase(userInput))
                    .map(room -> room.getDirection())
                    .findFirst();

            //update model
            if (directionOptional.isPresent()) {
                final Room currentRoom = Plan.instance.getCurrentRoom();

                final Direction dir = directionOptional.get();
                Optional<Map.Entry<Door, Room>> entry = currentRoom.getExits().entrySet()
                        .stream()
                        .filter(doorRoomEntry -> doorRoomEntry.getKey().getDirection().equals(dir))
                        .findAny();

                Room nextRoom;
                if (entry.get().getValue() == null) {
                    nextRoom = new Room();
                    final Door thisDoor = entry.get().getKey();
                    final Door door = new Door(Direction.getOpposite(directionOptional.get()), thisDoor.getDescription());
                    currentRoom.getExits().put(thisDoor, nextRoom);
                    nextRoom.getExits().put(door, currentRoom);
                    PlanService.instance.generateExitsRand(nextRoom);
                } else {
                    nextRoom = entry.get().getValue();
                }
                Plan.instance.setCurrent(nextRoom);
            }

            //display screen
            if (!directionOptional.isPresent()) {
                ScreenService.instance.display("Choix invalide!");
            } else {
                ScreenService.instance.display(p, Plan.instance.getCurrentRoom());
            }

            //reset user input
            directionOptional = Optional.empty();
        }
    }
}
