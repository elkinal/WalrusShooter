import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

/**
 * Created by alxye on 27-May-18.
 */
public class Sound {
    public static void playSound(File file) {
        InputStream in;
        try {
            in = new FileInputStream(file);
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
