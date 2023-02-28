package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    int xOn;
    int x;
    int xBeyond;
    int yOn;
    int y;
    int yBeyond;

    Entity e;

    @BeforeEach
    public void setup() {
        this.e = new Entity(0,0);
    }

    @Test
    public void checkNotWall() {
        x = 0;
        y = 0;
        assertFalse(e.checkWall(x,y));
    }

    @Test
    public void checkWallLeftTest() {
        this.xOn = 0;
        this.xBeyond = -1;
        this.y = 0;
        assertTrue(e.checkWall(xOn, y));
        assertTrue(e.checkWall(xBeyond, y));

    }

    @Test
    public void checkWallRightTest() {
        this.xOn = (int) (Game.WINDOWX);
        this.xBeyond = (int) (Game.WINDOWX) + 1;
        this.y = 0;
        assertTrue(e.checkWall(xOn, y));
        assertTrue(e.checkWall(xBeyond, y));
    }

    @Test
    public void checkWallTopTest() {
        this.yOn = 0;
        this.yBeyond = -1;
        this.x = 0;
        assertTrue(e.checkWall(x, yOn));
        assertTrue(e.checkWall(x, yBeyond));
    }

    @Test
    public void checkWallBottomTest() {
        this.yOn = (int) (Game.WINDOWY);
        this.yBeyond = (int) (Game.WINDOWY) + 1;
        this.x = 0;
        assertTrue(e.checkWall(x, yOn));
        assertTrue(e.checkWall(x, yBeyond));
    }

    @Test
    public void checkCornerTopLeftTest() {
        this.xOn = 0;
        this.xBeyond = -1;
        this.yOn = 0;
        this.yBeyond = -1;
        assertTrue(e.checkWall(xOn, yOn));
        assertTrue(e.checkWall(xOn, yBeyond));
        assertTrue(e.checkWall(xBeyond, yOn));
        assertTrue(e.checkWall(xBeyond, yBeyond));
    }

    @Test
    public void checkCornerBottomLeftTest() {
        this.xOn = 0;
        this.xBeyond = -1;
        this.yOn = (int) (Game.WINDOWY);
        this.yBeyond = (int) (Game.WINDOWY) + 1;
        assertTrue(e.checkWall(xOn, yOn));
        assertTrue(e.checkWall(xOn, yBeyond));
        assertTrue(e.checkWall(xBeyond, yOn));
        assertTrue(e.checkWall(xBeyond, yBeyond));
    }

    @Test
    public void checkCornerTopRightTest() {
        this.xOn = (int) (Game.WINDOWX);
        this.xBeyond = (int) (Game.WINDOWX) + 1;
        this.yOn = 0;
        this.yBeyond = -1;
        assertTrue(e.checkWall(xOn, yOn));
        assertTrue(e.checkWall(xOn, yBeyond));
        assertTrue(e.checkWall(xBeyond, yOn));
        assertTrue(e.checkWall(xBeyond, yBeyond));
    }

    @Test
    public void checkCornerBottomRightTest() {
        this.xOn = (int) (Game.WINDOWX);
        this.xBeyond = (int) (Game.WINDOWX) + 1;
        this.yOn = (int) (Game.WINDOWY);
        this.yBeyond = (int) (Game.WINDOWY) + 1;
        assertTrue(e.checkWall(xOn, yOn));
        assertTrue(e.checkWall(xOn, yBeyond));
        assertTrue(e.checkWall(xBeyond, yOn));
        assertTrue(e.checkWall(xBeyond, yBeyond));
    }
}
