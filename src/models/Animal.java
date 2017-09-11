package models;

import services.tracery.TraceryResult;

/**
 * Created by draluy on 06/09/2017.
 */
public interface Animal{
    int getNbLifePoints();
    void setNbLifePoints(int points);
    TraceryResult getDescription();
    void setDescription(String description);
}
