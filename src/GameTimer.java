/**
 * Created by alxye on 26-May-18.
 */
public class GameTimer extends Thread {
    public static int count = 60;
    public void run() {
        while (count > 0) {
            count--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
