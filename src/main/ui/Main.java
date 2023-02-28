package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String JSON_STORE = "./data/game.json";
    private static Game game;
    private static Player player;
    private static Scanner scan;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;


    public static void main(String[] args) {
        scan = new Scanner(System.in);
        game = new Game();
        player = game.getPlayer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        while (true) {
            printInstructions();
            String text = scan.nextLine();
            if (!game.getGamePaused()) {
                if (!printMenu(text)) {
                    break;
                }
            } else {
                System.out.println("Gamepaused...");
                System.out.println("'unpause' to unpause");
                if (text.equals("unpause")) {
                    game.unpauseGame();
                }
            }
        }
    }

    public static void inputAction() {
        inputActionInstructions();
        String text = scan.nextLine();
        if (text.equals("movedown")) {
            player.move(false,true,false,false);
        } else if (text.equals("moveup")) {
            player.move(true, false, false, false);
        } else if (text.equals("moveright")) {
            player.move(false, false, false, true);
        } else if (text.equals("moveleft")) {
            player.move(false, false, true, false);
        } else if (text.equals("shoot")) {
            game.addBullet(player.shoot(player.getXDir(), player.getYDir()), true);
        } else if (text.equals("powerup")) {
            PowerUp p = PowerUp.generatePowerUp();
            player.powerUp(p);
            powerUpInfo(p);
        }
    }

    public static void inputGame() {
        inputGameInstructions();
        String text = scan.nextLine();
        if (text.equals("restart")) {
            game = new Game();
            player = game.getPlayer();
        } else if (text.equals("tick")) {
            boolean gameOver = game.gameTick();
            if (gameOver) {
                gameOverInfo();
            }
        } else if (text.equals("pause")) {
            game.pauseGame();
        } else if (text.equals("newenemy")) {
            game.generateEnemy();
        } else if (text.equals("testenemy")) {
            Enemy e = game.makeEnemy(0, 0, 1, 1, 2);
            game.addEnemy(e);
        }
    }

    public static void inputBoard() {
        playerInfo();
        System.out.println("\n");
        for (Enemy e: game.getEntityList()) {
            enemyInfo(e);
            System.out.println("\n");
        }
        for (Bullet b: game.getFriendlyBulletList()) {
            bulletInfo(b);
            System.out.println("\n");
        }
        for (Bullet b: game.getUnfriendlyBulletList()) {
            bulletInfo(b);
            System.out.println("\n");
        }
    }

    public static boolean printMenu(String text) {
        if (text.equals("action")) {
            inputAction();
        } else if (text.equals("game")) {
            inputGame();
        } else if (text.equals("board")) {
            inputBoard();
        } else if (text.equals("quit")) {
            return false;
        } else if (text.equals("save")) {
            saveGame();
        } else if (text.equals("load")) {
            loadGame();
        }
        return true;
    }

    public static void inputActionInstructions() {
        System.out.println("=============================================");
        System.out.println("'moveup' to moveup");
        System.out.println("'movedown' to movedown");
        System.out.println("'moveleft' to moveleft");
        System.out.println("'moveright' to moveright");
        System.out.println("'shoot' to shoot a bullet");
        System.out.println("'powerup' to receive a powerup");
        System.out.println("'back' to go back");
        System.out.println("=============================================");
    }

    public static void inputGameInstructions() {
        System.out.println("=============================================");
        System.out.println("'restart' to restart");
        System.out.println("'tick' to tick the game forward");
        System.out.println("'pause' to pause the game");
        System.out.println("'unpause' to pause the game");
        System.out.println("'newenemy' to generate a new enemy");
        System.out.println("'testenemy' to generate a new enemy");
        System.out.println("'back' to go back");
        System.out.println("=============================================");
    }


    //EFFECTS: prints out console action instructions
    public static void printInstructions() {
        System.out.println("=============================================");
        System.out.println("'action' to see a list of player actions");
        System.out.println("'game' to see a list of game actions");
        System.out.println("'board' to get information of all current board elements");
        System.out.println("'save' to save current game");
        System.out.println("'load' to load previous save");
        System.out.println("'quit' to quit");
        System.out.println("=============================================");
    }

    // EFFECTS: print out useful user information
    public static void playerInfo() {
        System.out.println("User is at " + "(" + player.getXPosition() + "," + player.getYPosition() + ")");
        System.out.println("User direction = " + "(" + player.getXDir() + "," + player.getYDir() + ")");
        System.out.println("User health = " + player.getHealth());
        System.out.println("User speed = " + player.getSpeed());
        System.out.println("User damage = " + player.getDamage());
    }

    // EFFECTS: print out enemy information in readable format
    public static void enemyInfo(Enemy e) {
        System.out.println("Enemy is at " + "(" + e.getXPosition() + "," + e.getYPosition() + ")");
        System.out.println("Enemy health = " + e.getHealth());
        System.out.println("Enemy speed = " + e.getSpeed());
        System.out.println("Enemy damage = " + e.getDamage());
        System.out.println("Enemy direction = " + "(" + e.getXDir() + "," + e.getYDir() + ")");
    }

    // EFFECTS: Prints out user information in readable format.
    public static void bulletInfo(Bullet b) {
        System.out.println("Bullet is at " + "(" + b.getXPosition() + "," + b.getYPosition() + ")");
        System.out.println("Bullet speed = " + b.getSpeed());
        System.out.println("Bullet damage = " + b.getDamage());
        System.out.println("Bullet direction = " + "(" + b.getXDir() + "," + b.getYDir() + ")");
    }

    // EFFECTS: print out powerup information in readable format
    public static void powerUpInfo(PowerUp p) {
        System.out.println("Your PowerUp has:");
        System.out.println("Speedboost = " + p.getSpeedBoost());
        System.out.println("Damageboost = " + p.getDamageBoost());
        System.out.println("Healthboost = " + p.getHealthBoost());
        System.out.println("ProjectileNumberboost = " + p.getProjectileNumberBoost());
    }

    // EFFECTS: prints out gameover prompt
    public static void gameOverInfo() {
        System.out.println("Game Over!");
        System.out.println("Your score was: " + game.getScore());
        System.out.println("Your survived for: " + game.getTime() + " seconds");
        System.out.println("'restart' to restart");
        System.out.println("'quit' to quit");
    }


    // EFFECTS: saves the workroom to file
    public static void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved game to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns the loaded game from file at destination
    public static void loadGame() {
        try {
            game = jsonReader.read();
            player = game.getPlayer();
            System.out.println("Loaded game from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
