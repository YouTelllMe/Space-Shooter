package ui;

import model.Game;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

    private Game game;

    public MouseListener(Game game) {
        this.game = game;
    }


    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!game.getGamePaused() && !game.getGameOver()) {
            int x = (int) e.getPoint().getX();
            int y = (int) e.getPoint().getY();
            game.leftClick(x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
