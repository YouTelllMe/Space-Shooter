package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// An Enemy in the game
public class Enemy extends Entity implements Writable {
    public static final int speedFactor = 10;
    public static final int bulletSpeedFactor = 30;
    protected int type;

    // EFFECTS: makes an enemy with a xposition, yposition, xdir, ydir, damage, speed, and health
    public Enemy(double xposition, double yposition, double xdir, double ydir, int type) {
        super(xposition, yposition);
        this.type = type;
        this.health = type;
        this.damage = type;
        this.speed = 1;
        this.hitbox = type * 6;
        this.xdir = xdir;
        this.ydir = ydir;
        if (type == 3) {
            this.color = Color.YELLOW;
        } else if (type == 5) {
            this.color = Color.ORANGE;
        } else if (type == 10) {
            this.color = Color.RED;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the enemy xposition by (xdir * speed) and yposition by (ydir * speed)
    public void tick() {
        double newX = this.xposition + this.xdir * speed / speedFactor;
        double newY = this.yposition + this.ydir * speed / speedFactor;
        if (!checkWall(newX, newY)) {
            this.xposition = newX;
            this.yposition = newY;
        } else {
            this.xdir *= -1;
            this.ydir *= -1;
        }
    }

    public void shoot(Game g) {
        double xdir = g.getPlayer().getXPosition() - getXPosition();
        double ydir =  g.getPlayer().getYPosition() - getYPosition();
        double normalizeFactor = 5 / Math.sqrt(xdir * xdir + ydir * ydir);
        Bullet bullet = super.shoot(xdir * normalizeFactor / bulletSpeedFactor,
                ydir * normalizeFactor / bulletSpeedFactor);
        g.addBullet(bullet, false);
    }

    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(color);
        g.fillRect((int) (getXPosition() - getHitbox() / 2), (int) (getYPosition() - getHitbox() / 2),
                (int) getHitbox(), (int) getHitbox());
        g.setColor(savedCol);
    }

    // EFFECTS: returns the type of enemy
    public int getType() {
        return this.type;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("xposition", xposition);
        json.put("yposition", yposition);
        json.put("xdir", xdir);
        json.put("ydir", ydir);
        json.put("type", type);
        return json;
    }
}
