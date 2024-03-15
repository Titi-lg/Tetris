package Model;

import java.util.Observable;

public class Block{
    protected int x,y,dx,dy;
    protected int maxX=4,maxY=4;
    protected int[][] grid;

    public Block(int x, int y, int dx, int dy) {
        this.grid = new int[maxX][maxY];
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int[][] getGrid() {
        return grid;
    }
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDx(int dx) {
        this.dx = dx;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }
    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
    public void initGrid(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    public void moveRight(){
        int[][] grid = this.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i+1<maxX) {
                        if (grid[i + 1][j] == 0) {
                            grid[i + 1][j] = 1;
                            grid[i][j] = 0;
                        }
                    }
                    else {
                        return;
                    }
                }
            }
        }
    }
    public void moveLeft(){
        int[][] grid = this.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i-1>=0) {
                        if (grid[i - 1][j] == 0) {
                            grid[i - 1][j] = 1;
                            grid[i][j] = 0;
                        }
                    }
                    else {
                        return;
                    }
                }
            }
        }
    }
    public void moveDown(){
        int[][] grid = this.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (j+1<maxY) {
                        if (grid[i][j + 1] == 0) {
                            grid[i][j + 1] = 1;
                            grid[i][j] = 0;
                        }
                    }
                    else {
                        return;
                    }
                }
            }
        }
    }
    /*@Override
    public void run(){
        while(true){
            try {
                this.setChanged();
                this.notifyObservers();
                Thread.sleep(1000);
                nextStep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
    public void nextStep(){
        if (this.x-this.dx>=0) {
            this.x = this.x-this.dx;
        }
       if (this.y-this.dy>=0){
           this.y = this.y-this.dy;
       }
    }
    public void affiche(){
        System.out.println("x = "+this.x+" y = "+this.y);
    }
}
