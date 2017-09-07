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
        symbols.put("desc_piece", new HashSet<>(Arrays.asList("mesure environ 10 mètres de côté")));
        symbols.put("adj_piece", new HashSet<>(Arrays.asList("hexagonale", "richement décorée")));
        symbols.put("partie_piece", new HashSet<>(Arrays.asList("les murs", "les sols", "les plafonds")));
        symbols.put("adj_mur", new HashSet<>(Arrays.asList("nu", "suintant", "en pierre", "sombre")));
        symbols.put("syn_voir", new HashSet<>(Arrays.asList("percevoir", "contempler", "voir", "découvrir", "discerner ")));
        symbols.put("coul_plante", new HashSet<>(Arrays.asList("noir", "vert")));
        symbols.put("meuble", new HashSet<>(Arrays.asList("un coffre", "une table", "un abat-jour", "une armoire", "une berceau", "un buffet", "un canapé", "un banc", "une causeuse", "une cheminée", "une commode", "un fauteuil", "un écritoire", "un guéridon", "une horloge", "un lit", "une malle", "une méridienne", "un sofa")));
        symbols.put("nom_monstre", new HashSet<>(Arrays.asList("une ghoule", "un orc", "un golem", "un troll", "un vampire", "un rat enragé")));
        symbols.put("syn_entrer", new HashSet<>(Arrays.asList("entrez", "pénétrer", "plonger", "s'engouffrer", "se plonger", "rentrer", "s'insinuer")));
        symbols.put("adj_porte", new HashSet<>(Arrays.asList("grand", "robuste", "solide", "noir", "en planches épaisses", "à l'aspect patiné")));
        symbols.put("syn_piece", new HashSet<>(Arrays.asList("une pièce", "une chambre", "un appentis", "une cellule", "un couloir")));
        symbols.put("endroit_piece", new HashSet<>(Arrays.asList("dans le coin droit", "dans le coin gauche", "au milieu de la pièce", "dans un coin", "devant vous", "tout au fond")));

        symbols.put("porte_description", new HashSet<>(Arrays.asList("se dresse #syn_porte#.")));
        symbols.put("syn_porte", new HashSet<>(Arrays.asList("une porte #adj_porte.f#", "une barrière #adj_porte.f#", "un battant #adj_porte#", "un portail #adj_porte#", "un couloir #adj_mur#")));

        symbols.put("room_description", new HashSet<>(Arrays.asList("[type_piece:syn_piece]Vous #syn_entrer.z# dans #type_piece#. #partie_piece.c# sont #adj_mur.s#. #endroit_piece.c# de #type_piece.ad# vous #syn_voir.z# #meuble#.")));
    }
}
