package services;

import java.util.Scanner;

/**
 * Created by draluy on 16/08/2017.
 */
public class InputService {
    private InputService() {
    }
    public static final InputService instance = new InputService();

    public String getUserInput (){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }
}
