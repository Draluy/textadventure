package services.tracery;

/**
 * Created by draluy on 17/08/2017.
 */
public class ModifierService {
    public static final ModifierService instance = new ModifierService();

    private ModifierService() {
    }

    public String process (String sentence, String modifier){
        String result = sentence;
        switch (modifier) {
            case "f":
                result = feminize(result);
        }

        return result;
    }

    private String feminize(final String token) {
        String res = token;
        if (!token.endsWith("e")) {
            res = token + "e";
        }

        return res;
    }
}
