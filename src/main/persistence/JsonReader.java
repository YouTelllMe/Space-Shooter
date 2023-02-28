package persistence;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Bullet;
import model.Enemy;
import model.Game;
import org.json.*;

// Represents a reader that reads saved game from JSON data stored in file
// Class was modelled from JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game from JSON object and returns it
    private Game parseGame(JSONObject jsonObject) {
        int time = jsonObject.getInt("time");
        int score = jsonObject.getInt("score");
        boolean gameOver = jsonObject.getBoolean("gameOver");
        JSONObject playerJson = jsonObject.getJSONObject("player");
        JSONArray enemyJson = jsonObject.getJSONArray("enemyList");
        JSONArray friendlyBullets = jsonObject.getJSONArray("friendlyBulletList");
        JSONArray unfriendlyBullets = jsonObject.getJSONArray("unfriendlyBulletList");
        Game game = new Game();
        game.setTime(time);
        game.setScore(score);
        game.pauseGame();
        game.setGameOver(gameOver);
        loadPlayer(game, playerJson);
        loadEnemies(game, enemyJson);
        loadBullets(game, friendlyBullets, unfriendlyBullets);
        return game;
    }

    // MODIFIES: game
    // EFFECTS: parses player data from JSON object and adds it to game
    private void loadPlayer(Game game, JSONObject player) {
        double xposition = player.getDouble("xposition");
        double yposition = player.getDouble("yposition");
        double xdir = player.getDouble("xdir");
        double ydir = player.getDouble("ydir");
        int health = player.getInt("health");
        int damage = player.getInt("damage");
        int speed = player.getInt("speed");
        int projectileNumber = player.getInt("projectileNumber");
        game.getPlayer().setPlayer(xposition,yposition,xdir,ydir,health,damage,speed,projectileNumber);
    }

    // MODIFIES: game
    // EFFECTS: parses JSONArray of list of enemies and adds each enemy to game
    private void loadEnemies(Game game, JSONArray enemyList) {
        for (Object enemy : enemyList) {
            JSONObject nextEnemy = (JSONObject) enemy;
            loadEnemy(game, nextEnemy);
        }
    }

    // MODIFIES: game
    // EFFECTS: parses a JSONObject of an enemy and adds it to the game
    private void loadEnemy(Game game, JSONObject enemy) {
        double xposition = enemy.getDouble("xposition");
        double yposition = enemy.getDouble("yposition");
        double xdir = enemy.getDouble("xdir");
        double ydir = enemy.getDouble("ydir");
        int type = enemy.getInt("type");
        Enemy e = new Enemy(xposition,yposition,xdir,ydir,type);
        game.addEnemy(e);
    }

    // MODIFIES: game
    // EFFECTS: parses JSONArray of the list of friendly and the list of unfriendly bullets
    // and adds each bullet to game
    private void loadBullets(Game game, JSONArray friendlyBullets, JSONArray unfriendlyBullets) {
        for (Object bullet : friendlyBullets) {
            JSONObject nextBullet = (JSONObject) bullet;
            loadBullet(game, nextBullet, true);
        }
        for (Object bullet : unfriendlyBullets) {
            JSONObject nextBullet = (JSONObject) bullet;
            loadBullet(game, nextBullet, false);
        }
    }

    // MODIFIES: game
    // EFFECTS: parses JSONObject of a bullet and adds it to the appropriate bullet list in game
    private void loadBullet(Game game, JSONObject bullet, boolean friendly) {
        double xposition = bullet.getDouble("xposition");
        double yposition = bullet.getDouble("yposition");
        double xdir = bullet.getDouble("xdir");
        double ydir = bullet.getDouble("ydir");
        int damage = bullet.getInt("damage");
        int red = bullet.getInt("colorR");
        int green = bullet.getInt("colorG");
        int blue = bullet.getInt("colorB");
        Bullet b = new Bullet(xposition,yposition,xdir,ydir,damage,new Color(red, green, blue));
        game.addBullet(b,friendly);
    }

}
