package View;
import Controller.KeyPadController;
import model.Board;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class TetrisFrame extends JFrame implements PropertyChangeListener {
    //private TetrisCanva tetrisCanva;
    public static int hauteur=800, largeur=500;
    private JButton pauseButton;

    private Board board;
    private Board board2;
    private JPanel pJeu, pNextPiece, pOptions,pRight,pLeft,pSouth,pNextPeice2,pJeu2,pRight2;
    private ScorePanel scorePanel, scorePanel2;
    private Thread thread;
    private Thread thread2;
    private KeyPadController keyPadController;
    private TetrisCanva tetrisCanva2 ,tetrisCanva;

    private NextPieceCanva nextPieceCanva;
    private NextPieceCanva nextPieceCanva2;

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
        pauseButton = new JButton(new ImageIcon(this.getClass().getResource("/assets/Pause.jpg")));
        pauseButton.setBorderPainted(false); // Remove the button border
        pauseButton.setFocusPainted(false); // Remove focus border
        pauseButton.setContentAreaFilled(false); // Make the button transparent
        pauseButton.setPreferredSize(new Dimension(100, 50));

        pJeu = new JPanel(new BorderLayout());
        pRight = new JPanel(new GridLayout(2,1));
        scorePanel = new ScorePanel(0);
        board.addPropertyChangeListener(scorePanel);
        pNextPiece = new JPanel();
        pOptions = new JPanel();
        pLeft = new JPanel();
        pSouth = new JPanel();
        nextPieceCanva = new NextPieceCanva(board, board.getBrickManager());
        pNextPiece.add(nextPieceCanva);
        pRight.add(pNextPiece);
        pRight.add(scorePanel);
        keyPadController = new KeyPadController(board,board2);
        addKeyListener(keyPadController);
        setFocusable(true);
        tetrisCanva = new TetrisCanva(board);
        board.addPropertyChangeListener(tetrisCanva);
        pJeu.add(tetrisCanva, BorderLayout.CENTER);
        add(pJeu, BorderLayout.CENTER);
        pJeu.add(pRight, BorderLayout.EAST);
        add(pLeft, BorderLayout.WEST);
        add(pSouth, BorderLayout.SOUTH);
        add(pOptions, BorderLayout.NORTH);
        setVisible(true);
        thread = new Thread(board);
        thread.start();
        Themes mainThemes = new Themes("src/assets/maintheme.wav");
        mainThemes.playMusic();
        board2 = new Board(20, 10);
        board2.addPropertyChangeListener(this);
        board2.initialisation();
        thread2 = new Thread(board2);
        thread2.start();
        board2.setToSleep(true);


    }
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("GameOver")) {
            JOptionPane.showMessageDialog(this, "Game Over");
        }
        /*if (event.getPropertyName().equals("Pause")) {
            if (board.isPause()) {
                pauseButton.setIcon(new ImageIcon(this.getClass().getResource("/assets/Play.jpg")));
            } else {
                pauseButton.setIcon(new ImageIcon(this.getClass().getResource("/assets/Pause.jpg")));
            }
        }*/
        if (event.getPropertyName().equals("addJoueur")) {
            board2.setToSleep(false);
            board.initialisation();
            board2.initialisation();

            tetrisCanva2 = new TetrisCanva(board2);
            board2.addPropertyChangeListener(tetrisCanva2);
            pLeft.add(tetrisCanva2);
            nextPieceCanva2 = new NextPieceCanva(board2, board2.getBrickManager());
            pNextPeice2 = new JPanel();
            pJeu2 = new JPanel(new BorderLayout());
            pRight2 = new JPanel(new GridLayout(2, 1));
            scorePanel2 = new ScorePanel(0);
            board2.addPropertyChangeListener(scorePanel2);
            pNextPeice2.add(nextPieceCanva2);
            pRight2.add(pNextPeice2);
            pRight2.add(scorePanel2);
            pJeu2.add(tetrisCanva2, BorderLayout.CENTER);
            add(pJeu2, BorderLayout.WEST);
            pJeu2.add(pRight2, BorderLayout.EAST);
            keyPadController.setBoard2(board2);
            pack();
        }
        if (event.getPropertyName().equals("Cancel")) {
                board2.setToSleep(true);
                pJeu2.remove(pRight2);
                pJeu2.remove(tetrisCanva2);
                pLeft.remove(tetrisCanva2);
                pJeu2.remove(pNextPeice2);
                pJeu2.remove(pRight2);
                pJeu2.remove(pJeu2);
                keyPadController.setBoard2(null);
                pack();
                board.initialisation();
        }
    }


}
