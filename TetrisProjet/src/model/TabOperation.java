package model;

public class TabOperation {
    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {
                int targetX = x + i;
                int targetY = y + j;
                if (brick[j][i] != 0 && (checkOutOfBound(matrix, targetX, targetY) || matrix[targetY][targetX] != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkOutOfBound(int[][] matrix, int targetX, int targetY) {
        boolean returnValue = true;
        if (targetX >= 0 && targetY < matrix.length && targetX < matrix[targetY].length) {
            returnValue = false;
        }
        return returnValue;
    }

    public static int[][] copy(int[][] original) {
        int[][] newTab = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            int[] aMatrix = original[i];
            int aLength = aMatrix.length;
            newTab[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, newTab[i], 0, aLength);
        }
        return newTab;
    }


    public static int[][] merge(int[][] boardMatrix, int[][] brickMatrix, int row, int col) {
    int[][] newMatrix = copy(boardMatrix);
    for (int i = 0; i < brickMatrix.length; i++) {
        for (int j = 0; j < brickMatrix[i].length; j++) {
            if (brickMatrix[j][i] != 0) {
                if (col + j < newMatrix.length && row + i < newMatrix[0].length) {
                    newMatrix[col + j][row + i] = brickMatrix[j][i];
                }
            }
        }
    }
    return newMatrix;
}


    public static boolean checkLimit(int[][] boardMatrix, int[][] brickMatrix, int row, int col) {
        for (int i = 0; i < brickMatrix.length; i++) {
            for (int j = 0; j < brickMatrix[i].length; j++) {
                if (brickMatrix[j][i] != 0) {
                    if (col + j < 0 || row + i < 0 || row + i >= boardMatrix[0].length || col + j >= boardMatrix.length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }









}
