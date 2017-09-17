package services.grid;

import models.Room;

public class GridService {

    public static int HEIGHT = 5;
    public static int WIDTH = 5;

    private GridGenerator gridGenerator = new PrimGridGenerator();
    public final static GridService instance = new GridService();

    private GridService() {
    }

    public Room[][] initGrid () {
        return gridGenerator.generate(HEIGHT, WIDTH);
    }
}