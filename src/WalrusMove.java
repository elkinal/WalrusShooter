/**
 * Created by alxye on 27-May-18.
 */
public class WalrusMove extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            Content.walrusX += 2;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            Content.walrusX -= 2;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
