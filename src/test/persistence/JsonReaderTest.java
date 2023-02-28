package persistence;

import model.Bullet;
import model.Enemy;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Class was modelled from JsonReaderTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Game game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNewGame() {
        JsonReader reader = new JsonReader("./data/testReaderNewGame.json");
        try {
            Game g = reader.read();
            Player p = g.getPlayer();
            assertEquals(0, g.getTime());
            assertEquals(0, g.getScore());
            assertTrue(g.getGamePaused());
            assertFalse(g.getGameOver());
            assertEquals(0, g.getEntityList().size());
            assertEquals(0,g.getFriendlyBulletList().size());
            assertEquals(0,g.getUnfriendlyBulletList().size());
            checkPlayer(p,0,0,0,5, 1,10,3,2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGame() {
        JsonReader reader = new JsonReader("./data/testReaderGame.json");
        try {
            Game g = reader.read();
            assertEquals(1,g.getEntityList().size());
            assertEquals(1,g.getFriendlyBulletList().size());
            assertEquals(2,g.getUnfriendlyBulletList().size());
            assertEquals(0, g.getTime());
            assertEquals(0, g.getScore());
            assertTrue(g.getGamePaused());
            assertFalse(g.getGameOver());
            Player p = g.getPlayer();
            Enemy e = g.getEntityList().get(0);
            Bullet fb1 = g.getFriendlyBulletList().get(0);
            Bullet ufb1 = g.getUnfriendlyBulletList().get(0);
            Bullet ufb2 = g.getUnfriendlyBulletList().get(1);
            checkPlayer(p,3,6,5,5, 1,10,3,2);
            checkBullet(fb1,29,29,-1,-1,1,Color.BLUE);
            checkBullet(ufb1,0,0,-1,-1,1,Color.RED);
            checkBullet(ufb2,29,29,-1,-1,1, Color.PINK);
            checkEnemy(e,1,1,1,1,3);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}