/**
 * Created by alxye on 27-May-18.
 */
public class WalrusMove extends Thread {
    public void run() {
        while(true) {
            for (int i = 0; i < 50; i++) {
                Content.walrusX += 20;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 100; i++) {
                Content.walrusX -= 20;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
