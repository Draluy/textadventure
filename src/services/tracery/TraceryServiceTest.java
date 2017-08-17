package services.tracery;

import java.util.Arrays;
import java.util.HashSet;

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
        TraceryService.instance.getSymbols().put("sentence4", new HashSet<>(Arrays.asList("On dit un #taille2# mur, mais une #taille2.f# abeille")));
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
}