package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public static final String OVER_MESSAGE = "Game Over!";
    private Game game;

    public GamePanel(Game g) {
        setPreferredSize(new Dimension((int) Game.WINDOWX, (int) Game.WINDOWY));
        setBackground(Color.BLACK);
        this.game = g;
        addMouseListener(new MouseListener(this.game));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
        if (game.getGameOver()) {
            gameOver(g);
        }
    }

    // Draws the game
    // modifies: g
    // effects:  the game is drawn onto the Graphics object g
    private void drawGame(Graphics g) {
        game.draw(g);
    }

    // Draws the "game over" message
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER_MESSAGE, g, fm, Game.WINDOWY / 2);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, double y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (int) ((Game.WINDOWX - width) / 2), (int) y);
    }
}
