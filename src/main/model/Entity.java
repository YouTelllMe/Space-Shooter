package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// An Entity in the game (user or enemy)
public class Entity implements Writable {
    protected int health;
    protected double xposition;
    protected double yposition;
    protected double xdir;
    protected double ydir;
    protected double speed;
    protected int damage;
    protected Color color;
    protected double hitbox;

    // EFFECTS: creates an entity of the color white with a xposition and yposition
    public Entity(double xposition, double yposition) {
        this.xposition = xposition;
        this.yposition = yposition;
        this.color = Color.WHITE;
    }


    // EFFECTS: returns a bullet at the location of the entity towards a specified direction with the
    // same damage and color as the entity
    public Bullet shoot(double xdir, double ydir) {
        return new Bullet(xposition, yposition, xdir, ydir, damage, color);
    }

    // EFFECTS: returns whether an entity is colliding with the edge of the screen
    public boolean checkWall(double x, double y) {
        return (x <= 0
                || x >= Game.WINDOWX
                || y <= 0
                || y >= Game.WINDOWY);
    }

    // EFFECTS: returns entity xposition
    public double getXPosition() {
        return this.xposition;
    }

    // EFFECTS: returns entity yposition
    public double getYPosition() {
        return this.yposition;
    }

    // EFFECTS: returns entity speed
    public double getSpeed() {
        return this.speed;
    }

    // EFFECTS: returns entity health
    public int getHealth() {
        return this.health;
    }

    // MOFIDIES: this
    // EFFECTS: sets entity health of the given health parameter
    public void setHealth(int health) {
        this.health = health;
    }

    // EFFECTS: get entity damage
    public int getDamage() {
        return this.damage;
    }

    //EFFECTS: get entity hitbox
    public double getHitbox() {
        return this.hitbox;
    }

    //EFFECTS: return enemy xdir
    public double getXDir() {
        return this.xdir;
    }

    //EFFECTS: return enemy ydir
    public double getYDir() {
        return this.ydir;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject();
    }

}
