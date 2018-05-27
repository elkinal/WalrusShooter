/**
 * Created by alxye on 27-May-18.
 */
public class Reload extends Thread {
    public void run() {
        Sound.playSound(Content.magChange);
        Content.shootingLock = true;
        try {
            Thread.sleep(500);
            Sound.playSound(Content.magChange);
            for (int i = 0; i < 20; i++) {
                Content.reticleSizeX += Content.reticleSizeX * 0.012;
                Content.reticleSizeY += Content.reticleSizeY * 0.012;
                Thread.sleep(24);
            }
            for (int i = 0; i < 20; i++) {
                Content.reticleSizeX -= Content.reticleSizeX * 0.012;
                Content.reticleSizeY -= Content.reticleSizeY * 0.012;
                Thread.sleep(24);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Content.shootingLock = false;
        Content.ammunition = 5;
        Content.displayedAmmunition = "|||||";
    }
}
