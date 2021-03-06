// import java.util.*;

public class Level {
    private static int currentLevel;
    // private static boolean gameOver;
    
    public Level(int c) {
        currentLevel = c;
    }
    public static String[] getLevel1Cords() {
        String[] ret = new String[16]; // Yes this is inefficient, but it was the only way to get the cords of all of the walls in each level.
        ret[0] = "448 148";
        ret[1] = "112 260";
        ret[2] = "224 260";
        ret[3] = "448 260";
        ret[4] = "784 372";
        ret[5] = "896 372";
        ret[6] = "1008 372";
        ret[7] = "224 484";
        ret[8] = "448 484";
        ret[9] = "1008 484";
        ret[10] = "224 596";
        ret[11] = "448 596";
        ret[12] = "1008 596";
        ret[13] = "1232 596";
        ret[14] = "224 708";
        ret[15] = "1008 708";
        return ret;
    }
    public static String[] getLevel1Pos() {
        String[] ret = new String[16];  
        for(int i = 0; i < 16; i++) { // Size of ret and conditional need to be the same as number of walls
            String[] arr = getLevel1Cords()[i].split(" "); // wall
            int oldx = (Integer.parseInt(arr[0]))/112;
            int oldy = (Integer.parseInt(arr[1]))/112;
            ret[i] = (oldx-1) + " " + (oldy-1);
        }
        return ret;
        /* for(int i = 0; i < 4; i++) { // Leave if needed for later
            String[] arr = GameRunner.getP(i).getLoc().split(" ");
            int oldx = (Integer.parseInt(arr[0]))/112;
            int oldy = (Integer.parseInt(arr[1]))/112;
            ret2d[oldx-1][oldy-1] = Level.getPlayer(i);
            ret[i+16] = (oldx-1) + " " + (oldy-1);
        }
        for(int i = 0; i < 3; i++) {
            String[] arr = GameRunner.getE(i).getLoc().split(" ");
            int oldx = (Integer.parseInt(arr[0]))/112;
            int oldy = (Integer.parseInt(arr[1]))/112;
            ret2d[oldx-1][oldy-1] = Level.getEnemy(i);
            ret[i+20] = (oldx-1) + " " + (oldy-1);
        } */
    }
    public static String[] getLevel2Cords() {
        String[] ret = new String[20]; // Yes this is inefficient, but it works.
        ret[0] = "336 148";
        ret[1] = "448 148";
        ret[2] = "896 148";
        ret[3] = "1008 148";
        ret[4] = "560 260";
        ret[5] = "784 260";
        ret[6] = "112 372";
        ret[7] = "224 372";
        ret[8] = "336 372";
        ret[9] = "1008 372";
        ret[10] = "1120 372";
        ret[11] = "1232 372";
        ret[12] = "336 484";
        ret[13] = "1008 484";
        ret[14] = "112 708";
        ret[15] = "224 708";
        ret[16] = "336 708";
        ret[17] = "1008 708";
        ret[18] = "1120 708";
        ret[19] = "1232 708";
        return ret;
    }
    public static String[] getLevel2Pos() {
        String[] ret = new String[20];  
        for(int i = 0; i < 20; i++) { // Size of ret and conditional need to be the same as number of walls
            String[] arr = getLevel2Cords()[i].split(" "); // wall
            int oldx = (Integer.parseInt(arr[0]))/112;
            int oldy = (Integer.parseInt(arr[1]))/112;
            ret[i] = (oldx-1) + " " + (oldy-1);
        }
        return ret;
    }
    public static String[] getLevel3Cords() {
        String[] ret = new String[11]; // Yes this is inefficient, but it works.
        ret[0] = "224 148";
        ret[1] = "448 260";
        ret[2] = "560 260";
        ret[3] = "896 260";
        ret[4] = "224 372";
        ret[5] = "1120 372";
        ret[6] = "1120 596";
        ret[7] = "224 708";
        ret[8] = "336 708";
        ret[9] = "784 708";
        ret[10] = "784 820";
        return ret;
    }
    public static String[] getLevel3Pos() {
        String[] ret = new String[11];  
        for(int i = 0; i < 11; i++) { // Size of ret and conditional need to be the same as number of walls
            String[] arr = getLevel3Cords()[i].split(" "); // wall
            int oldx = (Integer.parseInt(arr[0]))/112;
            int oldy = (Integer.parseInt(arr[1]))/112;
            ret[i] = (oldx-1) + " " + (oldy-1);
        }
        return ret;
    }
    
    public static int getCurrentLevel() {return currentLevel;}
    public static void setCurrentLevel(int i){currentLevel = i;}

    public static void printThing() { // this is for testing dont touch.
        for(int i = 0; i < Level.getLevel1Pos().length; i++) {
            System.out.print(Level.getLevel1Pos()[i] + ", ");
            //for (int k = 0; k < Level.getLevel1Pos()[0].length; k++) {
            //}
            //System.out.println();
        }
    } 
}


/* 
null wall null null null null null
null wall null wall wall wall null
null null null null null null null
wall wall null wall wall null null
null null null null null null null
null null null null null null null
null null wall null null null null
null null wall null null null null
null null wall wall wall wall null
null null null null null null null
null null null null wall null null
*/