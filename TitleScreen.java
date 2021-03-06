import java.awt.*;
// import java.awt.Graphics.*;
import javax.swing.*;
import java.awt.event.*;

public class TitleScreen {
    
    private static Image img = new ImageIcon("images/Titlescreen.png").getImage(); // Crates an image from the images folder.

    public static void main(String[] args) {
        JFrame f = new JFrame("Cyberbreak - Titlescreen");
        JPanel p = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        };
        f.setSize(1920, 1080);
        f.setUndecorated(true);
        f.add(p);
        f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
                // if(ke.getKeyCode() == KeyEvent.VK_LEFT){System.out.println("");};
            }
            public void keyPressed(KeyEvent ke) {
                // Checks to see if spacebar pressed. If true, then the titlescreen will hide and the tutorial will show.
                if(ke.getKeyCode() == KeyEvent.VK_SPACE){Tutorial.main(null);f.setVisible(false);}; 
                

            }
            public void keyReleased(KeyEvent ke) {}
        });
        f.setVisible(true);
    }
}
