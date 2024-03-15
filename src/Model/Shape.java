package Model;

public class Shape extends Block{
    public enum Shapes {
        SQUARE, LINE, S, Z, T, L, J
    }

    public Shape(int x, int y, int dx, int dy) {
        super(x, y, dx, dy);
    }

    public void createShape(Shape.Shapes shapeType,int x) {
        this.initGrid();
        switch (shapeType) {
            case SQUARE:
                square(x);
                break;
            case LINE:
                line(x);
                break;
            case S:
                s(x);
                break;
            case Z:
                z(x);
                break;
            case T:
                t(x);
                break;
            case L:
                l(x);
                break;
            case J:
                j(x);
                break;
        }
    }
    public void square(int x){
        this.grid[x][0]=1;
        this.grid[x][1]=1;
        this.grid[x+1][0]=1;
        this.grid[x+1][1]=1;
        System.out.println("square");
    }
    public void line(int x){
        this.grid[x][0]=1;
        this.grid[x][1]=1;
        this.grid[x][2]=1;
        this.grid[x][3]=1;
        System.out.println("line");
    }
    public void s(int x){
        this.grid[x+1][0]=1;
        this.grid[x+2][0]=1;
        this.grid[x][1]=1;
        this.grid[x+1][1]=1;
        System.out.println("s");
    }
    public void z(int x){
        this.grid[x][0]=1;
        this.grid[x+1][0]=1;
        this.grid[x+1][1]=1;
        this.grid[x+2][1]=1;
        System.out.println("z");
    }
    public void t(int x){
        this.grid[x+1][0]=1;
        this.grid[x+1][1]=1;
        this.grid[x][1]=1;
        this.grid[x+1][1]=1;
        System.out.println("t");
    }
    public void l(int x){
        this.grid[x][0]=1;
        this.grid[x][1]=1;
        this.grid[x][2]=1;
        this.grid[x+1][2]=1;
        System.out.println("l");
    }
    public void j(int x){
        this.grid[x+1][0]=1;
        this.grid[x+1][1]=1;
        this.grid[x+1][2]=1;
        this.grid[x][2]=1;
        System.out.println("j");
    }


}
