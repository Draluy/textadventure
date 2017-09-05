package services.input;

import models.Direction;

/**
 * Created by draluy on 05/09/2017.
 */
public enum Action {
    NORTH(Direction.NORTH.getValue(), true),
    SOUTH(Direction.SOUTH.getValue(), true),
    EAST(Direction.EAST.getValue(), true),
    WEST(Direction.WEST.getValue(), true),
    FIGHT("Combattre", false),
    EXIT("Exit", false);

    private String value;
    private boolean isDirection;

    Action(String value, final boolean isDirection) {
        this.value = value;
        this.isDirection = isDirection;
    }

    public String getValue() {
        return value;
    }

    public boolean isDirection() {
        return isDirection;
    }
}
