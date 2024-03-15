package Model;

import java.util.Observable;

public class Block{
    protected int x,y,dx,dy;
    protected GridPlay grid;

    public Block(int x, int y, int dx, int dy) {
        this.grid = new GridPlay();
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

    public GridPlay getGrid() {
        return grid;
    }
    public void setGrid(GridPlay grid) {
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
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                grid.setNumber(i,j,0);
            }
        }
    }
    public void moveDown(){;
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (grid.getNumber(i,j)== 1) {
                    if (j+1<grid.getHeight() ) {
                        grid.setNumber(j+1,j,1);
                        grid.setNumber(i,j,0);
                    }
                    else {
                        return;
                    }
                }
            }
        }
    }
}
