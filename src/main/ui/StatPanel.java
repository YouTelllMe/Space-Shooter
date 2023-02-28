package ui;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the panel in which the player stats are displayed.
 */
public class StatPanel extends JPanel {
    private static final int ICON_SIZE = 20;
    private static final int LABEL_HEIGHT = 35;
    private static final int LABEL_WIDTH = 70;
    private Game game;
    JLabel healthLabel;
    JLabel damageLabel;
    JLabel speedLabel;
    JLabel projNumLabel;

    public StatPanel(Game g) {
        setBackground(Color.BLACK);
        this.game = g;
        Player player = g.getPlayer();
        JPanel statPanel = new JPanel();
        statPanel.setPreferredSize(new Dimension(LABEL_WIDTH, (int) Game.WINDOWX));
        statPanel.setBackground(new Color(15, 15, 15));
        this.healthLabel = statLabel("media/health.png", Integer.toString(player.getHealth()));
        this.damageLabel = statLabel("media/damage.png",Integer.toString(player.getDamage()));
        this.speedLabel = statLabel("media/speed.png",Integer.toString((int) player.getSpeed()));
        this.projNumLabel = statLabel("media/projnum.png",Integer.toString(player.getProjectileNumber()));
        statPanel.add(healthLabel);
        statPanel.add(Box.createVerticalStrut(30));
        statPanel.add(damageLabel);
        statPanel.add(Box.createVerticalStrut(30));
        statPanel.add(speedLabel);
        statPanel.add(Box.createVerticalStrut(30));
        statPanel.add(projNumLabel);
        add(statPanel, BorderLayout.CENTER);

    }

    public JLabel statLabel(String ipath, String stat) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ipath));
        Image resized = icon.getImage().getScaledInstance(ICON_SIZE,ICON_SIZE, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(stat,
                new ImageIcon(resized), JLabel.LEFT);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(15,15,15));
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        healthLabel.setText(Integer.toString(game.getPlayer().getHealth()));
        damageLabel.setText(Integer.toString(game.getPlayer().getDamage()));
        speedLabel.setText(Integer.toString((int) game.getPlayer().getSpeed()));
        projNumLabel.setText(Integer.toString(game.getPlayer().getProjectileNumber()));
        repaint();
    }
}
