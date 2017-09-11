package models;

import services.tracery.TraceryResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by draluy on 11/09/2017.
 */
public class Object {
    private TraceryResult description;
    private Map<ObjectType, Object> objects = new HashMap<>();

    public Object() {
    }

    public Object(final TraceryResult name) {
        this.description = name;
    }

    public TraceryResult getDescription() {
        return description;
    }

    public Map<ObjectType, Object> getObjects() {
        return objects;
    }
}

