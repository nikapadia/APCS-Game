import java.util.ArrayList;

public class EnemyController {
    private static int playerX, playerY, enemyX, enemyY;

    public static void boomerAction(int c){
        String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
        enemyX = Integer.parseInt(enemyLoc[0]);
        enemyY = Integer.parseInt(enemyLoc[1]);
        getClosestPlayer();

        if(Math.abs(enemyX-playerX)<=112 && Math.abs(enemyY-playerY)<=112){
            boomerAttack();
        } else {
            if(enemyY-playerY>0){GameRunner.getE(c).setLocation(enemyX+112, enemyY);}//move right
            else if(enemyY-playerY<0){GameRunner.getE(c).setLocation(enemyX-112, enemyY);}//move left
            else if(enemyX-playerX>0){GameRunner.getE(c).setLocation(enemyX, enemyY+112);}//move down
            else {GameRunner.getE(c).setLocation(enemyX, enemyY-112);}//move up
        }

        // System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
   //move to closest player's y pos then x pos then blow up when 1 square away
    }
    public static void boomerAttack(){}

    public static void copAction(int c){
        String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
        enemyX = Integer.parseInt(enemyLoc[0]);
        enemyY = Integer.parseInt(enemyLoc[1]);
        getClosestPlayer();

        if(Math.abs(enemyX-playerX)<=112*3 && Math.abs(enemyY-playerY)<=112*3){
            if(enemyY-playerY<0){GameRunner.getE(c).setLocation(enemyX+112, enemyY);}//move right
            else if(enemyY-playerY>0){GameRunner.getE(c).setLocation(enemyX-112, enemyY);}//move left
            else if(enemyX-playerX<0){GameRunner.getE(c).setLocation(enemyX, enemyY+112);}//move down
            else {GameRunner.getE(c).setLocation(enemyX, enemyY-112);}//move up
        } else {
            copAttack();
        }
   //stay x grids away from player and shoot
    }
    public static void copAttack(){}

    public static void robotAction(){robotAttack();}
    public static void robotAttack(){}

    public static void getClosestPlayer(){
        ArrayList<Integer> pLocs = new ArrayList<Integer>();
        String[] playerLocations = GameController.getPLocations();
        for(int i=0; i<playerLocations.length;i++){
            String[] tempArr = playerLocations[i].split(" ");            
            int x = Integer.parseInt(tempArr[0]);
            int y = Integer.parseInt(tempArr[1]);

            pLocs.add(x);
            pLocs.add(y);
        }

        int yMin = 0;
        int yIndex = 0;
        for(int i=1; i<8;i+=2){
            if(Math.abs(pLocs.get(i)-enemyY) < yMin){
                yMin = Math.abs(pLocs.get(i)-enemyY);
                yIndex = i;
            }
        }
        int xMin = 0;
        int xIndex = 0;
        for(int i=0; i<8;i+=2){
            if(Math.abs(pLocs.get(i)-enemyX) < xMin){
                xMin = Math.abs(pLocs.get(i)-enemyX);
                xIndex = i;
            }
        }

        if(pLocs.get(xIndex)<pLocs.get(yIndex)){
            playerX = pLocs.get(xIndex);
            playerY = pLocs.get(xIndex+1);
        }
        else if(pLocs.get(yIndex)<pLocs.get(xIndex)){
            playerX = pLocs.get(yIndex-1);
            playerY = pLocs.get(yIndex);
        }
        else if(pLocs.get(yIndex).equals(pLocs.get(xIndex))){
            playerX = pLocs.get(xIndex);
            playerY = pLocs.get(xIndex+1);
        }
    }
}
