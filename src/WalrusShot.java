import java.util.concurrent.TimeUnit;

/**
 * Created by alxye on 26-May-18.
 */
public class WalrusShot extends Thread {

    private int Xoffset = 0;
    private int YOffset = 0;
    public void run() {
        Sound.playSound(Content.explosionSound);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                Content.currentSkin = Content.spriteSheet.getSubimage(Xoffset, YOffset, 255, 255);
                Xoffset += 256;
/*                if (Xoffset > 750) {
                    Xoffset = 0;
                }*/

                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Xoffset = 0;
            YOffset += 245;
        }
        Content.resetWalrusPosition();
        Content.currentSkin = Content.defaultCharacter;
        Content.walrusAim = false;
        Content.score++;
    }

}
