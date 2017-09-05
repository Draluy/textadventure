package services.input;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by draluy on 16/08/2017.
 */
public class InputService {
    public static final InputService instance = new InputService();

    private InputService() {
    }

    public Optional<Action> getUserInput() {
        final Scanner scanner = new Scanner(System.in);
        final String input = scanner.nextLine();

        return Arrays
                .stream(Action.values())
                .filter(value -> value.getValue().equalsIgnoreCase(input))
                .findAny();
    }
}
