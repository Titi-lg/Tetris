import model.Board;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        board.createNewBrick();
        board.mergeBrickToBackground();
        board.displayBoard();

        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }
        if (board.moveRight()) {
            board.mergeBrickToBackground();
            board.displayBoard();
        }

    }
}