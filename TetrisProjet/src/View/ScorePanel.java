package View;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScorePanel extends JPanel implements PropertyChangeListener {
    private int score;
    private JLabel scoreLabel;

    public ScorePanel(int score) {
        this.score = score;
        scoreLabel = new JLabel("Score : " + score);
        this.add(scoreLabel);


    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score : " + score);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getPropertyName().equals("Score")) {
            updateScore((int) e.getNewValue());
        }
    }
}
