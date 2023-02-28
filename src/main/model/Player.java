package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

public class Player extends Entity implements Writable {

    public static final int SPEEDFACTOR = 5;
    public static final int SPREADRANGE = 200;
    private int projectileNumber;

    // EFFECTS: creates a new user instance
    public Player(double xposition, double yposition) {
        super(xposition, yposition);
        this.health = 10;
        this.damage = 1;
        this.speed = 1;
        this.hitbox = 10;
        this.projectileNumber = 2;
        this.xdir = 0;
        this.ydir = 0;
    }

    // MODIFIES: this
    // EFFECTS: moves User in a combination of up, down, left, right directions by speed units,
    // doesn't change position if the user will go out of the window
    public boolean move(boolean up, boolean down, boolean left, boolean right) {
        double newX = this.xposition;
        double newY = this.yposition;
        if (up) {
            newY -= this.speed / SPEEDFACTOR;
        }
        if (down) {
            newY += this.speed / SPEEDFACTOR;
        }
        if (left) {
            newX -= this.speed / SPEEDFACTOR;
        }
        if (right) {
            newX += this.speed / SPEEDFACTOR;
        }
        if (!checkWall(newX, newY)) {
            this.xposition = newX;
            this.yposition = newY;
        }
        return !checkWall(newX, newY);
    }
    
    public void shoot(double xd, double yd, Game g) {
        double xdir = xd - getXPosition();
        double ydir = yd - getYPosition() - SPREADRANGE / 2;
        double rangeFactor = SPREADRANGE / (projectileNumber + 2);
        double normalizeFactor = 5 / Math.sqrt(xd * xd + yd * yd);
        for (int i = 1; i <= projectileNumber; i++) {
            Bullet bullet = super.shoot((xdir + i * rangeFactor) * normalizeFactor,
                    (ydir + i * rangeFactor) * normalizeFactor);
            g.addBullet(bullet, true);
        }
    }


    // MODIFIES: this
    // EFFECTS: add all effects of the powerup to the user's stat
    public void powerUp(PowerUp p) {
        this.health += p.getHealthBoost();
        this.speed += p.getSpeedBoost();
        this.damage += p.getDamageBoost();
        this.projectileNumber += p.getProjectileNumberBoost();
        EventLog.getInstance().logEvent(new Event("Player Powered Up"));
    }

    // EFFECTS: returns projectile number
    public int getProjectileNumber() {
        return this.projectileNumber;
    }

    // MODIFIES: this
    // EFFECTS: set player stats to the specified parameters, requires xpos and ypos to be <= |WINDOWSIZE|, xdir and
    // ydir to be 5 or -5 respectively, health >=0, damage>0, speed>0, projNum>0;
    public void setPlayer(double xpos, double ypos, double xdir, double ydir,
                          int health, int damage, double speed, int projNum) {
        this.xposition = xpos;
        this.yposition = ypos;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.projectileNumber = projNum;
        this.xdir = xdir;
        this.ydir = ydir;
    }

    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect((int) (getXPosition() - getHitbox() / 2), (int) (getYPosition() - getHitbox() / 2),
                (int) getHitbox(), (int) getHitbox());
        g.setColor(savedCol);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("xposition", xposition);
        json.put("yposition", yposition);
        json.put("xdir", xdir);
        json.put("ydir", ydir);
        json.put("health", health);
        json.put("damage", damage);
        json.put("speed", speed);
        json.put("projectileNumber", projectileNumber);
        return json;
    }
}
