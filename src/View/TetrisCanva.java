package View;

import Model.Tetris;

import javax.swing.*;
import java.awt.*;

    public class TetrisCanva extends JPanel {
        private static final int GRID_WIDTH = 10;
        private static final int GRID_HEIGHT = 20;
        private static final int CELL_SIZE = Math.min(TetrisFrame.largeur, TetrisFrame.hauteur) / Math.max(GRID_WIDTH, GRID_HEIGHT);
        private static final int GRID_PIXEL_WIDTH = CELL_SIZE * GRID_WIDTH;
        private static final int GRID_PIXEL_HEIGHT = CELL_SIZE * GRID_HEIGHT;
        private Tetris tetris;

        public TetrisCanva(Tetris tetris) {
            this.tetris = tetris;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGrid(g);
            System.out.println("test2");
        }

        private void drawGrid(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect((TetrisFrame.largeur - GRID_PIXEL_WIDTH) / 2, (TetrisFrame.hauteur - GRID_PIXEL_HEIGHT) / 2, GRID_PIXEL_WIDTH, GRID_PIXEL_HEIGHT);
            g.setColor(Color.BLACK);
            for (int x = 0; x < GRID_WIDTH; x++) {
                for (int y = 0; y < GRID_HEIGHT; y++) {
                    drawCell(g, x, y);
                }
            }
        }

        private void drawCell(Graphics g, int x, int y) {
            int cellX = (TetrisFrame.largeur - GRID_PIXEL_WIDTH) / 2 + x * CELL_SIZE;
            int cellY = (TetrisFrame.hauteur - GRID_PIXEL_HEIGHT) / 2 + y * CELL_SIZE;
            g.setColor(Color.BLACK);
            g.drawRect(cellX, cellY, CELL_SIZE, CELL_SIZE);
            if (tetris.getGridPlay().getNumber(x, y) == 1) {
                System.out.println("test3 "+x+" "+y);
                g.setColor(Color.RED);
                g.fillRect(cellX, cellY, CELL_SIZE, CELL_SIZE);
            }
        }
    }