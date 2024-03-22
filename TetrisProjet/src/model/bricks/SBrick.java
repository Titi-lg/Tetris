package model.bricks;

import java.util.ArrayList;
import java.util.List;

public class SBrick implements Brick{
    private final List<int[][]> brickMatrix = new ArrayList<>();

    public SBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 5, 5, 0},
                {5, 5, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {5, 0, 0, 0},
                {5, 5, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public int[][] getShapeMatrix(int rotationIndex) {
        return brickMatrix.get(rotationIndex);
    }
}
