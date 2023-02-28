package persistence;

import model.Bullet;
import model.Enemy;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Class was modelled from JsonWriterTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            Game g = new Game();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNewGame() {
        try {
            Game g = new Game();
            JsonWriter writer = new JsonWriter("./data/testWriterNewGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewGame.json");
            g = reader.read();
            Player p = g.getPlayer();

            assertEquals(0, g.getTime());
            assertEquals(0, g.getScore());
            assertTrue(g.getGamePaused());
            assertFalse(g.getGameOver());
            assertEquals(0, g.getEntityList().size());
            assertEquals(0,g.getFriendlyBulletList().size());
            assertEquals(0,g.getUnfriendlyBulletList().size());
            checkPlayer(p,500,500,0,5, 1,10,10,2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteGame() {
        try {
            Game g = new Game();
            Player p = g.getPlayer();
            g.addBullet(new Bullet(30,30,-1,-1,1, Color.BLUE), true);
            g.addBullet(new Bullet(1,1,-1,-1,1,Color.RED), false);
            g.addBullet(new Bullet(30,30,-1,-1,1,Color.PINK), false);
            g.addEnemy(new Enemy(0,0,1,1,3));
            p.move(true,false,false,false);
            p.move(true,false,false,false);
            p.move(false,false,false,true);
            g.gameTick();
            g.pauseGame();
            g.gameTick();


            JsonWriter writer = new JsonWriter("./data/testWriterGame.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGame.json");
            g = reader.read();
            assertEquals(1,g.getEntityList().size());
            assertEquals(1,g.getFriendlyBulletList().size());
            assertEquals(2,g.getUnfriendlyBulletList().size());
            assertEquals(1, g.getTime());
            assertEquals(0, g.getScore());
            assertTrue(g.getGamePaused());
            assertFalse(g.getGameOver());
            p = g.getPlayer();
            Enemy e = g.getEntityList().get(0);
            Bullet fb1 = g.getFriendlyBulletList().get(0);
            Bullet ufb1 = g.getUnfriendlyBulletList().get(0);
            Bullet ufb2 = g.getUnfriendlyBulletList().get(1);
            checkPlayer(p,510,480,5,5, 1,10,3,2);
            checkBullet(fb1,29,29,-1,-1,1,Color.BLUE);
            checkBullet(ufb1,0,0,-1,-1,1,Color.RED);
            checkBullet(ufb2,29,29,-1,-1,1,Color.PINK);
            checkEnemy(e,1,1,1,1,3);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}