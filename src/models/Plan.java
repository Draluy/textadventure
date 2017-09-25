package models;

import services.grid.GridService;

/**
 * Created by draluy on 16/08/2017.
 */
public class Plan {

    private Plan() {
        final Room[][] maze = GridService.instance.initGrid();
        current = maze[0][0];

        //mark the ending.
        maze[GridService.WIDTH - 1][GridService.HEIGHT - 1].setEnding(true);
    }

    public static final Plan instance = new Plan();

    private Room current;

    public Room getCurrentRoom() {
        return current;
    }

    public void setCurrent(Room current) {
        this.current = current;
    }
}
