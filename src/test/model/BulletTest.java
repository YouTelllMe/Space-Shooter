package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    Bullet bulletX;
    Bullet bulletY;
    Bullet bulletNegX;
    Bullet bulletNegY;
    double xdir;
    double ydir;
    int windowSize;

    @BeforeEach
    public void setup(){
        this.bulletX = new Bullet(0,0,20,0,2, Color.WHITE);
        this.bulletY = new Bullet(0,0,0,20,2,Color.WHITE);
        this.bulletNegX = new Bullet(0,0,-20,0,2,Color.WHITE);
        this.bulletNegY = new Bullet(0,0,0,-20,2,Color.WHITE);
        this.xdir = bulletX.getXDir();
        this.ydir = bulletX.getYDir();
        this.windowSize = 100;
    }

    @Test
    public void tickOnceTest(){
        assertEquals(0, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());
        assertTrue(bulletX.tick());
        assertEquals(20, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());

    }

    @Test
    public void tickMultipleTest(){
        assertEquals(0, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());
        assertTrue(bulletX.tick());
        assertTrue(bulletX.tick());
        assertEquals(40, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());

    }

    @Test
    public void tickBeyondWindowXTest(){
        assertEquals(0, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());
        assertTrue(bulletX.tick());
        assertTrue(bulletX.tick());
        assertFalse(bulletX.tick());
        assertEquals(60, bulletX.getXPosition());
        assertEquals(0, bulletX.getYPosition());
    }

    @Test
    public void tickBeyondWindowYTest(){
        assertEquals(0, bulletY.getXPosition());
        assertEquals(0, bulletY.getYPosition());
        assertTrue(bulletY.tick());
        assertTrue(bulletY.tick());
        assertFalse(bulletY.tick());
        assertEquals(0, bulletY.getXPosition());
        assertEquals(60, bulletY.getYPosition());
    }

    @Test
    public void tickBeyondWindowNegXTest(){
        assertEquals(0, bulletNegX.getXPosition());
        assertEquals(0, bulletNegX.getYPosition());
        assertTrue(bulletNegX.tick());
        assertTrue(bulletNegX.tick());
        assertFalse(bulletNegX.tick());
        assertEquals(-60, bulletNegX.getXPosition());
        assertEquals(0, bulletNegX.getYPosition());
    }

    @Test
    public void tickBeyondWindowNegYTest(){
        assertEquals(0, bulletNegY.getXPosition());
        assertEquals(0, bulletNegY.getYPosition());
        assertTrue(bulletNegY.tick());
        assertTrue(bulletNegY.tick());
        assertFalse(bulletNegY.tick());
        assertEquals(0, bulletNegY.getXPosition());
        assertEquals(-60, bulletNegY.getYPosition());
    }
}
