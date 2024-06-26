package model;

import View.NextPieceCanva;
import View.Themes;
import model.bricks.Brick;
import model.bricks.BrickManager;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class BoardGrand implements Runnable{
    private int[][] boardMatrix;
    private BrickManager brickManager, brickManager2;
    private final int rows;
    private final int cols;
    private int score = 0;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private Point point,point2;


    private  boolean gameOver=false;

    public BoardGrand(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boardMatrix = new int[rows][cols];
        brickManager = new BrickManager();
        brickManager2 = new BrickManager();

    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public boolean createNewBrick() {
        Brick currentBrick = brickManager.getBrick();
        point = new Point(cols-4, 0);
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


    public void updateScore(int r) {
        int oldScore = this.score;
        this.score += 10*r;
        pcs.firePropertyChange("Score", oldScore, this.score);
    }
    public void checkAndClearFullRows() {
        int multiple=1;
        int coef=1;
        for (int i = rows - 1; i >= 0; i--) {
            if (isRowFull(i)) {
                updateScore(multiple);
                deleteRow(i);
                moveRowsDown(i);
                i++;
                coef++;
                multiple++;
                multiple*=coef;
                // Recheck the current row as it now contains the row that was above it
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
        // Supprimer les blocs du joueur
        for (int j = 0; j < cols; j++) {
            boardMatrix[0][j] = 0;
        }

        // Si une ligne a été détruite, déplacer les lignes vers le bas
        for (int i = startRow; i > 0; i--) {
            System.arraycopy(boardMatrix[i - 1], 0, boardMatrix[i], 0, cols);
        }

        // Réinitialiser les lignes au-dessus de l'avant-dernière ligne déplacée vers le bas
        for (int i = startRow - 3; i >= 0; i--) {
            Arrays.fill(boardMatrix[i], 0);
        }

        // Vérifier si le bloc du joueur est isolé
        boolean playerBlockIsolated = true;
        for (int j = 0; j < cols; j++) {
            if (boardMatrix[1][j] != 0) {
                playerBlockIsolated = false;
                break;
            }
        }

        // Si le bloc du joueur est isolé, supprimer le bloc et créer deux nouveaux blocs pour le joueur
        if (playerBlockIsolated) {
            createNewBrick();
            createNewBrick2();
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
        if (gameOver) {
            return;
        }
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
        } else if (direction.equals("Down")) {
            gameOver = stopAndCreateNewBrick();
            if(gameOver){
                gameEnd();
            }
            updateNextPiece();
            checkAndClearFullRows();
        }
    }


    public void updateNextPiece() {
        Brick nextBrick = brickManager.getNextBrick();
        //Brick nextBrick2 = brickManager2.getNextBrick();
        pcs.firePropertyChange("NextPiece", null, nextBrick);
        //pcs.firePropertyChange("NextPiece2", null, nextBrick2);
    }
    public void updateNextPiece2() {
        //Brick nextBrick = brickManager.getNextBrick();
        Brick nextBrick2 = brickManager2.getNextBrick();
        //pcs.firePropertyChange("NextPiece", null, nextBrick);
        pcs.firePropertyChange("NextPiece2", null, nextBrick2);
    }

    public void restartGame() {
        gameOver = false;
        int oldScore = this.score;
        boardMatrix = new int[rows][cols];
        brickManager = new BrickManager();
        brickManager2 = new BrickManager();
        this.score = 0;
        pcs.firePropertyChange("Score", oldScore, this.score);
        createNewBrick();
        createNewBrick2();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
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

    private boolean toSleep = false;
    public void setToSleep(boolean toSleep) {
        synchronized (this) {
            this.toSleep = toSleep;
            if (!toSleep) {
                notifyAll(); // Réveiller tous les threads qui attendent sur cet objet
            }
        }
    }
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (toSleep) { // Attendre tant que toSleep est vrai
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                int timetodown;
                handleMovement("Down");
                handleMovement2("Down");
                if (this.score >= 500){
                    timetodown = 150;
                } else if (this.score >= 200) {
                    timetodown = 200;
                } else if (this.score >= 150) {
                    timetodown = 250;
                } else if (this.score >= 100) {
                    timetodown = 300;
                } else if (this.score >= 50) {
                    timetodown = 400;
                } else {
                    timetodown = 500;
                }
                sleep(timetodown);
            } catch (InterruptedException e) {
                // Gérer l'interruption ici, par exemple en rétablissant le statut d'interruption
                Thread.currentThread().interrupt();
            }
        }
    }
    // Retourne le brickManager pour le constructeur de NextPieceCanva
    public BrickManager getBrickManager() {
        return brickManager;
    }




    public void Pause() {
        setToSleep(true);
    }

    public void Resume() {
        setToSleep(false);
    }

    public void gameEnd() {
        pcs.firePropertyChange("GameOver", null, null);
        System.out.println("Game Over");
        restartGame();
    }
    public Boolean gameOver() {
        return true;
    }

    public String getScore() {
        return String.valueOf(score);
    }
    public boolean stopAndCreateNewBrick2() {
        mergeBrickToBackground();
        brickManager2.NextBrick();
        boolean success = createNewBrick2();
        return success;
    }

    public boolean moveDown2() {
        int[][] brickMatrix = brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation());
        int brickX = (int) point2.getX();
        int brickY = (int) point2.getY();
        if (TabOperation.checkLimit(boardMatrix, brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation()), (int) point2.getX(), (int) point2.getY())) {
            return false;
        }
        // Effacer la pièce de sa position actuelle dans la grille
        deletePosition(brickX,brickY,brickMatrix);



        // Vérifier si la pièce peut se déplacer vers le bas
        if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX, brickY + 1)) {
            // Si la pièce peut se déplacer vers le bas, la déplacer
            point2.translate(0, 1);
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
    public boolean rotation2() {
        int[][] brickMatrix = brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation());
        int[][] old = brickMatrix;
        int brickX = (int) point2.getX();
        int brickY = (int) point2.getY();

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
        brickMatrix = brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().nextRotationIndex());

        if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX, brickY)) {
            brickManager2.getBrick().setRotationIndex(brickManager2.getBrick().nextRotationIndex());
            return true;
        } else {
            return false;
        }
    }
    public boolean moveLeft2() {
        int[][] brickMatrix = brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation());
        int brickX = (int) point2.getX();
        int brickY = (int) point2.getY();
        deletePosition(brickX,brickY,brickMatrix);

        // Vérifier si la pièce peut se déplacer vers la gauche
        if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX - 1, brickY)) {
            // Si la pièce peut se déplacer vers la gauche, la déplacer
            point2.translate(-1, 0);
            horizontalMove(brickX,brickY,brickMatrix);
            return true;
        } else {
            // Si la pièce ne peut pas se déplacer vers la gauche, ne pas la déplacer
            return false;
        }
    }
    public boolean moveRight2() {
        int[][] brickMatrix = brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation());
        int brickX = (int) point2.getX();
        int brickY = (int) point2.getY();
        deletePosition(brickX,brickY,brickMatrix);

        // Vérifier si la pièce peut se déplacer vers la droite
        if (!TabOperation.intersect(boardMatrix, brickMatrix, brickX + 1, brickY)) {
            // Si la pièce peut se déplacer vers la droite, la déplacer
            point2.translate(1, 0);
            horizontalMove(brickX,brickY,brickMatrix);
            return true;
        } else {
            // Si la pièce ne peut pas se déplacer vers la droite, ne pas la déplacer
            return false;
        }

    }
    public void mergeBrickToBackground2() {
        int[][] old = boardMatrix;
        boardMatrix = TabOperation.merge(boardMatrix, brickManager2.getBrick().getShapeMatrix(brickManager2.getBrick().getNumberOfRotation()), (int) point2.getX(), (int) point2.getY());
        pcs.firePropertyChange("NewMove", old, boardMatrix);
    }

    public void handleMovement2(String direction) {
        boolean success;
        if (gameOver) {
            return;
        }
        switch (direction) {
            case "Left":
                success = moveLeft2();
                break;
            case "Right":
                success = moveRight2();
                break;
            case "Down":
                success = moveDown2();
                break;
            case "Space":
                success = rotation2();
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        if (success) {
            mergeBrickToBackground2();
        } else if (direction.equals("Down")) {
            gameOver = stopAndCreateNewBrick2();
            if(gameOver){
                gameEnd();
            }
            updateNextPiece2();
            checkAndClearFullRows();
        }
    }
    public BrickManager getBrickManager2() {
        return brickManager2;
    }
    public boolean createNewBrick2() {
        Brick currentBrick = brickManager2.getBrick();
        point2 = new Point(0, 0);
        return TabOperation.intersect(boardMatrix, currentBrick.getShapeMatrix(brickManager2.getBrick().getNumberOfRotation()), (int) point2.getX(), (int) point2.getY());
    }
}
