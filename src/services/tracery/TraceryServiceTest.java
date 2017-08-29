package services.tracery;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;

/**
 * Created by draluy on 17/08/2017.
 */
class TraceryServiceTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        TraceryService.instance.getSymbols().clear();
        TraceryService.instance.getSymbols().put("sentence1", new HashSet<>(Arrays.asList("Le joueur entre dans le dongeon.")));
        TraceryService.instance.getSymbols().put("sentence2", new HashSet<>(Arrays.asList("Le joueur #couleur# entre dans le dongeon.")));
        TraceryService.instance.getSymbols().put("couleur", new HashSet<>(Arrays.asList(("bleu rouge vert jaune").split(" "))));
        TraceryService.instance.getSymbols().put("sentence3", new HashSet<>(Arrays.asList("Le joueur #couleur# entre dans #piece#.")));
        TraceryService.instance.getSymbols().put("piece", new HashSet<>(Arrays.asList("la #taille.f# chambre", "le #taille# couloir", "le #taille# tombeau")));
        TraceryService.instance.getSymbols().put("taille", new HashSet<>(Arrays.asList("grand", "petit", "immense")));
        TraceryService.instance.getSymbols().put("taille2", new HashSet<>(Arrays.asList("grand")));
        TraceryService.instance.getSymbols().put("taille3", new HashSet<>(Arrays.asList("grand", "petit", "immense", "ridicule", "joyeux", "beau")));
        TraceryService.instance.getSymbols().put("sentence4", new HashSet<>(Arrays.asList("On dit un #taille2# mur, mais une #taille2.f# abeille")));
        TraceryService.instance.getSymbols().put("sentence5", new HashSet<>(Arrays.asList("On dit un #taille# mur, le #taille# bateau, un #taille# truc.")));
        TraceryService.instance.getSymbols().put("sentence7", new HashSet<>(Arrays.asList("On dit une #taille.f# murette, et une #taille.f# gondole, et une #taille.f# chose.")));
        TraceryService.instance.getSymbols().put("sentence6", new HashSet<>(Arrays.asList("Debut de l'histoire. #[taille:taille3]sentence5#. Fin de l'histoire.")));
    }

    @org.junit.jupiter.api.Test
    void parse1() {
        Assertions.assertEquals(TraceryService.instance.parse("sentence1"),"Le joueur entre dans le dongeon.");
    }

    @org.junit.jupiter.api.Test
    void parse2() {
        final String res = TraceryService.instance.parse("sentence2");
        Assertions.assertTrue(res.startsWith("Le joueur "));
        Assertions.assertTrue(res.endsWith(" entre dans le dongeon."));
        Assertions.assertTrue(res.length() > 32);
        Assertions.assertFalse(res.contains("#"));
    }

    @org.junit.jupiter.api.Test
    void parse3() {
        final String res = TraceryService.instance.parse("sentence3");
        Assertions.assertFalse(res.contains("#"));
    }

    @org.junit.jupiter.api.Test
    void parse4() {
        final String res = TraceryService.instance.parse("sentence4");
        Assertions.assertEquals(res, "On dit un grand mur, mais une grande abeille");
    }

    @org.junit.jupiter.api.Test
    void parseGlobalVariables() {
        final String res = TraceryService.instance.parse("sentence6");
        final String[] words = res.split(" ");
        final int frequences = Collections.frequency(Arrays.asList(words), TraceryService.instance.getSymbols().get("taille3"));

        final Optional<String> taille3 = TraceryService.instance.getSymbols().get("taille3").stream()
                .filter(s -> Collections.frequency(Arrays.asList(words), s) >= 3)
                .findAny();
        Assertions.assertTrue(taille3.isPresent());
    }
}