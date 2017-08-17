package models;

/**
 * Created by draluy on 16/08/2017.
 */
public enum Direction {
    NORTH, SOUTH, EAST, WEST;

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
