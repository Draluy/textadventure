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

    private TraceryDict() {
    }

    static {
        symbols.put("adj_piece", new HashSet<>(Arrays.asList("hexagonale", "richement décorée")));
        symbols.put("adj_mur", new HashSet<>(Arrays.asList("nu", "suintant", "en pierre", "sombre")));
        symbols.put("adj_porte", new HashSet<>(Arrays.asList("grand", "robuste", "solide", "noir", "en planches épaisses", "à l'aspect patiné")));

        symbols.put("syn_voir", new HashSet<>(Arrays.asList("percevoir", "contempler", "voir", "découvrir", "discerner ")));
        symbols.put("syn_meuble", new HashSet<>(Arrays.asList("un coffre", "une table", "un abat-jour", "une armoire", "une berceau", "un buffet", "un canapé", "un banc", "une causeuse", "une cheminée", "une commode", "un fauteuil", "un écritoire", "un guéridon", "une horloge", "un lit", "une malle", "une méridienne", "un sofa")));
        symbols.put("syn_monstre", new HashSet<>(Arrays.asList("une ghoule", "un orc", "un golem", "un troll", "un vampire", "un rat enragé")));
        symbols.put("syn_se_trouver", new HashSet<>(Arrays.asList("entrez",  "être", "se situer")));
        symbols.put("syn_entrer", new HashSet<>(Arrays.asList("se trouver", "pénétrer", "plonger", "s'engouffrer", "se plonger", "rentrer", "s'insinuer")));
        symbols.put("syn_piece", new HashSet<>(Arrays.asList("une pièce", "une chambre", "un appentis", "une cellule", "un couloir")));
        symbols.put("syn_porte", new HashSet<>(Arrays.asList("une porte #adj_porte.f#", "une barrière #adj_porte.f#", "un battant #adj_porte#", "un portail #adj_porte#", "un couloir #adj_mur#")));

        symbols.put("desc_piece", new HashSet<>(Arrays.asList("mesure environ 10 mètres de côté")));
        symbols.put("partie_piece", new HashSet<>(Arrays.asList("les murs", "les sols", "les plafonds")));
        symbols.put("coul_plante", new HashSet<>(Arrays.asList("noir", "vert")));
        symbols.put("endroit_piece", new HashSet<>(Arrays.asList("dans le coin droit", "dans le coin gauche", "au milieu", "dans un coin", "au fond")));
        symbols.put("porte_description", new HashSet<>(Arrays.asList("se dresse #syn_porte#.")));

        symbols.put("monster_description", new HashSet<>(Arrays.asList("[monstre:syn_monstre]Vous #syn_voir.z# également #monstre#.")));
        symbols.put("room_description", new HashSet<>(Arrays.asList("[type_piece:syn_piece][meuble:syn_meuble]Vous #syn_se_trouver.z# dans #type_piece#. #partie_piece.c# sont #adj_mur.s#. #endroit_piece.c# de #type_piece.ad# vous #syn_voir.z# #meuble#.")));
        symbols.put("ending_description", new HashSet<>(Arrays.asList("Peu importe ce que vous voyez en réalité. Devant vous se trouve le trésor que vous étiez venu chercher. Félicitations!")));
    }
}
