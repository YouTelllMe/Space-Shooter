package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTest {
    Enemy enemy;
    Enemy wallEnemyX;
    Enemy wallEnemyY;
    Enemy wallEnemyNegX;
    Enemy wallEnemyNegY;

    @BeforeEach
    public void setup(){
        enemy = new Enemy(0,0,1,1,1);
        wallEnemyX = new Enemy(Game.WINDOWX,0,1,1,1);
        wallEnemyY = new Enemy(0,Game.WINDOWY,1,1,1);
        wallEnemyNegX = new Enemy(0,0,-1,-1,1);
        wallEnemyNegY = new Enemy(0,0,-1,-1,1);

    }


    @Test
    public void tickOnceTest(){
        assertEquals(0, enemy.getXPosition());
        assertEquals(0, enemy.getYPosition());
        assertEquals(1, enemy.getXDir());
        assertEquals(1, enemy.getYDir());
        enemy.tick();
        assertEquals(1, enemy.getXDir());
        assertEquals(1, enemy.getYDir());
        assertEquals(1, enemy.getXPosition());
        assertEquals(1, enemy.getYPosition());

    }

    @Test
    public void tickMultipleTest(){
        assertEquals(0, enemy.getXPosition());
        assertEquals(0, enemy.getYPosition());
        assertEquals(1, enemy.getXDir());
        assertEquals(1, enemy.getYDir());
        enemy.tick();
        enemy.tick();
        assertEquals(1, enemy.getXDir());
        assertEquals(1, enemy.getYDir());
        assertEquals(2, enemy.getXPosition());
        assertEquals(2, enemy.getYPosition());
    }

    @Test
    public void tickCollideXTest() {
        assertEquals(Game.WINDOWX, wallEnemyX.getXPosition());
        assertEquals(0, wallEnemyX.getYPosition());
        assertEquals(1, wallEnemyX.getXDir());
        assertEquals(1, wallEnemyX.getYDir());
        wallEnemyX.tick();
        assertEquals(Game.WINDOWX, wallEnemyX.getXPosition());
        assertEquals(0, wallEnemyX.getYPosition());
        assertEquals(-1, wallEnemyX.getXDir());
        assertEquals(-1, wallEnemyX.getYDir());
    }

    @Test
    public void tickCollideYTest() {
        assertEquals(Game.WINDOWY, wallEnemyY.getYPosition());
        assertEquals(0, wallEnemyY.getXPosition());
        assertEquals(1, wallEnemyY.getXDir());
        assertEquals(1, wallEnemyY.getYDir());
        wallEnemyY.tick();
        assertEquals(Game.WINDOWY, wallEnemyY.getYPosition());
        assertEquals(0, wallEnemyY.getXPosition());
        assertEquals(-1, wallEnemyY.getXDir());
        assertEquals(-1, wallEnemyY.getYDir());
    }

    @Test
    public void tickCollideNegXTest() {
        assertEquals(0, wallEnemyNegX.getXPosition());
        assertEquals(0, wallEnemyNegX.getYPosition());
        assertEquals(-1, wallEnemyNegX.getXDir());
        assertEquals(-1, wallEnemyNegX.getYDir());
        wallEnemyNegX.tick();
        assertEquals(0, wallEnemyNegX.getXPosition());
        assertEquals(0, wallEnemyNegX.getYPosition());
        assertEquals(1, wallEnemyNegX.getXDir());
        assertEquals(1, wallEnemyNegX.getYDir());
    }

    @Test
    public void tickCollideNegYTest() {
        assertEquals(0, wallEnemyNegY.getYPosition());
        assertEquals(0, wallEnemyNegY.getXPosition());
        assertEquals(-1, wallEnemyNegY.getXDir());
        assertEquals(-1, wallEnemyNegY.getYDir());
        wallEnemyNegY.tick();
        assertEquals(0, wallEnemyNegY.getYPosition());
        assertEquals(0, wallEnemyNegY.getXPosition());
        assertEquals(1, wallEnemyNegY.getXDir());
        assertEquals(1, wallEnemyNegY.getYDir());
    }

}
