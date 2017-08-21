package services.tracery;

import exceptions.TextAdventureException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by draluy on 17/08/2017.
 */
public class TraceryService {

    private final Map<String, Set<String>> symbols = new HashMap<>();
    public static final TraceryService instance = new TraceryService();

    private TraceryService() {
        symbols.putAll(TraceryDict.symbols);
    }

    public String parse(String startingSymbol) {
        if (!symbols.containsKey(startingSymbol)) {
            throw new TextAdventureException("Error, could not find key for " + startingSymbol);
        }

        final String text = getRandomValue(startingSymbol);

        if (text.contains("#")) {
            return parseSymbol(text);
        } else {
            return text;
        }
    }

    private String parseSymbol(String sentence) {
        final Pattern pattern = Pattern.compile("#(.+?)#");
        final Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()) {
            final String token = matcher.group(1);
            final String[] tokens = token.contains(".") ? token.split("\\.") : new String[]{token};
            final String symbolToReplace = tokens[0];
            final String symbolToReplaceWithHashes = "#" + symbolToReplace + (tokens.length > 1? "."+tokens[1]:"") + "#";

            final Set<String> replacements = new HashSet<>();
            while (sentence.contains(symbolToReplaceWithHashes)){
                String replacement = parseSymbol(tokens.length > 1 ? getRandomValue(symbolToReplace, tokens[1]) : getRandomValue(symbolToReplace));
                while (replacements.contains(replacement)){
                    replacement = parseSymbol(tokens.length > 1 ? getRandomValue(symbolToReplace, tokens[1]) : getRandomValue(symbolToReplace));
                }
                sentence = sentence.replaceFirst(symbolToReplaceWithHashes, replacement);
                replacements.add(replacement);
            }
        }

        return sentence;
    }

    private String getRandomValue(String key) {
        return getRandom(symbols.get(key));
    }

    private String getRandomValue(String key, String modifier) {

        if (modifier == null) {
            throw new TextAdventureException("Modifier is null for key " + key);
        }

        String result = getRandomValue(key);
        result = ModifierService.instance.process(result, modifier);
        return result;
    }

    private static <E> E getRandom(Collection<E> e) {
        return e.stream()
                .skip(new Random().nextInt(e.size()))
                .findFirst()
                .get();
    }

    public Map<String, Set<String>> getSymbols() {
        return symbols;
    }
}
