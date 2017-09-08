package models;

import services.tracery.TraceryService;

/**
 * Created by redeyed on 8/20/17.
 */
public class Door {
    private Direction direction;
    private String description;

    public Door(Direction direction) {
        this.direction = direction;
        this.description = TraceryService.instance.parse("porte_description").getParsedText();
    }

    public Door(Direction direction, String description) {
        this.direction = direction;
        this.description = description;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }
}
