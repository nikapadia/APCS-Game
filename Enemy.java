import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

// This code is basically the same as Player. They are separate classes because when we made the Player class, we didn't forsee to make a superclass that Player and Enemy would inherit from.
// Most comments here are unique to this class specifically. 
public class Enemy extends JPanel implements ActionListener { 

    private int HP, speed, acc;
    private String c;
    private String loc;
    private boolean alive;

    public Enemy(int HP, String c, int speed, int acc, boolean alive) {
        Timer timer = new Timer(100, this);
        timer.setInitialDelay(0);
        timer.start();
        this.HP = HP;
        this.c = c;
        this.speed = speed;
        this.acc = acc;
        this.alive = alive;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void setLocation(String str) {
        GameRunner.setLocation(str, this);
    }

    public int getHP(){return HP;}           // returns HP of Enemy object
    public void setHP(int HP){this.HP = HP;} // sets HP of Enemy object
    public void removeHP(int x){HP-=x;}      // removes x HP of Enemy object
    public void addHP(int x){HP+=x;}         // adds x HP of Enemy object

    public String getEClass(){return c;}     // returns status of Enemy object
    public int getAcc(){return acc;}         // returns accuracy
    public int getSpeed(){return speed;}     // returns speed
    public boolean getAlive(){return alive;}
    public void setAlive(boolean b){
        if(!b){this.setVisible(false);}
        alive = b;}
    public void setLoc(int x, int y){loc = x + " " + y;}
    public String getLoc(){return loc;}
    public String getPos() {
        String[] arr = loc.split(" ");
        int x = (Integer.parseInt(arr[0]))/112;
        int y = (Integer.parseInt(arr[1]))/112;
        return GameController.letterParser(x) + y;

    } // returns pos of enemy on the grid Ex. A1
    public void newLevel() {
        this.setVisible(true);
        switch(getEClass()) {
            case "COP": setHP(35); break;
            case "COP2": setHP(35); break;
            case "ROBOT": setHP(50); break;
            case "ROBOT2": setHP(50); break;
            case "BOOMER": setHP(10);  break;
        }
        setAlive(true);
    }

    public void paintComponent(Graphics g) { // Cop and Robot have 2 separate cases each in order to make accessing them in other classes properly easier. 
        super.paintComponent(g);
        switch(c) {
            case "COP":
                g = animateCOP(g);
                break;
            case "COP2":
                g = animateCOP(g);
                break;
            case "ROBOT": 
                g = animateROBOT(g);
                break;
            case "ROBOT2":
                g =animateROBOT(g);
                break;
            case "BOOMER":
                g = animateBOOMER(g);
                break;
        }
    }
    public Graphics animateCOP(Graphics g) {
        Image ii = new ImageIcon(this.getClass().getResource("images/Cop_Idle.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public Graphics animateROBOT(Graphics g) {
        Image ii = new ImageIcon(this.getClass().getResource("images/Robot_Idle.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public Graphics animateBOOMER(Graphics g) {
        Image ii = new ImageIcon(this.getClass().getResource("images/Boomer_Idle.gif")).getImage();
        g.drawImage(ii, 0, 0, null);
        return g;
    }
    public void actionPerformed(ActionEvent e) { // Needed in order for the character animations to work properly.
        repaint();
        revalidate();
    }
}