import java.awt.*;
import java.util.Random;

/**
 * Created by alxye on 27-May-18.
 */
public class Recoil extends Thread {
    Random random = new Random();
    int offsetX = random.nextInt((10 - -10) + 1) + -10;
    public void run() {
        Content.currentReticle = Content.reticleShooting;
        for (int i = 0; i < 10; i++) {
            //make sure that the reticle moves sideways
            Content.robot.mouseMove((int)(MouseInfo.getPointerInfo().getLocation().getX() + offsetX),
                    (int)(MouseInfo.getPointerInfo().getLocation().getY() - 10));
            //make sure that the reticle increases in size
            Content.reticleSizeX += Content.reticleSizeX * 0.012;
            Content.reticleSizeY += Content.reticleSizeY * 0.012;
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Content.currentReticle = Content.reticle;
        Content.reticleSizeX = 1000;
        Content.reticleSizeY = 1500;
    }
}
