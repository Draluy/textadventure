package services;

import models.Direction;
import models.Player;
import models.Room;

import java.io.PrintStream;

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
                "Vous apercevez " + size + " sorties. " + sortiesLabel
                : "Cette pièce n'a pas d'issues.");

        out.println("Quel est votre choix ?");
    }

    private String getDirectionsLabel(final Room room) {
        String result="";

        for (Direction dir : room.getExits().keySet()) {
            result+= "Au "+dir.getValue();

            if (room.getExits().get(dir) != null){
                result+= ", "+room.getExits().get(dir).doorDescription;
            }else {
                result+= ".";
            }
        }

        return result;
    }

    public void display(String s) {
        System.out.println(s);
    }
}
