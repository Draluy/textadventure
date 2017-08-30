package models;

/**
 * Created by draluy on 30/08/2017.
 */
public class Monster {
    private String name;
    private int nbLifePoints = 20;

    public Monster(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNbLifePoints() {
        return nbLifePoints;
    }
}