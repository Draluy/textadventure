package services.tracery;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by draluy on 08/09/2017.
 */
public class TraceryResult {
     private String parsedSentence;
     private final Map<String, String> constTokens = new HashMap<>();

    public String getParsedText() {
        return parsedSentence;
    }

    public void setParsedText(String parsedSentence) {
        this.parsedSentence = parsedSentence;
    }

    public Map<String, String> getConstTokens() {
        return constTokens;
    }
}
