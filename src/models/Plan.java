package models;

import services.PlanService;

/**
 * Created by draluy on 16/08/2017.
 */
public class Plan {
    private Plan() {
    }

    public static final Plan instance = new Plan();

    private Room current;

    public void initPlan() {
        current = new Room();
        PlanService.instance.generateExitsRand(current);
    }

    public Room getCurrentRoom() {
        return current;
    }

    public void setCurrent(Room current) {
        this.current = current;
    }
}
