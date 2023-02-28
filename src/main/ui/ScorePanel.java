package ui;

import java.awt.*;

import javax.swing.*;

import model.Game;

/*
 * Represents the panel in which the scoreboard is displayed.
 */

public class ScorePanel extends JPanel {
    private static final String SCORE_TEXT = "Score: ";
    private static final String TIME_TXT = "Time: ";
    private static final int LBL_WIDTH = 400;
    public static final int LBL_HEIGHT = 30;
    private Game game;
    private JLabel scoreLbl;
    private JLabel timeLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g, JButton pauseB, JButton saveB, JButton loadB, JButton restartB) {
        game = g;
        setBackground(new Color(15, 15, 15));
        scoreLbl = makeLabel(SCORE_TEXT + game.getScore());
        scoreLbl.setHorizontalAlignment(JLabel.RIGHT);
        timeLbl = makeLabel(TIME_TXT + game.getTime());
        add(timeLbl);
        add(pauseB);
        add(saveB);
        add(loadB);
        add(restartB);
        add(scoreLbl);
    }

    public JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        return label;
    }


    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        scoreLbl.setText(SCORE_TEXT + game.getScore());
        timeLbl.setText(TIME_TXT + game.getTime() / 1000 * GUI.INTERVAL);
        repaint();
    }

}