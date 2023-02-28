package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// A projectile in the game shot out by the player or an enemy
public class Bullet extends Object implements Writable {
    private double ydir;
    private double xdir;
    private Color color;
    private int damage;
    private int speed = 1;

    //EFFECTS: creates a bullet with a x-direction, y-direction, damage, speed, and color
    public Bullet(double xposition, double yposition, double xdir, double ydir,  int damage, Color color) {
        super(xposition, yposition);
        this.xdir = xdir;
        this.ydir = ydir;
        this.damage = damage;
        this.color = color;
    }

    // MODIFIES: this;
    // EFFECTS: Advanced the bullet x position by xdir * speed and y position by ydir * speed. Returns false if
    // either bullet coordinate goes beyond the screen size. Else returns true.
    public boolean tick() {
        double newXPosition = this.xposition + (this.xdir * this.speed);
        double newYPosition = this.yposition + (this.ydir * this.speed);
        this.xposition = newXPosition;
        this.yposition = newYPosition;
        return (!(newYPosition > Game.WINDOWY
                || newXPosition > Game.WINDOWX
                || newXPosition < 0
                || newYPosition < 0));
    }

    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(color);
        g.fillRect((int) getXPosition() - 5, (int) getYPosition() - 5, 7, 7);
        g.setColor(savedCol);
    }

    // EFFECTS: return bullet damage
    public int getDamage() {
        return this.damage;
    }

    // EFFECTS: return bullet xdir
    public double getXDir() {
        return this.xdir;
    }

    // EFFECTS: return bullet ydir
    public double getYDir() {
        return this.ydir;
    }

    // EFFECTS: return bullet speed
    public int getSpeed() {
        return this.speed;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("xposition", xposition);
        json.put("yposition", yposition);
        json.put("xdir", xdir);
        json.put("ydir", ydir);
        json.put("damage", damage);
        json.put("colorR", color.getRed());
        json.put("colorG", color.getGreen());
        json.put("colorB", color.getBlue());
        return json;
    }

}
