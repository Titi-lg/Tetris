package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Themes {

    private Clip clip;

    public Themes(String musicFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFile));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playMusic() {
        clip.start();
    }

    public void stopMusic() {
        clip.stop();
    }

    public void loopMusic() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}