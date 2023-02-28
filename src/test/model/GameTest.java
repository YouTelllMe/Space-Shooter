package model;

import model.Bullet;
import model.Enemy;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game g;
    int randomX;
    int randomY;
    int randomXDir;
    int randomYDir;
    Bullet fb;
    Bullet fbb;
    Bullet ufb;
    Bullet ufbb;
    Enemy createdenemy;
    Enemy createdenemy2;
    Bullet collision0;
    Bullet collision1;
    Bullet collision2;
    Bullet collision3;
    Bullet collision4;
    List<Bullet> removeBullet;
    List<Enemy> removeEnemy;



    @BeforeEach
    public void setup() {
        this.g = new Game();
        this.randomX = 0;
        this.randomY = 0;
        this.randomXDir = 1;
        this.randomYDir = 1;
        this.fb = new Bullet(0, 0, 1, 1, 1, Color.WHITE);
        this.fbb = new Bullet(0, 0, 1, 3, 1, Color.WHITE);
        this.ufb = new Bullet(20, 20, 1, 1, 1, Color.WHITE);
        this.ufbb = new Bullet(20, 20, 1, 3, 1, Color.WHITE);
        this.createdenemy = g.makeEnemy(0, 0, 1, 1, 2);
        this.createdenemy2 = g.makeEnemy(0, 0, 2, 2, 2);
        this.collision0 = new Bullet(20, 20, 1, 1, 1, Color.WHITE);
        this.collision1 = new Bullet(4, 4, 0, 0, 1, Color.WHITE);
        this.collision2 = new Bullet(-4, -4, 0, 0, 3, Color.WHITE);
        this.collision3 = new Bullet(-4, -4, 0, 0, 10, Color.WHITE);
        this.collision4 = new Bullet(-4, -4, 0, 0, 10, Color.WHITE);
        this.removeBullet = new ArrayList<>();
        this.removeEnemy = new ArrayList<>();
    }

    @Test
    public void pauseUnpausedGameTest() {
        assertFalse(g.getGamePaused());
        g.pauseGame();
        assertTrue(g.getGamePaused());
    }

    @Test
    public void pausePausedGameTest() {
        g.pauseGame();
        assertTrue(g.getGamePaused());
        g.pauseGame();
        assertTrue(g.getGamePaused());
    }

    @Test
    public void UnpausePausedGameTest() {
        g.pauseGame();
        assertTrue(g.getGamePaused());
        g.unpauseGame();
        assertFalse(g.getGamePaused());
    }

    @Test
    public void UnpauseUnausedGameTest() {
        assertFalse(g.getGamePaused());
        g.unpauseGame();
        assertFalse(g.getGamePaused());
    }

    @Test
    public void generateEnemyTest() {
        assertEquals(0, g.getEntityList().size());
        g.generateEnemy();
        assertEquals(1, g.getEntityList().size());
    }

    @Test
    public void makeEnemySmallTest() {
        Enemy createdEnemy = g.makeEnemy(randomX, randomY, randomXDir, randomYDir, 2);
        assertEquals(3, createdEnemy.getType());
        assertEquals(9, createdEnemy.getHitbox());
        assertEquals(3, createdEnemy.getHealth());
        assertEquals(3, createdEnemy.getDamage());
        assertEquals(0, createdEnemy.getXPosition());
        assertEquals(0, createdEnemy.getYPosition());
        assertEquals(1, createdEnemy.getXDir());
        assertEquals(1, createdEnemy.getYDir());
    }

    @Test
    public void makeEnemyMediumTest() {
        Enemy createdEnemy = g.makeEnemy(randomX, randomY, randomXDir, randomYDir, 1);
        assertEquals(5, createdEnemy.getType());
        assertEquals(15, createdEnemy.getHitbox());
        assertEquals(5, createdEnemy.getHealth());
        assertEquals(5, createdEnemy.getDamage());
        assertEquals(0, createdEnemy.getXPosition());
        assertEquals(0, createdEnemy.getYPosition());
        assertEquals(1, createdEnemy.getXDir());
        assertEquals(1, createdEnemy.getYDir());
    }

    @Test
    public void makeEnemyLargeTest() {
        Enemy createdEnemy = g.makeEnemy(randomX, randomY, randomXDir, randomYDir, 0);
        assertEquals(10, createdEnemy.getType());
        assertEquals(30, createdEnemy.getHitbox());
        assertEquals(10, createdEnemy.getHealth());
        assertEquals(10, createdEnemy.getDamage());
        assertEquals(0, createdEnemy.getXPosition());
        assertEquals(0, createdEnemy.getYPosition());
        assertEquals(1, createdEnemy.getXDir());
        assertEquals(1, createdEnemy.getYDir());
    }

    @Test
    public void addFriendlyBulletTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.addBullet(fb, true);
        assertEquals(1, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
        Bullet b2 = g.getFriendlyBulletList().get(0);
        assertEquals(fb.getDamage(), b2.getDamage());
        assertEquals(fb.getSpeed(), b2.getSpeed());
        assertEquals(fb.getXPosition(), b2.getXPosition());
        assertEquals(fb.getYPosition(), b2.getYPosition());
        assertEquals(fb.getXDir(), b2.getXDir());
        assertEquals(fb.getYDir(), b2.getYDir());
    }

    @Test
    public void addUnfriendlyBulletTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.addBullet(fb, false);
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(1, g.getUnfriendlyBulletList().size());
        Bullet b2 = g.getUnfriendlyBulletList().get(0);
        assertEquals(fb.getDamage(), b2.getDamage());
        assertEquals(fb.getSpeed(), b2.getSpeed());
        assertEquals(fb.getXPosition(), b2.getXPosition());
        assertEquals(fb.getYPosition(), b2.getYPosition());
        assertEquals(fb.getXDir(), b2.getXDir());
        assertEquals(fb.getYDir(), b2.getYDir());
    }

    @Test
    public void gametickNullTest() {
        assertEquals(0, g.getEntityList().size());
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.gameTick();
        assertEquals(0, g.getEntityList().size());
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
    }

    @Test
    public void gametickBulletTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        g.addBullet(fb, true);
        assertEquals(1, g.getFriendlyBulletList().size());
        g.gameTick();
        Bullet b2 = g.getFriendlyBulletList().get(0);
        assertEquals(1, b2.getXPosition());
        assertEquals(1, b2.getYPosition());

    }

    @Test
    public void gametickMutipleBulletTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.addBullet(fb, true);
        g.addBullet(fbb, true);
        g.addBullet(ufb, false);
        g.addBullet(ufbb, false);
        assertEquals(2, g.getFriendlyBulletList().size());
        assertEquals(2, g.getUnfriendlyBulletList().size());
        g.gameTick();
        Bullet fb2 = g.getFriendlyBulletList().get(0);
        Bullet fbb2 = g.getFriendlyBulletList().get(1);
        Bullet ufb2 = g.getUnfriendlyBulletList().get(0);
        Bullet ufbb2 = g.getUnfriendlyBulletList().get(1);
        System.out.println(g.getUnfriendlyBulletList().size());
        assertEquals(1, fb2.getXPosition());
        assertEquals(1, fb2.getYPosition());
        assertEquals(1, fbb2.getXPosition());
        assertEquals(3, fbb2.getYPosition());
        assertEquals(21, ufb2.getXPosition());
        assertEquals(21, ufb2.getYPosition());
        assertEquals(21, ufbb2.getXPosition());
        assertEquals(23, ufbb2.getYPosition());
    }

    @Test
    public void gametickEnemiesTest() {
        assertEquals(0, g.getEntityList().size());
        g.addEnemy(createdenemy);
        assertEquals(1, g.getEntityList().size());
        g.gameTick();
        assertEquals(0, g.getEntityList().get(0).getXPosition());
        assertEquals(1, g.getEntityList().get(0).getYPosition());

    }

    @Test
    public void gametickMultipleEnemiesTest() {
        assertEquals(0, g.getEntityList().size());
        g.addEnemy(createdenemy);
        g.addEnemy(createdenemy2);
        assertEquals(2, g.getEntityList().size());
        g.gameTick();
        assertEquals(1, g.getEntityList().get(0).getXPosition());
        assertEquals(1, g.getEntityList().get(0).getYPosition());
        assertEquals(2, g.getEntityList().get(1).getXPosition());
        assertEquals(2, g.getEntityList().get(1).getYPosition());
    }

    @Test
    public void checkBulletCollisionTrueTest() {
        assertTrue(g.checkBulletCollision(createdenemy, collision1));
        assertTrue(g.checkBulletCollision(createdenemy, collision2));
        assertTrue(g.checkBulletCollision(createdenemy, fb));

    }

    @Test
    public void checkBulletCollisionFalseTest() {
        assertFalse(g.checkBulletCollision(createdenemy, collision0));
    }


    @Test
    public void checkFriendlyBulletNullTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        g.checkFriendlyBullet();
        assertEquals(0, g.getFriendlyBulletList().size());
    }

    @Test
    public void checkFriendlyBulletNoCollisionTest() {
        g.addEnemy(createdenemy);
        g.addBullet(collision0, true);
        assertEquals(1, g.getFriendlyBulletList().size());
        assertEquals(1, g.getEntityList().size());
        Enemy e = g.getEntityList().get(0);
        assertEquals(3, e.getHealth());
        g.checkFriendlyBullet();
        assertEquals(1, g.getFriendlyBulletList().size());
        assertEquals(1, g.getEntityList().size());
        assertEquals(3, e.getHealth());
    }

    @Test
    public void checkFriendlyBulletCollisionTest() {
        g.addEnemy(createdenemy);
        g.addBullet(collision1, true);
        assertEquals(1, g.getFriendlyBulletList().size());
        assertEquals(1, g.getEntityList().size());
        Enemy e = g.getEntityList().get(0);
        assertEquals(3, e.getHealth());
        g.checkFriendlyBullet();
        assertEquals(0, g.getFriendlyBulletList().size());
        assertEquals(1, g.getEntityList().size());
        assertEquals(2, e.getHealth());
    }

    @Test
    public void checkFriendlyBulletKillTest() {
        g.addEnemy(createdenemy);
        g.addBullet(collision1, true);
        g.addBullet(collision2, true);
        g.addBullet(collision3, true);
        assertEquals(3, g.getFriendlyBulletList().size());
        assertEquals(1, g.getEntityList().size());
        Enemy e = g.getEntityList().get(0);
        assertEquals(3, e.getHealth());
        g.checkFriendlyBullet();
        assertEquals(0, g.getEntityList().size());
        assertEquals(-1, e.getHealth());
        assertEquals(1, g.getFriendlyBulletList().size());
    }

    @Test
    public void checkUnfriendlyBulletNullTest() {
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.checkUnFriendlyBullet();
        assertEquals(0, g.getUnfriendlyBulletList().size());
    }

    @Test
    public void checkUnfriendlyBulletNoCollisionTest() {
        g.addBullet(collision0, false);
        assertEquals(1, g.getUnfriendlyBulletList().size());
        assertEquals(10, g.getPlayer().getHealth());
        g.checkUnFriendlyBullet();
        assertEquals(1, g.getUnfriendlyBulletList().size());
        assertEquals(10, g.getPlayer().getHealth());
    }

    @Test
    public void checkUnfriendlyBulletCollisionTest() {
        g.addBullet(collision1, false);
        assertEquals(1, g.getUnfriendlyBulletList().size());
        assertEquals(10, g.getPlayer().getHealth());
        g.checkUnFriendlyBullet();
        assertEquals(0, g.getUnfriendlyBulletList().size());
        assertEquals(9, g.getPlayer().getHealth());
    }

    @Test
    public void checkUnfriendlyBulletKillTest() {
        g.addBullet(collision1, false);
        g.addBullet(collision2, false);
        g.addBullet(collision3, false);
        assertEquals(3, g.getUnfriendlyBulletList().size());
        assertEquals(10, g.getPlayer().getHealth());
        g.checkUnFriendlyBullet();
        assertEquals(-4, g.getPlayer().getHealth());
        assertEquals(0, g.getUnfriendlyBulletList().size());
    }

    @Test
    public void clearFriendlyBulletNullTest() {
        assertEquals(0, g.getFriendlyBulletList().size());
        g.clearBullets(this.removeBullet, true);
        assertEquals(0, g.getFriendlyBulletList().size());
    }

    @Test
    public void clearFriendlyBulletTest() {
        g.addBullet(fb, true);
        assertEquals(1, g.getFriendlyBulletList().size());
        assertTrue(g.getFriendlyBulletList().contains(fb));
        this.removeBullet.add(fb);
        g.clearBullets(this.removeBullet, true);
        assertEquals(0, g.getFriendlyBulletList().size());
    }

    @Test
    public void clearUnfriendlyBulletNullTest() {
        assertEquals(0, g.getUnfriendlyBulletList().size());
        g.clearBullets(this.removeBullet, false);
        assertEquals(0, g.getUnfriendlyBulletList().size());
    }

    @Test
    public void clearUnfriendlyBulletTest() {
        g.addBullet(fb, false);
        assertEquals(1, g.getUnfriendlyBulletList().size());
        assertTrue(g.getUnfriendlyBulletList().contains(fb));
        this.removeBullet.add(fb);
        g.clearBullets(this.removeBullet, false);
        assertEquals(0, g.getUnfriendlyBulletList().size());
    }

    @Test
    public void clearEnemyNullTest() {
        assertEquals(0, g.getEntityList().size());
        g.clearEnemy(this.removeEnemy);
        assertEquals(0, g.getEntityList().size());
    }

    @Test
    public void clearEnemyTest() {
        g.addEnemy(createdenemy);
        assertEquals(1, g.getEntityList().size());
        assertTrue(g.getEntityList().contains(createdenemy));
        this.removeEnemy.add(createdenemy);
        g.clearEnemy(this.removeEnemy);
        assertEquals(0, g.getEntityList().size());
    }
}