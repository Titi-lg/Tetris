
package Controller;

import model.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DownController extends KeyAdapter {
    private Board board;

    public DownController(Board board) {
        this.board = board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            board.handleMovement("Down");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            board.handleMovement("Left");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            board.handleMovement("Right");
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            board.handleMovement("Space");
        }
    }
}

