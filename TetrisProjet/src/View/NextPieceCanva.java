package View;

import model.Board;
import model.bricks.Brick;
import model.bricks.BrickManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NextPieceCanva extends JPanel implements PropertyChangeListener {
    private static final int BLOCK_SIZE = 30;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private int[][] grid;
    private Board board;

    private Brick nextBrick;
    private BrickManager brickManager;

    public NextPieceCanva(Board board, BrickManager brickManager) {
        this.board = board;
        this.brickManager = brickManager;
        this.grid = board.getGrid();
        board.addPropertyChangeListener(this);
        this.setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        this.nextBrick = brickManager.getNextBrick();
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("NextPiece")) {
            this.nextBrick = (Brick) e.getNewValue();
            repaint();
        }
    }


    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (nextBrick != null) {
        int[][] shape = nextBrick.getShapeMatrix(nextBrick.getNumberOfRotation());
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    switch (shape[i][j]) {
                        case 1:
                            g.setColor(Color.CYAN);
                            break;
                        case 2:
                            g.setColor(Color.BLUE);
                            break;
                        case 3:
                            g.setColor(Color.ORANGE);
                            break;
                        case 4:
                            g.setColor(Color.YELLOW);
                            break;
                        case 5:
                            g.setColor(Color.GREEN);
                            break;
                        case 6:
                            g.setColor(Color.MAGENTA);
                            break;
                        case 7:
                            g.setColor(Color.RED);
                            break;
                        default:
                            g.setColor(Color.WHITE);
                            break;
                    }
                    g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }
}

    public void updateGrid(int[][] newGrid) {
        this.grid = newGrid;
        repaint();
    }
}