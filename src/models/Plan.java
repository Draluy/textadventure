package models;

import services.grid.GridService;

/**
 * Created by draluy on 16/08/2017.
 */
public class Plan {

    private Plan() {
        //todo: take one room at random
        final Room [][] maze = GridService.instance.initGrid();
        current = maze[0][0];
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
