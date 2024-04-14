package View;

import model.Board;
import model.BoardGrand;
import model.bricks.Brick;
import model.bricks.BrickManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Cette classe est utiliser pour afficher les prochaines pièces.
 */
public class NextPieceCanvaJ2 extends JPanel implements PropertyChangeListener {
    private static final int BLOCK_SIZE = 30;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Board board;

    private BoardGrand boardGrand;

    private Brick nextBrick;
    private BrickManager brickManager;


    /**
     * Constructeur de la classe NextPieceCanva
     * @param board
     * @param brickManager
     */


    public NextPieceCanvaJ2(BoardGrand board,BrickManager brickManager) {
        this.boardGrand = board;
        board.addPropertyChangeListener(this);
        this.setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        this.nextBrick = brickManager.getNextBrick();
    }

    /**
     * Cette méthode permet de mettre à jour la prochaine pièce
     * @param e
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("NextPiece2")) {
            this.nextBrick = (Brick) e.getNewValue();
            repaint();
        }
    }

    /**
     * Cette méthode permet de dessiner la prochaine pièce
     * @param g the <code>Graphics</code> object to protect
     */
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

}