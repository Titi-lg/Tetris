package model;

import model.bricks.Brick;
import model.bricks.BrickManager;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Board implements Runnable{
    private int[][] boardMatrix;
    private BrickManager brickManager;
    private final int rows;
    private final int cols;
    private int score = 0;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private Point point;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boardMatrix = new int[rows][cols];
        brickManager = new BrickManager();
    }
    public void initialisation(){
        createNewBrick();
        mergeBrickToBackground();
        displayBoard();
    }
    public boolean createNewBrick() {
        Brick currentBrick = brickManager.getBrick();
        point = new Point(4, 0);
        return TabOperation.intersect(boardMatrix, currentBrick.getShapeMatrix(brickManager.getBrick().getNumberOfRotation()), (int) point.getX(), (int) point.getY());
    }
    public boolean stopAndCreateNewBrick() {
    mergeBrickToBackground();
    brickManager.NextBrick();
    boolean success = createNewBrick();
    return success;
}
    public int[][] getGrid() {
        return boardMatrix;
    }

    public boolean moveDown() {
    int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation());
    int brickX = (int) point.getX();
    int brickY = (int) point.getY();
       if (TabOperation.checkLimit(boardMatrix, brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation()), (int) point.getX(), (int) point.getY())) {
           return false;
       }
       // Effacer la pièce de sa position actuelle dans la grille
    deletePosition(brickX,brickY,brickMatrix);



    // Vérifier si la pièce peut se déplacer vers le bas
    if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX, brickY + 1)) {
        // Si la pièce peut se déplacer vers le bas, la déplacer
        point.translate(0, 1);
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[brickY + i + 1][brickX + j] = brickMatrix[i][j];
                }
            }
        }
        return true;
    } else {
        // Si la pièce ne peut pas se déplacer vers le bas, arrêter la pièce et créer une nouvelle pièce
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[brickY + i][brickX + j] = brickMatrix[i][j];
                }
            }
        }
        return false;
    }
}
    public void updateScore() {
    int oldScore = this.score;
    this.score += 10;
    pcs.firePropertyChange("Score", oldScore, this.score);
}
    public void checkAndClearFullRows() {
        for (int i = rows - 1; i >= 0; i--) {
            if (isRowFull(i)) {
                updateScore();
                deleteRow(i);
                moveRowsDown(i);
                i++; // Recheck the current row as it now contains the row that was above it
            }
        }
    }

    private boolean isRowFull(int row) {
        for (int j = 0; j < cols; j++) {
            if (boardMatrix[row][j] == 0) {
                return false;
            }
        }
        return true;
    }

    private void deleteRow(int row) {
        Arrays.fill(boardMatrix[row], 0);
    }

    private void moveRowsDown(int startRow) {
        for (int i = startRow; i > 0; i--) {
            System.arraycopy(boardMatrix[i - 1], 0, boardMatrix[i], 0, cols);
        }
    }

    public boolean rotation() {
            int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation());
            int[][] old = brickMatrix;
            int brickX = (int) point.getX();
            int brickY = (int) point.getY();

            // Effacer la pièce de sa position actuelle dans la grille
            for (int i = 0; i < brickMatrix.length; i++) {
                for (int j = 0; j < brickMatrix[i].length; j++) {
                    if (brickMatrix[i][j] != 0) {
                        if (brickY + i < boardMatrix.length && brickX + j < boardMatrix[0].length) {
                            boardMatrix[brickY + i][brickX + j] = 0;
                        }
                    }
                }
            }
            brickMatrix = brickManager.getBrick().getShapeMatrix(brickManager.getBrick().nextRotationIndex());

            if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX, brickY)) {
                brickManager.getBrick().setRotationIndex(brickManager.getBrick().nextRotationIndex());
                return true;
            } else {
                return false;
            }
        }

    public boolean moveLeft() {
        int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation());
        int brickX = (int) point.getX();
        int brickY = (int) point.getY();
        deletePosition(brickX,brickY,brickMatrix);

        // Vérifier si la pièce peut se déplacer vers la gauche
        if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX - 1, brickY)) {
            // Si la pièce peut se déplacer vers la gauche, la déplacer
            point.translate(-1, 0);
            horizontalMove(brickX,brickY,brickMatrix);
            return true;
        } else {
            // Si la pièce ne peut pas se déplacer vers la gauche, ne pas la déplacer
            return false;
        }
    }

    public boolean moveRight() {
    int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation());
    int brickX = (int) point.getX();
    int brickY = (int) point.getY();
    deletePosition(brickX,brickY,brickMatrix);

    // Vérifier si la pièce peut se déplacer vers la droite
    if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX + 1, brickY)) {
        // Si la pièce peut se déplacer vers la droite, la déplacer
        point.translate(1, 0);
        horizontalMove(brickX,brickY,brickMatrix);
        return true;
    } else {
        // Si la pièce ne peut pas se déplacer vers la droite, ne pas la déplacer
        return false;
    }
}
    public void mergeBrickToBackground() {
        int[][] old = boardMatrix;
        boardMatrix = TabOperation.merge(boardMatrix, brickManager.getBrick().getShapeMatrix(brickManager.getBrick().getNumberOfRotation()), (int) point.getX(), (int) point.getY());
        pcs.firePropertyChange("NewMove", old, boardMatrix);
    }
    public void handleMovement(String direction) {
    boolean success;
    boolean gameOver = false;
    switch (direction) {
        case "Left":
            success = moveLeft();
            break;
        case "Right":
            success = moveRight();
            break;
        case "Down":
            success = moveDown();
            break;
        case "Space":
            success = rotation();
            break;
        default:
            throw new IllegalArgumentException("Invalid direction: " + direction);
    }
    if (success) {
        mergeBrickToBackground();
        displayBoard();
    } else if (direction.equals("Down")) {
        gameOver = stopAndCreateNewBrick();
        if(gameOver){
            System.out.println("Game Over");
            restartGame();
        }
        checkAndClearFullRows();
    }
}
    public void restartGame() {
        boardMatrix = new int[rows][cols];
        brickManager = new BrickManager();
        score = 0;
        createNewBrick();
    }
    public void displayBoard() {
        // Afficher la grille de jeu
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(boardMatrix[i][j] == 0 ? "0" : boardMatrix[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("_________________________");

    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return pcs.getPropertyChangeListeners();
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return pcs.getPropertyChangeListeners(propertyName);
    }

    public void deletePosition(int x,int y,int[][] brickMatrix){
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[y + i][x + j] = 0;
                }
            }
        }
    }

    public void horizontalMove(int x,int y,int[][] brickMatrix){
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[y + i][x + j] = 0;
                }
            }
        }
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        while (true) {
            try {
                handleMovement("Down");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Gérer l'interruption ici, par exemple en rétablissant le statut d'interruption
                Thread.currentThread().interrupt();
            }
        }
}
}
