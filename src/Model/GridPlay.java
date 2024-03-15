package Model;

public class GridPlay {
    private int[][] grid;
    private int width;
    private int height;

    public GridPlay() {
        this.width = 10;
        this.height = 20;
        grid = new int[width][height];
    }
    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    System.out.print(". ");  // Display . for empty cells
                } else {
                    System.out.print("X ");  // Display X for blocks
                }
            }
            System.out.println();
        }
    }
    public void initGrid(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }
    public void updateGrid(Shape shape){
        for (int i = 0; i < shape.getGrid().width; i++) {
            for (int j = 0; j < shape.getGrid().height; j++) {
                if (shape.getGrid().getNumber(i,j) == 1) {
                    grid[shape.getX() + i][shape.getY() + j] += 1;
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }
    public int getNumber(int x, int y){
        return grid[x][y];
    }

    public void setNumber(int x, int y, int value){
        grid[x][y] = value;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
