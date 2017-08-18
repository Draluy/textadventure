import models.Direction;
import models.Plan;
import models.Player;
import models.Room;
import services.InputService;
import services.PlanService;
import services.ScreenService;

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
                    .filter(direction -> direction.getValue().equalsIgnoreCase(userInput))
                    .findFirst();

            //update model
            if (directionOptional.isPresent()) {
                final Room currentRoom = Plan.instance.getCurrentRoom();
                Room nextRoom = currentRoom.getExits().get(directionOptional.get());
                if (nextRoom == null) {
                    nextRoom = new Room();
                    nextRoom.getExits().put(Direction.getOpposite(directionOptional.get()),currentRoom);
                    PlanService.instance.generateExitsRand(nextRoom);
                    currentRoom.getExits().put(directionOptional.get(), nextRoom);
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
