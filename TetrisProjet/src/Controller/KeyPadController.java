
package Controller;

import model.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPadController extends KeyAdapter {
    private Board board;

    private Board board2;

    public KeyPadController(Board board) {
        this.board = board;
    }

    public KeyPadController(Board board, Board board2) {
        this.board = board;
        this.board2 = board2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard2() {
        return board2;
    }

    public void setBoard2(Board board2) {
        this.board2 = board2;
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
        if (e.getKeyCode() == KeyEvent.VK_S && board2 != null) {
            board2.handleMovement("Down");
        }
        if (e.getKeyCode() == KeyEvent.VK_Q && board2 != null) {
            board2.handleMovement("Left");
        }
        if (e.getKeyCode() == KeyEvent.VK_D && board2 != null) {
            board2.handleMovement("Right");
        }
        if (e.getKeyCode() == KeyEvent.VK_Z && board2 != null) {
            board2.handleMovement("Space");
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            board.getPcs().firePropertyChange("addJoueur", null, null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            board.getPcs().firePropertyChange("Cancel", null, null);
        }
    }
}

