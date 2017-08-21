package exceptions;

/**
 * Created by draluy on 21/08/2017.
 */
public class TextAdventureException extends RuntimeException {
    public TextAdventureException(String message) {
        super(message);
    }

    public TextAdventureException(String message, Throwable cause) {
        super(message, cause);
    }
}
