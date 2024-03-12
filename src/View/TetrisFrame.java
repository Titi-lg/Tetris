package View;

import Model.Block;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class TetrisFrame extends JFrame implements Observer {
    private Block block;
    private int hauteur=600, largeur=800;
    private JPanel pJeu, pScore, pNextPiece, pOptions,pRight;
    @Override
    public void update(java.util.Observable o, Object arg) {
            block.affiche();
    }


    public TetrisFrame() {
        setTitle("Tetris");
        setSize(largeur, hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        pJeu = new JPanel();
        pRight = new JPanel(new GridLayout(2,1));
        pScore = new JPanel();
        pNextPiece = new JPanel();
        pRight.add(pNextPiece);
        pRight.add(pScore);
        add(pJeu, BorderLayout.CENTER);
        add(pRight, BorderLayout.EAST);
    }


}
