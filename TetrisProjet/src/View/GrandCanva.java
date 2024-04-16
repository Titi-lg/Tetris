package View;

import model.Board;
import model.BoardGrand;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GrandCanva extends JPanel implements PropertyChangeListener {
    private static final int BLOCK_SIZE = 30;
    private static final int ROWS = 25;
    private static final int COLS = 20;
    private int[][] grid;
    private BoardGrand board;

    public GrandCanva(BoardGrand board) {
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
                switch (grid[i][j]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
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
                }
                g.fillRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("NewMove")) {
            updateGrid((int[][]) e.getNewValue());
        }
    }
    public void updateGrid(int[][] newGrid) {
        this.grid = newGrid;
        repaint();
    }
}
