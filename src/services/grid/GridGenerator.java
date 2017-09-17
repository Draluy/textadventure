package services.grid;

import models.Room;

public interface GridGenerator {

    Room[][] generate(int height, int width);
}