package model;

import java.awt.*;

public class MediumEnemy extends Enemy {

    // EFFECTS: Create a MediunEnemy with at the proper xposition, yposition, with an xdirection and ydirection
    public MediumEnemy(double xposition, double yposition, double xdir, double ydir) {
        super(xposition, yposition, xdir, ydir, 5);
    }
}
