package persistence;


import model.Bullet;
import model.Enemy;
import model.Player;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

// Class was modelled from JsonTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    protected void checkEnemy(Enemy e, int xpos, int ypos, int xdir, int ydir, int type) {
        assertEquals(xpos, e.getXPosition());
        assertEquals(ypos, e.getYPosition());
        assertEquals(xdir, e.getXDir());
        assertEquals(ydir, e.getYDir());
        assertEquals(type, e.getType());
    }

    protected void checkBullet(Bullet b, int xpos, int ypos, int xdir, int ydir, int damage, Color color) {
        assertEquals(xpos, b.getXPosition());
        assertEquals(ypos, b.getYPosition());
        assertEquals(xdir, b.getXDir());
        assertEquals(ydir, b.getYDir());
        assertEquals(damage, b.getDamage());
        assertEquals(color.getRGB(), b.getColor().getRGB());
    }

    protected void checkPlayer(Player p, int xpos, int ypos, int xdir, int ydir, int damage, int health,
                               int speed, int projNum) {
        assertEquals(xpos, p.getXPosition());
        assertEquals(ypos, p.getYPosition());
        assertEquals(xdir, p.getXDir());
        assertEquals(ydir, p.getYDir());
        assertEquals(damage, p.getDamage());
        assertEquals(health, p.getHealth());
        assertEquals(speed, p.getSpeed());
        assertEquals(projNum, p.getProjectileNumber());
    }
}
