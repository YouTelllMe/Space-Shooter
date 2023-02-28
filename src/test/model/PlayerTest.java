package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Player wallUser;
    PowerUp powerUp;
    double speed;
    double windowSize;

    @BeforeEach
    public void setup(){
        this.player = new Player(Game.WINDOWX / 2, Game.WINDOWY / 2);
        this.wallUser = new Player(Game.WINDOWX,0);
        this.speed = player.getSpeed();
        this.powerUp = new PowerUp(true,true,true,true);
    }

    @Test
    public void moveOnceTest(){
        assertEquals(Game.WINDOWX / 2, player.getXPosition());
        assertEquals(Game.WINDOWY / 2, player.getYPosition());
        assertTrue(player.move(true,false,true,false));
        assertEquals(-1 * this.speed, player.getXPosition());
        assertEquals(this.speed, player.getYPosition());
    }

    @Test
    public void moveUpMultipleTest(){
        assertEquals(0, player.getXPosition());
        assertEquals(0, player.getYPosition());
        assertTrue(player.move(false,true,false,true));
        assertTrue(player.move(false,true,false,true));
        assertEquals(2 * this.speed, player.getXPosition());
        assertEquals(-2 * this.speed, player.getYPosition());
    }

    @Test
    public void moveNullTest(){
        assertEquals(0, player.getXPosition());
        assertEquals(0, player.getYPosition());
        assertTrue(player.move(true,true,true,true));
        assertEquals(0, player.getXPosition());
        assertEquals(0, player.getYPosition());
    }

    @Test
    public void moveIntoWallTest(){
        assertEquals(windowSize, wallUser.getXPosition());
        assertEquals(0, wallUser.getYPosition());
        assertFalse(wallUser.move(false,false,false,true));
        assertEquals(windowSize, wallUser.getXPosition());
        assertEquals(0, wallUser.getYPosition());
    }

    @Test
    public void powerUpTest(){
        assertEquals(10,player.getSpeed());
        assertEquals(1,player.getDamage());
        assertEquals(10,player.getHealth());
        assertEquals(2,player.getProjectileNumber());
        player.powerUp(powerUp);
        assertEquals(4,player.getSpeed());
        assertEquals(2,player.getDamage());
        assertEquals(15,player.getHealth());
        assertEquals(3,player.getProjectileNumber());

    }

    @Test
    public void setPlayerTest(){
        player.setPlayer(1,1,1,1,1,1,1,1);
        assertEquals(1,player.getXPosition());
        assertEquals(1,player.getYPosition());
        assertEquals(1,player.getXDir());
        assertEquals(1,player.getYDir());
        assertEquals(1,player.getHealth());
        assertEquals(1,player.getDamage());
        assertEquals(1,player.getSpeed());
        assertEquals(1,player.getProjectileNumber());
    }

}