package services;

import models.Player;
import models.Room;

import java.io.PrintStream;
import java.util.stream.Collectors;

/**
 * Created by draluy on 16/08/2017.
 */
public class ScreenService {

    private ScreenService() {
    }
    public static final ScreenService instance = new ScreenService();

    public void display (Player player, Room room){
        PrintStream out = System.out;

        final int size = room.getExits().size();
        String sortiesLabel = String.join(",", room.getExits().keySet().stream().map(direction -> direction.name()).collect(Collectors.toSet()));
        out.println( size > 0?
                "Vous êtes dans une pièce comportant "+size+" sorties, au "+sortiesLabel
        : "Cette pièce n'a pas d'issues.");

        out.println("Quel est votre choix ?");
    }

    public void display(String s) {
        System.out.println(s);
    }
}
