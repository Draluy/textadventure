package services.tracery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by draluy on 18/08/2017.
 */
public class TraceryDict {

    static final Map<String, Set<String>> symbols = new HashMap<>();

    private TraceryDict (){}

    static {
        symbols.put("porte_description", new HashSet<>(Arrays.asList("se dresse #syn_porte#, #filler_porte#.")));
        symbols.put("adj_porte", new HashSet<>(Arrays.asList("grand", "inquiétant", "solide", "noir", "en planches épaisses et jointives", "à l'aspect patiné")));
        symbols.put("syn_porte", new HashSet<>(Arrays.asList("une porte #adj_porte.f#", "une barrière #adj_porte.f#", "un battant #adj_porte#", "un portail #adj_porte#")));
        symbols.put("filler_porte", new HashSet<>(Arrays.asList("avec un détail étrange: il n'y a pas de serrure", "la possibilité d'un piège vous empêche de l'ouvrir immédiatement", " comme vous en avez vu des dizaines", "sans autre détail frappant")));

        symbols.put("room_description", new HashSet<>(Arrays.asList("La pièce qui vous entoure #desc_piece#. #partie_piece.c# #adj_mur# sont recouvertes de tapisseries élimées.",
                "Vous êtes au centre d'une pièce #adj_piece# #adj_piece#. Les coins sont décorées de guéridons surmontés de plantes #coul_plante.f#s foisonnantes. Devant vous, un lit à baldaquin semble presque incongru dans ce décor.",
                "Vous entrez dans un long couloir mal éclairé, les murs #adj_mur.s# et #adj_mur.s# recouverts d'une mousse #coul_plante.f# par endroits.")));

        symbols.put("desc_piece", new HashSet<>(Arrays.asList("mesure environ 10 mètres de côté")));
        symbols.put("adj_piece", new HashSet<>(Arrays.asList("hexagonale", "richement décorée")));
        symbols.put("partie_piece", new HashSet<>(Arrays.asList("les murs", "les coins")));
        symbols.put("adj_mur", new HashSet<>(Arrays.asList("nu", "suintant", "en pierre grossièrement taillées")));
        symbols.put("coul_plante", new HashSet<>(Arrays.asList("noir", "vert")));
        symbols.put("nom_monstre", new HashSet<>(Arrays.asList("une ghoule", "un orc", "un golem", "un troll", "un vampire")));
    }
}
