package Model;

import java.util.Observable;

public class Block extends Observable implements Runnable{
    private int x,y,dx,dy;
    private int maxX,maxY;

    public Block(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.maxX = 1000;
        this.maxY = 1000;

    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


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
    }
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
