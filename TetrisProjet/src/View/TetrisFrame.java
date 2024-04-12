package View;
import Controller.KeyPadController;
import model.Board;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TetrisFrame extends JFrame implements PropertyChangeListener {
    //private TetrisCanva tetrisCanva;
    public static int hauteur=800, largeur=500;

    private Board board;
    private JPanel pJeu, pNextPiece, pOptions,pRight,pLeft,pSouth;
    private ScorePanel scorePanel;

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

  //  @Override
  //  public void update(Observable o, Object arg) {tetrisCanva.repaint();
    //}


    public TetrisFrame() {
        board = new Board(20, 10);
        board.addPropertyChangeListener(this);
        board.initialisation();

        setTitle("Tetris");
        setSize(largeur+200, hauteur+200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pJeu = new JPanel(new BorderLayout());
        pRight = new JPanel(new GridLayout(2,1));
        scorePanel = new ScorePanel(0);
        board.addPropertyChangeListener(scorePanel);
        pNextPiece = new JPanel();
        pOptions = new JPanel();
        pLeft = new JPanel();
        pSouth = new JPanel();
        NextPieceCanva nextPieceCanva = new NextPieceCanva(board, board.getBrickManager());
        pNextPiece.add(nextPieceCanva);
        pRight.add(pNextPiece);
        pRight.add(scorePanel);
        KeyPadController keyPadController = new KeyPadController(board);
        addKeyListener(keyPadController);
        setFocusable(true);
        TetrisCanva tetrisCanva = new TetrisCanva(board);
        board.addPropertyChangeListener(tetrisCanva);
        pJeu.add(tetrisCanva, BorderLayout.CENTER);
        add(pJeu, BorderLayout.CENTER);
        add(pRight, BorderLayout.EAST);
        add(pLeft, BorderLayout.WEST);
        add(pSouth, BorderLayout.SOUTH);
        add(pOptions, BorderLayout.NORTH);
        setVisible(true);
        Thread thread = new Thread(board);
        thread.start();

    }
    public void propertyChange(PropertyChangeEvent event) {
    }


}
