package model.bricks;

import java.util.ArrayList;

public class BrickManager {
    private final String[] brickTypes = {"I", "J", "L", "O", "S", "T", "Z"};
    private ArrayList<Brick> bricks = new ArrayList<>();

    public BrickManager() {
        bricks.add(getRandomBrick());
        bricks.add(getRandomBrick());
        bricks.add(getRandomBrick());
    }

    public Brick getRandomBrick() {
        return getBrick(getRandomBrickType());
    }

    public String getRandomBrickType() {
        return brickTypes[(int) (Math.random() * brickTypes.length)];
    }

    public Brick getBrick(String brickType) {
        switch (brickType) {
            case "I":
                return new IBrick();
            case "J":
                return new JBrick();
            case "L":
                return new LBrick();
            case "O":
                return new SquareBrick();
            case "S":
                return new SBrick();
            case "T":
                return new TBrick();
            case "Z":
                return new ZBrick();
            default:
                return null;
        }
    }

    public Brick getBrick() {
        if (bricks.size() <= 1) {
            bricks.add(getRandomBrick());
        }
        return bricks.get(0);
    }
    public void NextBrick() {
        bricks.remove(0);
    }

    public Brick getNextBrick() {
        return bricks.get(1);
    }


}
