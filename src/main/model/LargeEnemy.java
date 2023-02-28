package model;

import java.awt.*;

public class LargeEnemy extends Enemy {

    // EFFECTS: Create a LargeEnemy with at the proper xposition, yposition, with an xdirection and ydirection
    public LargeEnemy(double xposition, double yposition, double xdir, double ydir) {
        super(xposition, yposition, xdir, ydir, 10);

    }
}
