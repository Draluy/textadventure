package models;

/**
 * Created by draluy on 16/08/2017.
 */
public class Player implements Animal{
    private int points = 20;

    private String name = "Player";

    @Override
    public int getNbLifePoints() {
        return points;
    }

    @Override
    public void setNbLifePoints(int points) {
        this.points = points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
