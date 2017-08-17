package models;

/**
 * Created by draluy on 16/08/2017.
 */
public class Player {
    private int points = 20;

    private String name = "Player";

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
