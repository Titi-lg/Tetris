package model.bricks;

import java.util.ArrayList;
import java.util.List;

public class SquareBrick implements Brick {
    private final List<int[][]> brickMatrix = new ArrayList<>();
    private int rotationIndex = 0;

    public SquareBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }

    @Override
    public int[][] getShapeMatrix(int rotationIndex) {
        return brickMatrix.get(rotationIndex);
    }

    @Override
    public int getNumberOfRotation() {
        return rotationIndex = 0;
    }

    @Override
    public void setRotationIndex(int rotationIndex) {
        this.rotationIndex = rotationIndex;
    }
    @Override
    public int nextRotationIndex() {
        return 0;
    }
}
