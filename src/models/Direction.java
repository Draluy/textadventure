package models;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by draluy on 16/08/2017.
 */
public enum Direction {
    NORTH("nord"), SOUTH("sud"), EAST("est"), WEST("ouest");

    private String value;

    Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Direction> fromValue(String value){
        return Arrays.stream(Direction.values()).filter(dir -> dir.getValue().equalsIgnoreCase(value))
                .findAny();
    }

    public static Direction getOpposite(Direction direction) {
        if (direction == null) {
            return null;
        }

        Direction result = null;
        switch (direction) {
            case NORTH:
                result = SOUTH;
                break;
            case SOUTH:
                result = NORTH;
                break;
            case EAST:
                result = WEST;
                break;
            case WEST:
                result = EAST;
                break;
        }

        return result;
    }
}
