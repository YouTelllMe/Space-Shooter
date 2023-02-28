package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {
    public static final int INTERVAL = 1;
    private Game game;
    private GamePanel gp;
    private ScorePanel sp;
    private StatPanel stp;
    private Timer timer;

    private JButton pauseB;
    private JButton saveB;
    private JButton loadB;
    private JButton restartB;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static ImageIcon pause;
    private static ImageIcon play;
    private static final String JSON_STORE = "./data/game.json";
    boolean left;
    boolean right;
    boolean up;
    boolean down;


    public GUI() {
        super("Space Shooter 2022");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        pause = new ImageIcon(getButtonIcon("media/pause.png"));
        play = new ImageIcon(getButtonIcon("media/play.png"));
        game = new Game();
        gp = new GamePanel(game);
        stp = new StatPanel(game);
        add(gp, BorderLayout.CENTER);
        add(stp, BorderLayout.WEST);
        getButtons();
        sp = new ScorePanel(game, pauseB, saveB, loadB, restartB);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter());
        addTimer();
        timer.start();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.gameTick();
                gp.repaint();
                sp.update();
                stp.update();
                if (!game.getGamePaused() && !game.getGameOver()) {
                    game.getPlayer().move(up,down,left,right);
                }
            }
        });
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = true;
            }
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pauseB) {
                if (game.getGamePaused()) {
                    pauseB.setIcon(pause);
                    game.unpauseGame();
                } else {
                    pauseB.setIcon(play);
                    game.pauseGame();
                }
            } else if (e.getSource() == restartB) {
                resetGame();
            } else if (e.getSource() == saveB) {
                saveGame();
            } else if (e.getSource() == loadB) {
                loadGame();
            }
            requestFocus();
        }
    }


    public Image getButtonIcon(String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage().getScaledInstance(ScorePanel.LBL_HEIGHT,
                ScorePanel.LBL_HEIGHT, Image.SCALE_SMOOTH);
    }

    public void getButtons() {
        saveB = makeButton(new ImageIcon(getButtonIcon("media/save.png")));
        pauseB = makeButton(new ImageIcon(getButtonIcon("media/pause.png")));
        loadB = makeButton(new ImageIcon(getButtonIcon("media/load.png")));
        restartB = makeButton(new ImageIcon(getButtonIcon("media/reload.png")));
    }

    public JButton makeButton(ImageIcon ico) {
        JButton button = new JButton();
        button.setIcon(ico);
        button.addActionListener(new ButtonHandler());
        button.setBackground(Color.BLACK);
        return button;
    }

    // EFFECTS: saves the workroom to file
    public void saveGame() {
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
    public void loadGame() {
        try {
            Game oldGame = jsonReader.read();
            Player player = oldGame.getPlayer();
            game.getPlayer().setPlayer(Game.WINDOWX / 2, Game.WINDOWY / 2, 0, 0,
                    player.getHealth(), player.getDamage(), player.getSpeed(), player.getProjectileNumber());
            game.setTime(oldGame.getTime());
            game.setUnfriendlyBulletList(oldGame.getUnfriendlyBulletList());
            game.setFriendlyBulletList(oldGame.getFriendlyBulletList());
            game.setGameOver(oldGame.getGameOver());
            game.setScore(oldGame.getScore());
            game.setEntityList(oldGame.getEntityList());
            game.pauseGame();
            pauseB.setIcon(play);
            System.out.println("Loaded game from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void resetGame() {
        game.getPlayer().setPlayer(Game.WINDOWX / 2, Game.WINDOWY / 2, 0, 0,
                10, 1, 1, 2);
        game.setTime(0);
        game.setUnfriendlyBulletList(new ArrayList<Bullet>());
        game.setFriendlyBulletList(new ArrayList<Bullet>());
        game.setGameOver(false);
        game.setScore(0);
        game.setEntityList(new ArrayList<Enemy>());
        game.unpauseGame();
        pauseB.setIcon(pause);
    }

    // Play the game
    public static void main(String[] args) {
        new GUI();
    }

}
