package models;

/**
 * Created by draluy on 30/08/2017.
 */
public class Monster implements Animal {
    private String name;
    private int nbLifePoints = 20;

    public Monster(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNbLifePoints() {
        return nbLifePoints;
    }

    @Override
    public void setNbLifePoints(int points) {
        this.nbLifePoints = points;
    }
}