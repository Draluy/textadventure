package services.grid;

import models.Direction;
import models.Door;
import models.Room;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * See http://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm
 */
public class PrimGridGenerator implements GridGenerator {

    public static int HEIGHT = 5;
    public static int WIDTH = 5;


    @Override
    public Room[][] generate(int height, int width) {
        final Room[][] result = new Room[WIDTH][HEIGHT];

        initMaze(result);

        final Point randomCoordinates = getRandomCoordinates();
        final Room startingRoom = result[randomCoordinates.x][randomCoordinates.y];

        final Set<Room> visitedRooms = new HashSet<>();

        visitedRooms.add(startingRoom);

        final Set<Room> frontierCells = getFrontierCells(visitedRooms, result);

        while (visitedRooms.size() < HEIGHT * WIDTH) {
            //take 1 at random
            final Room randomRoom = getRandomRoom(frontierCells);

            visitedRooms.add(randomRoom);

             final Room bridgeRoom = getAdjacentCells(randomRoom, result).stream()
                    .filter(room -> visitedRooms.contains(room))
                    .findAny().get();

            connectRooms (bridgeRoom, randomRoom);

            frontierCells.clear();
            frontierCells.addAll(getFrontierCells(visitedRooms, result));
        }

        return result;
    }

    private void connectRooms(final Room bridgeRoom, final Room randomRoom) {
        if (randomRoom.getX() < bridgeRoom.getX()){
            //New room at west
            final Door door = new Door(Direction.WEST);
            bridgeRoom.getExits().put(door, randomRoom);
            randomRoom.getExits().put(new Door(Direction.EAST, door.getDescription()), bridgeRoom);
        }else if (randomRoom.getX() > bridgeRoom.getX()){
            //New room at east
            final Door door = new Door(Direction.EAST);
            bridgeRoom.getExits().put(door, randomRoom);
            randomRoom.getExits().put(new Door(Direction.WEST, door.getDescription()), bridgeRoom);
        }else if (randomRoom.getY() > bridgeRoom.getY()){
            //New room at south
            final Door door = new Door(Direction.SOUTH);
            bridgeRoom.getExits().put(door, randomRoom);
            randomRoom.getExits().put(new Door(Direction.NORTH, door.getDescription()), bridgeRoom);
        }else if (randomRoom.getY() < bridgeRoom.getY()) {
            //New room at north
            final Door door = new Door(Direction.NORTH);
            bridgeRoom.getExits().put(door, randomRoom);
            randomRoom.getExits().put(new Door(Direction.SOUTH, door.getDescription()), bridgeRoom);
        }
    }

    private Point getRandomCoordinates() {
        int y = ThreadLocalRandom.current().nextInt(0, HEIGHT);
        int x = ThreadLocalRandom.current().nextInt(0, WIDTH);

        return new Point(x, y);
    }

    private Set<Room> getFrontierCells(final Set<Room> visitedRooms, final Room[][] maze) {
        Set<Room> points = new HashSet<>();
        visitedRooms.stream().forEach(room -> {
            points.addAll(getAdjacentCells(room, maze));
        });
        points.removeAll(visitedRooms);

        return points;
    }

    private Set<Room> getAdjacentCells(final Room room, final Room[][] maze) {
        final Set<Room> rooms = new HashSet<>();
        final List<Point> points = Arrays.asList(new Point(-1, 0), new Point(1, 0), new Point(0, 1), new Point(0, -1));
        points.stream()
                .forEach(point -> {
                    int newX = point.x + room.getX();
                    int newY = point.y + room.getY();
                    if (newX >= 0 && newY >= 0 && newX < WIDTH && newY < HEIGHT) {
                        rooms.add(maze[newX][newY]);
                    }
                });

        return rooms;
    }

    private Room getRandomRoom(final Collection<Room> collection) {
        Room result = collection.iterator().next();
        int item = new Random().nextInt(collection.size());
        int i = 0;
        for (Room obj : collection) {
            if (i == item) {
                result = obj;
                break;
            }
            i++;
        }
        return result;
    }

    private void initMaze(final Room[][] maze) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                maze[i][j] = new Room(i, j);
            }
        }
    }

}