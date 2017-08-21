package services.tracery;

import java.util.Arrays;

/**
 * Created by draluy on 17/08/2017.
 */
public class ModifierService {
    public static final ModifierService instance = new ModifierService();

    private ModifierService() {
    }

    public String process(String token, String[] modifiers) {
        String result = token;

        //Hack to process plurals after feminisation and capitalisation
        Arrays.sort(modifiers);

        for (String modifier : modifiers) {
            switch (modifier) {
                case "f":
                    result = feminize(result);
                    break;
                case "s":
                    result = pluralize(result);
                    break;
                case "c":
                    result = capitalize(result);
                    break;
            }
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

    private String pluralize(final String token) {
        String res = token;


        if (token.endsWith("al")) {
            res = token.replace("al", "aux");
            ;
        } else if (token.endsWith("eux")) {
            res = token;
        } else if (!token.endsWith("s")) {
            res += "s";
        }

        return res;
    }

    private String capitalize(final String token) {
        return token.substring(0, 1).toUpperCase() + token.substring(1);
    }
}
