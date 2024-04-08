import model.Board;
import View.TetrisFrame;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(20, 10);
        board.createNewBrick();
        board.mergeBrickToBackground();
        board.displayBoard();
        TetrisFrame frame = new TetrisFrame(board);
        board.addPropertyChangeListener(frame);
    }
}