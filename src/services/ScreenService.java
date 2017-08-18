package services;

import models.Direction;
import models.Player;
import models.Room;

import java.io.PrintStream;
import java.util.TreeSet;

/**
 * Created by draluy on 16/08/2017.
 */
public class ScreenService {

    public static final ScreenService instance = new ScreenService();

    private ScreenService() {
    }

    public void display(Player player, Room room) {
        PrintStream out = System.out;

        final int size = room.getExits().size();
        String sortiesLabel = getDirectionsLabel(room);
        out.println(size > 0 ? room.roomDescription+
                "Vous apercevez " + size + " sorties, au " + sortiesLabel
                : "Cette pièce n'a pas d'issues.");

        out.println("Quel est votre choix ?");
    }

    private String getDirectionsLabel(final Room room) {
        String result = "";

        TreeSet<Direction> directions = new TreeSet<>(room.getExits().keySet());
        int i = 0;
        for (Direction dir : directions) {

            if (i == directions.size() - 1 && i > 0) {
                result += " et ";
            } else if (i > 0) {
                result += ", ";
            }

            result += dir.getValue();
            i++;
        }

        return result;
    }

    public void display(String s) {
        System.out.println(s);
    }
}
