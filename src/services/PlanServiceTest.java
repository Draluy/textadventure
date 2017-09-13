package services;

import models.Room;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Created by draluy on 13/09/2017.
 */
class PlanServiceTest {

    @Test
    void generateExitsRand() {
        final List<Integer> values = new ArrayList<>();
        final int nbValues = 100;
        for (int i = 0; i < nbValues; i++) {
            Room room = new Room();
            PlanService.instance.generateExitsRand(room);
            values.add(room.getExits().size());
            System.out.println("Nb exits " + room.getExits().size());
        }
        final double mean = values.stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Nb exits mean = " + mean);
        System.out.println("Nb exits variance = " + getVariance(mean, values));
    }

    private double getVariance(double mean, final List<Integer> values) {
        double temp = 0;
        for (Integer a : values) {
            temp += (a - mean) * (a - mean);
        }
        return temp / (values.size() - 1);
    }
}