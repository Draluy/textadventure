package services.tracery;

/**
 * Created by draluy on 17/08/2017.
 */
public class ModifierService {
    public static final ModifierService instance = new ModifierService();

    private ModifierService() {
    }

    public String process(String token, String modifier) {
        String result = token;
        switch (modifier) {
            case "f":
                result = feminize(result);
        }

        return result;
    }

    private String feminize(final String token) {
        String res = token;

        if (token.endsWith("el")) {
            res = token.replace("el", "elle");
        } else if (token.endsWith("eux")) {
            res = token.replace("eux", "euse");
        } else if (token.endsWith("eur")) {
            res = token.replace("eur", "euse");
        } else if (!token.endsWith("e")) {
            res = token + "e";
        }

        return res;
    }
}
