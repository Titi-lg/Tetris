package model;

import model.bricks.Brick;
import model.bricks.BrickManager;

import java.awt.*;

public class Board {
    private int[][] boardMatrix;
    private BrickManager brickManager;
    private final int rows;
    private final int cols;

    private Point point;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boardMatrix = new int[rows][cols];
        brickManager = new BrickManager();
    }

    public boolean createNewBrick() {
        Brick currentBrick = brickManager.getBrick();
        point = new Point(4, 0);
        return TabOperation.intersect(boardMatrix, currentBrick.getShapeMatrix(0), (int) point.getX(), (int) point.getY());
    }

    public boolean moveDown() {
        int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(0);
        int brickX = (int) point.getX();
        int brickY = (int) point.getY();

        // Effacer la pièce de sa position actuelle dans la grille
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[brickY + i][brickX + j] = 0;
                }
            }
        }

        point.translate(0, 1);

        if (!TabOperation.intersect(boardMatrix, brickMatrix, (int) point.getX(), (int) point.getY())) {
            for (int i = 0; i < brickMatrix.length; i++) {
                for (int j = 0; j < brickMatrix[i].length; j++) {
                    if (brickMatrix[i][j] != 0) {
                        boardMatrix[brickY + i][brickX + j] = 0;
                    }
                }
            }
                return true;
        } else {
            return false;
        }
    }

    public boolean moveLeft() {
        int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(0);
        int brickX = (int) point.getX();
        int brickY = (int) point.getY();

        // Effacer la pièce de sa position actuelle dans la grille
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[brickY + i][brickX + j] = 0;
                }
            }
        }

        point.translate(-1, 0);

        if (!TabOperation.intersect(boardMatrix, brickMatrix, (int) point.getX(), (int) point.getY())) {
            for (int i = 0; i < brickMatrix.length; i++) {
                for (int j = 0; j < brickMatrix[i].length; j++) {
                    if (brickMatrix[i][j] != 0) {
                        boardMatrix[brickY + i][brickX + j] = 0;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        int[][] brickMatrix = brickManager.getBrick().getShapeMatrix(0);
        int brickX = (int) point.getX();
        int brickY = (int) point.getY();

        // Effacer la pièce de sa position actuelle dans la grille
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[i][j] != 0) {
                    boardMatrix[brickY + i][brickX + j] = 0;
                }
            }
        }

        point.translate(1, 0);

        if (!TabOperation.intersect(boardMatrix, brickMatrix, (int) point.getX(), (int) point.getY())) {
            for (int i = 0; i < brickMatrix.length; i++) {
                for (int j = 0; j < brickMatrix[i].length; j++) {
                    if (brickMatrix[i][j] != 0) {
                        boardMatrix[brickY + i][brickX + j] = 0;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public void mergeBrickToBackground() {
        boardMatrix = TabOperation.merge(boardMatrix, brickManager.getBrick().getShapeMatrix(0), (int) point.getX(), (int) point.getY());
    }
    public void displayBoard() {
        // Afficher la grille de jeu
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(boardMatrix[i][j] == 0 ? "0" : "#");
            }
            System.out.println("|");
        }
        System.out.println("_________________________");

    }



}
