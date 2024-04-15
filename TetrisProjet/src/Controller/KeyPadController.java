
package Controller;

import model.Board;
import model.BoardGrand;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPadController extends KeyAdapter {
    private Board board;


    private Board board2;
    private BoardGrand boardGrand;


    public KeyPadController(Board board) {
        this.board = board;
    }

    public KeyPadController(Board board, Board board2) {
        this.board = board;
        this.board2 = board2;
    }

    public KeyPadController(BoardGrand boardGrand) {
        this.boardGrand = boardGrand;
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
            if(board!=null)
                board.handleMovement("Down");
            if(boardGrand!=null){
                boardGrand.handleMovement("Down");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(board!=null)
                board.handleMovement("Left");
            if(boardGrand!=null){
                boardGrand.handleMovement("Left");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(board!=null)
                board.handleMovement("Right");
            if(boardGrand!=null){
                boardGrand.handleMovement("Right");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(board!=null)
                board.handleMovement("Space");
            if(boardGrand!=null){
                boardGrand.handleMovement("Space");
            }
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
        if (e.getKeyCode() == KeyEvent.VK_S && boardGrand != null) {
            boardGrand.handleMovement2("Down");
        }
        if (e.getKeyCode() == KeyEvent.VK_Q && boardGrand != null) {
            boardGrand.handleMovement2("Left");
        }
        if (e.getKeyCode() == KeyEvent.VK_D && boardGrand != null) {
            boardGrand.handleMovement2("Right");
        }
        if (e.getKeyCode() == KeyEvent.VK_Z && boardGrand != null) {
            boardGrand.handleMovement2("Space");
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            board.getPcs().firePropertyChange("addJoueur", null, null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(board!=null)
                board.getPcs().firePropertyChange("Cancel", null, null);
            if(boardGrand!=null)
                boardGrand.getPcs().firePropertyChange("Cancel", null, null);
        }

        if(e.getKeyCode()==KeyEvent.VK_B){
            if(board!=null)
                board.getPcs().firePropertyChange("Pause",null,null);
            if(boardGrand!=null){
                boardGrand.getPcs().firePropertyChange("Pause",null,null);
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_C){
            board.getPcs().firePropertyChange("Coop",null,null);
        }
        if(e.getKeyCode()==KeyEvent.VK_G){
            if(board!=null)
                board.getPcs().firePropertyChange("Grand",null,null);
            }
        }

    }


