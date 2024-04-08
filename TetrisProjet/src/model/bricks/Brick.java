package model.bricks;

import java.util.List;

public interface Brick {
        int[][] getShapeMatrix(int rotationIndex);
        int getNumberOfRotation();
        void setRotationIndex(int rotationIndex);
        int nextRotationIndex();
}
