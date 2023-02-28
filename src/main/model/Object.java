package model;

import org.json.JSONObject;
import persistence.Writable;

public class Object implements Writable {
    protected double xposition;
    protected double yposition;

    // EFFECTS: creates an Object instance
    public Object(double xposition, double yposition) {
        this.xposition = xposition;
        this.yposition = yposition;
    }


    // EFFECTS: return object xposition
    public double getXPosition() {
        return this.xposition;
    }

    // EFFECTS: return object yposition
    public double getYPosition() {
        return this.yposition;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject();
    }
}
