package model;

import java.awt.*;

public class SmallEnemy extends Enemy {

    // EFFECTS: Create a SmallEnemy with at the proper xposition, yposition, with an xdirection and ydirection
    public SmallEnemy(double xposition, double yposition, double xdir, double ydir) {
        super(xposition, yposition, xdir, ydir, 3);
    }
}
