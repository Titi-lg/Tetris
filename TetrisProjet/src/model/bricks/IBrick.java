package model.bricks;

import java.util.ArrayList;
import java.util.List;

public class IBrick implements Brick{
    private final List<int[][]> brickMatrix = new ArrayList<>();
    private int rotationIndex = 0;

    public IBrick() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        });
    }

    @Override
    public int[][] getShapeMatrix(int rotationIndex) {
        return brickMatrix.get(rotationIndex);
    }

    @Override
    public int getNumberOfRotation() {
        return rotationIndex ;
    }

    @Override
    public void setRotationIndex(int rotationIndex) {
        this.rotationIndex = rotationIndex;
    }

    @Override
    public int nextRotationIndex() {
        if (getNumberOfRotation() <  brickMatrix.size() -1){
            return getNumberOfRotation() + 1;
        } else {
            return 0;
        }
    }
}
