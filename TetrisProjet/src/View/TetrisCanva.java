package View;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisCanva extends JPanel implements PropertyChangeListener {
    private static final int BLOCK_SIZE = 30;
    private static final int ROWS = 20;
    private static final int COLS = 10;
    private int[][] grid;
    private Board board;

    public TetrisCanva(Board board) {
        this.board = board;
        this.grid = board.getGrid();
        board.addPropertyChangeListener(this);
        this.setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        System.out.println("propertyChange");
        if (e.getPropertyName().equals("NewMove")) {
            updateGrid((int[][]) e.getNewValue());
        }
    }
    public void updateGrid(int[][] newGrid) {
        this.grid = newGrid;
        repaint();
    }
}
