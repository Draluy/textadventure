package models;

import services.tracery.TraceryResult;

/**
 * Created by draluy on 16/08/2017.
 */
public class Player implements Animal{
    private int points = 20;

    private TraceryResult description = new TraceryResult();

    @Override
    public int getNbLifePoints() {
        return points;
    }

    @Override
    public void setNbLifePoints(int points) {
        this.points = points;
    }

    @Override
    public TraceryResult getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description.setParsedText(description);
    }
}
