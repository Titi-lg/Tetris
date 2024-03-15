package View;

import Model.Block;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TetrisFrame extends JFrame implements Observer{
    private Block block;
    private TetrisCanva tetrisCanva;
    public static int hauteur=800, largeur=1000;
    private JPanel pJeu, pScore, pNextPiece, pOptions,pRight,pLeft,pSouth;

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

    @Override
    public void update(Observable o, Object arg) {tetrisCanva.repaint();
    }


    public TetrisFrame() {
        Game game = new Game();
        tetrisCanva = new TetrisCanva(game.getTetris());
        game.addObserver(this);
        game.play();

        setTitle("Tetris");
        setSize(largeur+200, hauteur+200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pJeu = new JPanel(new BorderLayout());
        pRight = new JPanel(new GridLayout(2,1));
        pScore = new JPanel();
        pNextPiece = new JPanel();
        pOptions = new JPanel();
        pLeft = new JPanel();
        pSouth = new JPanel();
        pRight.add(pNextPiece);
        pRight.add(pScore);
        pJeu.add(tetrisCanva, BorderLayout.CENTER);
        add(pJeu, BorderLayout.CENTER);
        add(pRight, BorderLayout.EAST);
        add(pLeft, BorderLayout.WEST);
        add(pSouth, BorderLayout.SOUTH);
        add(pOptions, BorderLayout.NORTH);

        pack();
        setVisible(true);
        new Thread(game::play).start();
        game.run();

    }


}
