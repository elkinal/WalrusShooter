import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.media.jfxmedia.AudioClip;
import com.sun.media.jfxmedia.Media;
import com.sun.media.jfxmedia.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by alxye on 26-May-18.
 */
public class Content extends JPanel implements ActionListener {
    private Timer t;
    private static int walrusSize = 150;
    private static Random random = new Random();
    private static Thread walrusShot = new WalrusShot();
    private static Thread gameTimer = new GameTimer();
    private static Thread recoil = new Recoil();
    private static Thread walrusMove = new WalrusMove();
    private static Timer animationTimer;
    public static BufferedImage spriteSheet;
    public static BufferedImage currentSkin;
    public static BufferedImage defaultCharacter;
    public static BufferedImage reticle;
    public static BufferedImage currentReticle;
    public static BufferedImage reticleShooting;
    private static BufferedImage backgroundImage;
    public static boolean walrusAim = false;
    public static int score = 0;

    public static int walrusXVelocity = 0;
    public static int walrusYVelocity = 0;

    public static int reticleSizeX = 1000;
    public static int reticleSizeY = 1500;

    public static Robot robot;

    public static File explosionSound = new File("C:\\Users\\alxye\\Desktop\\tiles\\420.wav");
    public static File endSound = new File("C:\\Users\\alxye\\Desktop\\tiles\\yee.wav");
    public static File gun = new File("C:\\Users\\alxye\\Desktop\\tiles\\gun.wav");


    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static int randomMinX = 0;
    private static int randomMaxX = (int) (screenSize.getWidth() - 150);
    public static int walrusX = random.nextInt((randomMaxX - randomMinX) + 1) + randomMinX;

    private static int randomMinY = 0;
    private static int randomMaxY = (int) (screenSize.getHeight() - 150);
    public static int walrusY = random.nextInt((randomMaxY - randomMinY) + 1) + randomMinY;

    public Content() {
        t = new Timer(5, this);
        t.start();
        super.setDoubleBuffered(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        try {
            spriteSheet = ImageIO.read(new File("C:\\Users\\alxye\\Desktop\\tiles\\explosions.png"));
            defaultCharacter = ImageIO.read(new File("C:\\Users\\alxye\\Desktop\\tiles\\player.png"));
            reticle = ImageIO.read(new File("C:\\Users\\alxye\\Desktop\\tiles\\reticle.png"));
            reticleShooting = ImageIO.read(new File("C:\\Users\\alxye\\Desktop\\tiles\\reticleShooting.png"));
            backgroundImage = ImageIO.read(new File("C:\\Users\\alxye\\Desktop\\tiles\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //starts that timer
        gameTimer.start();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }


        //set current skin to the default walrus skin;
        currentSkin = defaultCharacter;
        currentReticle = reticle;
// Create an AudioStream object from the input stream.
// Use the static class member "player" from class AudioPlayer to play
// clip.


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //walrus has been shot
                if(!e.isMetaDown()) {
                    Sound.playSound(gun);
                    if (walrusAim) {
                        walrusShot = new WalrusShot();
                        walrusShot.start();
                        walrusAim = false;
                    }
                    recoil = new Recoil();
                    recoil.start();
                }
            }
        });
    }
    public static void resetWalrusPosition() {
         walrusX = random.nextInt((randomMaxX - randomMinX) + 1) + randomMinX;
         walrusY = random.nextInt((randomMaxY - randomMinY) + 1) + randomMinY;
         walrusXVelocity = random.nextInt((5 - 1) + 1) + 1;
         walrusYVelocity = random.nextInt((5 - 1) + 1) + 1;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.drawRect(walrusX, walrusY, walrusSize, walrusSize);

        //draw background for the JFrame
        graphics2D.drawImage(backgroundImage, 0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight(), null);
        graphics2D.drawImage(currentSkin, walrusX, walrusY, walrusSize, walrusSize, null);
        graphics2D.drawImage(currentReticle,
                (int)MouseInfo.getPointerInfo().getLocation().getX() - 447,
                (int)MouseInfo.getPointerInfo().getLocation().getY() - 740,
                reticleSizeX, reticleSizeY, null);

        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Sans-Serif", Font.PLAIN, 40);
        Font fontLarge = new Font("Sans-Serif", Font.PLAIN, 100);
        graphics2D.setFont(font);
        graphics2D.drawString(String.valueOf(GameTimer.count), 10, 40);
        graphics2D.drawString(String.valueOf(score), screenSize.width - 100, 40);

        if(GameTimer.count == 0) {
            Sound.playSound(endSound);
            GameTimer.count = -1;
            //destroy all the threads in the current process
        }
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        int currentMouseLocationX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int currentMouseLocationY = (int) MouseInfo.getPointerInfo().getLocation().getY();
//        System.out.println(walrusX + "," + walrusY + "    " + currentMouseLocationX + "," + currentMouseLocationY);
        //no idea how this works. This is a stilt
        walrusMove = new WalrusMove();
        walrusMove.start();
        if(currentMouseLocationX >= walrusX && currentMouseLocationX <= walrusX + walrusSize &&
                currentMouseLocationY >= walrusY+35 && currentMouseLocationY <= walrusY + walrusSize+30) {
                //mouse over walrus
            walrusAim = true;
        }

    }


}
