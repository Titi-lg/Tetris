package View;

import Controller.KeyPadController;
import model.Board;
import model.BoardGrand;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GrandTetrisFrame extends JFrame implements PropertyChangeListener {
    private JButton pauseButton;

    private BoardGrand board;

    private Themes mainTheme;
    private Themes soundGameOver;

    private JPanel pJeu, pNextPiece, pOptions, pRight, pLeft, pSouth, pNextPiece2;
    private ScorePanel scorePanel;
    private Thread thread;
    private KeyPadController keyPadController;
    private GrandCanva tetrisCanva;


    private NextPieceCanva nextPieceCanva;
    private NextPieceCanvaJ2 nextPieceCanva2;
    public Boolean coop = false;

    public GrandTetrisFrame() {
        board = new BoardGrand(25, 20);
        board.addPropertyChangeListener(this);
        board.restartGame();
        mainTheme = new Themes("src/assets/maintheme.wav");
        mainTheme.loopMusic();
        setTitle("Grand Tetris Coop");
        //setSize(largeur+200, hauteur+200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pJeu = new JPanel(new BorderLayout());
        pRight = new JPanel(new GridLayout(3, 1));
        scorePanel = new ScorePanel(0);
        scorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
        board.addPropertyChangeListener(scorePanel);
        pNextPiece = new JPanel();
        pNextPiece.setBorder(BorderFactory.createTitledBorder("Next J1"));
        pNextPiece2 = new JPanel();
        pNextPiece2.setBorder(BorderFactory.createTitledBorder("Next J2"));
        pOptions = new JPanel();
        pLeft = new JPanel();
        pSouth = new JPanel();
        nextPieceCanva = new NextPieceCanva(board, board.getBrickManager());
        pNextPiece.add(nextPieceCanva);
        nextPieceCanva2 = new NextPieceCanvaJ2(board, board.getBrickManager2());
        pNextPiece2.add(nextPieceCanva2);
        pRight.add(pNextPiece);
        pRight.add(pNextPiece2);
        pRight.add(scorePanel);
        keyPadController = new KeyPadController(board);
        addKeyListener(keyPadController);
        setFocusable(true);
        tetrisCanva = new GrandCanva(board);
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
        coop = false;
        pack();


    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("GameOver")) {
            mainTheme.stopMusic();
            soundGameOver = new Themes("src/assets/gameover.wav");
            soundGameOver.playMusic();
            board.setToSleep(true);
            JOptionPane.showMessageDialog(this, "Game Over \n Your score is : " + board.getScore());
            board.restartGame();
            board.setToSleep(false);
            mainTheme = new Themes("src/assets/maintheme.wav");
            mainTheme.loopMusic();
        }

        if (event.getPropertyName().equals("Pause")) {
            board.Pause();
            mainTheme.stopMusic();
            JOptionPane.showMessageDialog(this, "Pause");
            mainTheme.loopMusic();
            board.Resume();
        }
        if(event.getPropertyName().equals("Cancel")){

            // Close the current game
            this.dispose();
            board.setToSleep(true);
            mainTheme.stopMusic();

            // Launch the new game
            TetrisFrame newGame = new TetrisFrame();
            newGame.setVisible(true);
        }

    }
}
