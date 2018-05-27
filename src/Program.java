import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alxye on 26-May-18.
 */
public class Program extends JPanel {
    public static int screenHeight = 755;
    public static int screenWidth = 1280;
    public Program() {


        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setTitle("Walrus Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Content());
        frame.setResizable(true);
        frame.setSize(screenWidth,screenHeight);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //making sure that the cursor is blank
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
    }

    public static void main(String[] args) {
        new Program();
    }
}
