package services.tracery;

import exceptions.TextAdventureException;

import java.util.Arrays;
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

    public TraceryResult parse(String startingSymbol) {
        if (!symbols.containsKey(startingSymbol)) {
            throw new TextAdventureException("Error, could not find key for " + startingSymbol);
        }

        final TraceryResult result = new TraceryResult();
        String startingText = getRandomValue(startingSymbol);
        result.setParsedText(parseSymbol(startingText, result.getConstTokens()));

        return result;
    }

    private String parseSymbol(final String sentenceInput, final Map<String, String> symbols) {
        String sentence = sentenceInput;

        final Pattern patternVariables = Pattern.compile("\\[([^\\[\\]]+):([^\\[\\]]+)\\]");
        final Matcher matchervariables = patternVariables.matcher(sentence);

        //These are the symbolds that must be constant, so drawn one and kept here
        Map<String, String> predefinedSymbols = new HashMap<>();
        if (symbols != null) {
            predefinedSymbols.putAll(symbols);
        }
        while (matchervariables.find()) {
            final String varName = matchervariables.group(1);
            final String symbolName = matchervariables.group(2);

            final String[] tokens = symbolName.contains(".") ? symbolName.split("\\.") : new String[]{symbolName};
            final String symbolToReplace = tokens[0];
            final String[] modifiers = Arrays.copyOfRange(tokens, 1, tokens.length);

            String replacement = parseSymbol(modifiers.length > 0 ? getRandomValue(symbolToReplace, modifiers) : getRandomValue(symbolToReplace), predefinedSymbols);
            predefinedSymbols.put(varName, replacement);
            symbols.put(varName, replacement);
            sentence = sentence.replace(matchervariables.group(0), "");
        }

        final Pattern pattern = Pattern.compile("#(.+?)#");
        final Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()) {
            final String token = matcher.group(1);
            final String[] tokens = token.contains(".") ? token.split("\\.") : new String[]{token};
            final String symbolToReplace = tokens[0];
            final String[] modifiers = Arrays.copyOfRange(tokens, 1, tokens.length);
            final String symbolToReplaceWithHashes = "#" + symbolToReplace + (modifiers.length > 0 ? "." + String.join(".", modifiers) : "") + "#";

            final Set<String> replacements = new HashSet<>();
            while (sentence.contains(symbolToReplaceWithHashes)) {
                String replacement;
                if (predefinedSymbols.containsKey(symbolToReplace)) {
                    replacement = ModifierService.instance.process(predefinedSymbols.get(symbolToReplace), modifiers);
                } else {
                    replacement = parseSymbol(modifiers.length > 0 ? getRandomValue(symbolToReplace, modifiers) : getRandomValue(symbolToReplace), predefinedSymbols);
                    while (replacements.contains(replacement)) {
                        replacement = parseSymbol(modifiers.length > 0 ? getRandomValue(symbolToReplace, modifiers) : getRandomValue(symbolToReplace), predefinedSymbols);
                    }
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

    private String getRandomValue(String key, String[] modifiers) {

        if (modifiers == null) {
            throw new TextAdventureException("Modifier is null for key " + key);
        }

        String result = getRandomValue(key);
        result = ModifierService.instance.process(result, modifiers);
        return result;
    }

    private <E> E getRandom(Collection<E> e) {
        return e.stream()
                .skip(new Random().nextInt(e.size()))
                .findFirst()
                .get();
    }

    public Map<String, Set<String>> getSymbols() {
        return symbols;
    }
}
