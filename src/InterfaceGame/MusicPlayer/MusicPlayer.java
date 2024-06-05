package InterfaceGame.MusicPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;

    public MusicPlayer(String filePath) {
        load(filePath);
        play();
    }

    public void load(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Chơi lại nhạc liên tục
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void stopImmediately() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
