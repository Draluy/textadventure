package services;

import java.util.Scanner;

/**
 * Created by draluy on 16/08/2017.
 */
public class InputService {
    public static final InputService instance = new InputService();

    private InputService() {
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
