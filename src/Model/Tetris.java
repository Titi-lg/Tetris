package Model;

import java.util.Random;

public class Tetris {
    private Shape[] stack;

    private GridPlay gridPlay;
    private static final Random RANDOM = new Random();

    public Tetris() {
        this.stack = new Shape[2];
        this.gridPlay = new GridPlay();
    }

    public GridPlay getGridPlay() {
        return gridPlay;
    }
    public void init(){
        newShape();
        this.stack[0]=this.stack[1];
        newShape();
        this.gridPlay.initGrid();
        this.gridPlay.updateGrid(stack[0]);
    }

    public static int getRandomNumber() {
        return RANDOM.nextInt(7) ;
    }

    public void moveDown(){
        this.stack[0].moveDown();
        this.gridPlay.updateGrid(stack[0]);
    }
    public void newShape(){
        Shape shape = new Shape(0,0,1,1);
        int shapeNum = getRandomNumber();
        switch (shapeNum){
            case 0:
                shape.createShape(Shape.Shapes.SQUARE,0);
                stack[1] = shape;
                break;
            case 1:
                shape.createShape(Shape.Shapes.LINE,0);
                stack[1] = shape;
                break;
            case 2:
                shape.createShape(Shape.Shapes.S,0);
                stack[1] = shape;
                break;
            case 3:
                shape.createShape(Shape.Shapes.Z,0);
                stack[1] = shape;
                break;
            case 4:
                shape.createShape(Shape.Shapes.T,0);
                stack[1] = shape;
                break;
            case 5:
                shape.createShape(Shape.Shapes.L,0);
                stack[1] = shape;
                break;
            case 6:
                shape.createShape(Shape.Shapes.J,0);
                stack[1] = shape;
                break;
        }
    }
    public void addShape(){
        this.stack[0] = this.stack[1];
        newShape();
        this.gridPlay.updateGrid(stack[0]);


    }
}
