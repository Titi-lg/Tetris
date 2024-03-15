package Model;
import java.util.Observable;
public class Game extends Observable implements Runnable{
    private Tetris tetris;

    public Game(){
        this.tetris = new Tetris();
    }
    public void play(){
        tetris.init();
    }
    public Tetris getTetris(){
        return tetris;
    }
    @Override
    public void run(){
        while(true){
            //tetris.moveDown();
            tetris.getGridPlay().displayGrid();
            this.setChanged();
            this.notifyObservers();
            try {
                Thread.sleep(1000);
                System.out.println("test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
