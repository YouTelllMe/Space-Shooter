package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// A Game instance storing all entities, objects, their getters, and methods for controlling the game
public class Game implements Writable {
    public static final double WINDOWX = 1600;
    public static final double WINDOWY = 900;
    private boolean gamePaused;
    private boolean gameOver;
    private int score;
    private int time;
    private List<Bullet> friendlyBulletList;
    private List<Bullet> unfriendlyBulletList;

    private List<Enemy> enemyList;
    private Player player;


    //EFFECTS: create a game instance
    public Game() {
        this.gamePaused = false;
        this.gameOver = false;
        this.score = 0;
        this.time = 0;
        this.friendlyBulletList = new ArrayList<>();
        this.unfriendlyBulletList = new ArrayList<>();
        this.enemyList = new ArrayList<>();
        this.player = new Player(WINDOWX / 2, WINDOWY / 2);
        EventLog.getInstance().logEvent(new Event("Game Started."));

    }


    //MODIFIES: this
    //EFFECTS: pauses the game instance if unpaused
    public void pauseGame() {
        if (!this.gamePaused) {
            this.gamePaused = true;
            EventLog.getInstance().logEvent(new Event("Game Paused."));
        }
    }

    //MODIFIES: this
    //EFFECTS: unpauses the game instance if paused
    public void unpauseGame() {
        if (this.gamePaused) {
            this.gamePaused = false;
            EventLog.getInstance().logEvent(new Event("Game Unpaused."));
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a random enemy entity with a random position, direction, and type
    public void generateEnemy() {
        Random rand = new Random();
        int type = rand.nextInt(6);
        int randomX = rand.nextInt((int) WINDOWX);
        int randomY = rand.nextInt((int) WINDOWY);
        int randomXDir = rand.nextInt(6);
        int randomYDir = rand.nextInt(4);

        Enemy e = makeEnemy(randomX, randomY, randomXDir, randomYDir, type);
        addEnemy(e);
        EventLog.getInstance().logEvent(new Event("Type " + Integer.toString(type) + "Enemy Spawned"));
    }

    // MODIFIES: this
    // EFFECTS: create an enemy entity with a specified position, direction, and type
    public Enemy makeEnemy(int randomX, int randomY, int randomXDir, int randomYDir, int type) {
        if (type == 0) {
            return new LargeEnemy(randomX, randomY, randomXDir, randomYDir);
        } else if (type == 1) {
            return new MediumEnemy(randomX, randomY,randomXDir,randomYDir);
        } else {
            return new SmallEnemy(randomX, randomY,randomXDir,randomYDir);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an enemy to list of entities
    public void addEnemy(Enemy e) {
        this.enemyList.add(e);
    }

    // MODIFIES: this
    // EFFECTS: adds the given bullet to the proper bulletList
    public void addBullet(Bullet bullet, boolean friendly) {
        if (friendly) {
            EventLog.getInstance().logEvent(new Event("Friendly Bullet Added."));
            this.friendlyBulletList.add(bullet);
        } else {
            EventLog.getInstance().logEvent(new Event("Unfriendly Bullet Added."));
            this.unfriendlyBulletList.add(bullet);
        }
    }

    // MODIFIES: this
    // EFFECTS: ticks the game forward.
    public boolean gameTick() {
        if (!this.gamePaused) {
            for (Enemy e : enemyList) {
                if (!e.checkWall(e.getXPosition(), e.getYPosition())) {
                    e.tick();
                }
            }
            checkFriendlyBullet();
            checkUnFriendlyBullet();
            this.time += 1;
        }

        if (time % 1000 == 0) {
            generateEnemy();
            for (Enemy e: enemyList) {
                e.shoot(this);
            }
        }

        if (time % 15000 == 0) {
            player.powerUp(PowerUp.generatePowerUp());
        }
        return this.getGameOver();
    }


    // EFFECTS: returns true if the bullet coordinates are located within the hitbox of an entity
    public boolean checkBulletCollision(Entity e, Bullet b) {
        double bulletX = b.getXPosition();
        double bulletY = b.getYPosition();
        double entityX = e.getXPosition();
        double entityY = e.getYPosition();
        double halfHitbox = e.getHitbox() / 2;
        double entityXMax = entityX + halfHitbox;
        double entityXMin = entityX - halfHitbox;
        double entityYMax = entityY + halfHitbox;
        double entityYMin = entityY - halfHitbox;

        return (bulletX <= entityXMax
                && bulletX >= entityXMin
                && bulletY <= entityYMax
                && bulletY >= entityYMin);
    }

    // MODIFIES: this
    // EFFECTS: Check whether the user's bullets hit the enemy. If kills, remove enemy from list of enemies. If
    // doesn't kill, apply the damage. Removes the bullet if it goes out of the window and if it damages or kills
    // an enemy.
    public void checkFriendlyBullet() {
        List<Bullet> removeBullet = new ArrayList<>();
        List<Enemy> removeEnemy = new ArrayList<>();
        for (Bullet b: friendlyBulletList) {
            if (b.tick()) {
                for (Enemy e: enemyList) {
                    if (checkBulletCollision(e, b) && e.getHealth() > 0) {
                        if (e.getHealth() - b.getDamage() <= 0) {
                            removeEnemy.add(e);
                        }
                        removeBullet.add(b);
                        e.setHealth(e.getHealth() - b.getDamage());
                        EventLog.getInstance().logEvent(new Event("Enemy Shot: Bullet Removed."));
                        break;
                    }
                }
            } else {
                removeBullet.add(b);
            }
        }
        clearBullets(removeBullet, true);
        clearEnemy(removeEnemy);
    }

    // MODIFIES: this
    // EFFECTS: Check whether the enemy's bullets hit the user. If kills, game over procedure occurs. If
    // doesn't kill, apply the damage. Removes the bullet if it goes out of the window and if it damages or kills
    // the user.
    public void checkUnFriendlyBullet() {
        List<Bullet> removeBullet = new ArrayList<>();
        for (Bullet b: unfriendlyBulletList) {
            if (b.tick()) {
                if (checkBulletCollision(player, b)) {
                    if (player.getHealth() - b.getDamage() <= 0) {
                        EventLog.getInstance().logEvent(new Event("Game Over."));
                        this.gameOver = true;
                    }
                    removeBullet.add(b);
                    player.setHealth(player.getHealth() - b.getDamage());
                    if (!this.gameOver) {
                        EventLog.getInstance().logEvent(new Event("Player Shot: Bullet Removed."));
                    }
                }
            } else {
                removeBullet.add(b);
            }
        }
        clearBullets(removeBullet, false);
    }

    // MODIFIES: this
    // EFFECTS: clear the list of bullets to be removed from the list of bullets in the game. If friendly is true,
    // clear bullets from list of friendly bullets, if false, from unfriendly bullets.
    public void clearBullets(List<Bullet> removeBullet, boolean friendly) {
        if (friendly) {
            for (Bullet b: removeBullet) {
                this.friendlyBulletList.remove(b);
            }
        } else {
            for (Bullet b: removeBullet) {
                this.unfriendlyBulletList.remove(b);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: clear the killed enemies from the game and add the enemy type * 100 to the score
    public void clearEnemy(List<Enemy> removeEnemy) {
        for (Enemy e: removeEnemy) {
            this.score += e.getType() * 100;
            this.enemyList.remove(e);
        }
    }

    public void draw(Graphics g) {
        player.draw(g);
        for (Bullet b: friendlyBulletList) {
            b.draw(g);
        }
        for (Bullet b: unfriendlyBulletList) {
            b.draw(g);
        }
        for (Enemy e: enemyList) {
            e.draw(g);
        }
    }

    public void leftClick(int x, int y) {
        player.shoot(x,y,this);
    }

    // EFFECTS: returns entitylist
    public List<Enemy> getEntityList() {
        return this.enemyList;
    }

    public void setEntityList(List<Enemy> loe) {
        this.enemyList = loe;
    }

    // EFFECTS: returns friendly bulletlist
    public List<Bullet> getFriendlyBulletList() {
        return this.friendlyBulletList;
    }

    public void setFriendlyBulletList(List<Bullet> lob) {
        this.friendlyBulletList = lob;
    }

    // EFFECTS: returns unfriendly bulletlist
    public List<Bullet> getUnfriendlyBulletList() {
        return this.unfriendlyBulletList;
    }

    public void setUnfriendlyBulletList(List<Bullet> lob) {
        this.unfriendlyBulletList = lob;
    }

    // EFFECTS: returns user
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    // EFFECTS: returns gamePaused
    public boolean getGamePaused() {
        return this.gamePaused;
    }

    // EFFECTS: returns gameover
    public boolean getGameOver() {
        return this.gameOver;
    }

    // EFFECTS: returns score
    public int getScore() {
        return this.score;
    }

    // EFFECTS: returns time

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gameOver", gameOver);
        json.put("score", score);
        json.put("time", time);
        json.put("friendlyBulletList", bulletListToJson(friendlyBulletList));
        json.put("unfriendlyBulletList", bulletListToJson(unfriendlyBulletList));
        json.put("enemyList", enemyListToJson(enemyList));
        json.put("player",player.toJson());
        return json;
    }

    public JSONArray bulletListToJson(List<Bullet> listOfObject) {
        JSONArray jsonArray = new JSONArray();
        for (Object o: listOfObject) {
            jsonArray.put(o.toJson());
        }
        return jsonArray;
    }

    public JSONArray enemyListToJson(List<Enemy> listofEntity) {
        JSONArray jsonArray = new JSONArray();
        for (Entity e: listofEntity) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

}
