package models;

import services.tracery.TraceryResult;

/**
 * Created by draluy on 30/08/2017.
 */
public class Monster implements Animal {
    private TraceryResult description;
    private int nbLifePoints = 20;

    public Monster(final TraceryResult name) {
        this.description = name;
    }

    @Override
    public TraceryResult getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setParsedText(description);
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