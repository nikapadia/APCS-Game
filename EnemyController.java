import java.util.ArrayList;

public class EnemyController {
    private static int playerX, playerY, enemyX, enemyY;
    private static boolean CopFail, RobFail;
    private static String enemy;
    private static int damageCop, damageRob;

    public static void boomerAction(int c){ // Controls the behavior of the boomer enemy. Basically it always goes to the nearest player.
        if(GameRunner.getE(c).getAlive()) {
            String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
            enemyX = Integer.parseInt(enemyLoc[0]);
            enemyY = Integer.parseInt(enemyLoc[1]);
            getClosestPlayer();

            if(Math.abs(enemyX-playerX)<=112 && Math.abs(enemyY-playerY)<=112){
                boomerAttack();
            } else {
                if(enemyY-playerY>0){GameController.setLocationE(c, enemyX, (enemyY-112));}
                else if(enemyY-playerY<0){GameController.setLocationE(c, enemyX, enemyY+112);}//move left
                if(enemyX-playerX>0){GameController.setLocationE(c, enemyX-112, enemyY);}//move down
                else if (enemyX-playerX<0){GameController.setLocationE(c, enemyX+112, enemyY);}//move up
            }
            enemyLoc = GameRunner.getE(c).getLoc().split(" ");
            enemyX = Integer.parseInt(enemyLoc[0]);
            enemyY = Integer.parseInt(enemyLoc[1]);
        }

        // System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
   //move to closest player's y pos then x pos then blow up when 1 square away
    }
    public static void copAction(int c){ // Controls the behavior of the boomer enemy. In short, it always runs away from the nearest player.
        if(GameRunner.getE(c).getAlive()) {
            String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
            enemyX = Integer.parseInt(enemyLoc[0]);
            enemyY = Integer.parseInt(enemyLoc[1]);
            getClosestPlayer();

            if(Math.abs(enemyX-playerX)<=112*3 || Math.abs(enemyY-playerY)<=112*3){
                if(enemyY-playerY<0){GameController.setLocationE(c, enemyX, enemyY+112);}//move right
                else if(enemyY-playerY>0){GameController.setLocationE(c, enemyX, enemyY-112);}//move left
                else if(enemyY-playerY == 0){GameController.setLocationE(c, enemyX, enemyY-112);}

                if(enemyX-playerX<0){GameController.setLocationE(c, enemyX-112, enemyY);}//move down
                else if(enemyX-playerX>0){GameController.setLocationE(c, enemyX+112, enemyY);}//move up
                else if(enemyX-playerX == 0){GameController.setLocationE(c, enemyX+112, enemyY);}
            }
            enemyLoc = GameRunner.getE(c).getLoc().split(" ");
            enemyX = Integer.parseInt(enemyLoc[0]);
            enemyY = Integer.parseInt(enemyLoc[1]);
            copAttack();
        }
   //stay x grids away from player and shoot
    }
    public static void robotAction(int c){ // Doesn't move so doesn't need move logic
        if(GameRunner.getE(c).getAlive()) {
            getClosestPlayer();
            String[] enemyLoc = GameRunner.getE(c).getLoc().split(" ");
            enemyX = Integer.parseInt(enemyLoc[0]);
            enemyY = Integer.parseInt(enemyLoc[1]);
            robotAttack();
        }
    }

    static void boomerAttack() { // Attack for Boomer. 
        console.insertMsg("The boomer at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        int c = GameController.playerAt(playerX, playerY);
        GameRunner.getP(c).setHP(GameRunner.getP(c).getHP()-100); // Takes current HP of Player object it is attacking and takes 100 HP away from it, instantly killing it
        GameRunner.getP(GameController.playerAt(playerX, playerY)).setAlive(false);
        GameController.hasFailed();
    }
    static void copAttack() { // Attack for Cop objects
        console.insertMsg("The cop at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        if(LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
            int rand = (int)(Math.random()*100+1);
            if(rand>50){ // Randomization for attack. 50/50 chance of the enemy attacking you and hitting you.
                CopFail = false;
                enemy = "cop";
                damageCop = rand/10;
                //GameController.hasFailed();
            } else {
                CopFail = true;
                enemy = "cop";
                damageCop = 0;
            }
        } else {
            CopFail = true;
            enemy = "cop";
            damageCop = 0;
        }
        EnemyController.failureState();
    }
    static void robotAttack() { // Attack for robot objects. Just a simple LOS check and then damage rolling
        console.insertMsg("The robot at position " + GameController.letterParser(enemyX/112) + (enemyY-36)/112 + " is attacking a player at " + GameController.letterParser(playerX/112) + (playerY-36)/112);
        if(LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
            int rand = (int)(Math.random()*100+1);
            if(rand>50){ // Randomization for attack. 50/50 chance of the enemy attacking you and hitting you.
                RobFail = false;
                enemy = "robot";
                damageRob = (rand/10)+5;
                //GameController.hasFailed();
            } else {
                RobFail = true;
                enemy = "robot";
                damageRob = 0;
            }   
        } else {
            RobFail = true;
            enemy = "robot";
            damageRob = 0;
        }
        EnemyController.failureState();
    }
    
    private static void failureState(){ // Output if the enemy either fails or succeeds at hitting a Player object.
        getClosestPlayer();
        Player p = GameRunner.getP(GameController.playerAt(playerX, playerY));
        if(!LineOfSight.canAttack(enemyX, enemyY, playerX, playerY)){
            console.insertMsg("The " + enemy + " has failed to hit you because of an obstruction");
            return;
        } 
        if(enemy.equals("robot")){
            if(RobFail){
                console.insertMsg("The robot missed!");
            } else {
                console.insertMsg("The robot has hit you! You have taken " + damageRob + " damage!");
                p.setHP(p.getHP()-damageRob);
                System.out.println(p.getHP());
                if(p.getHP() <= 0){p.setAlive(false);}
            } 
            if(enemy.equals("robot")){
                if(RobFail){
                    console.insertMsg("The robot missed!");
                } else {
                    console.insertMsg("The robot has hit you! You have taken " + damageRob + " damage!");
                    p.setHP(p.getHP()-damageRob);
                    if(p.getHP() <= 0){p.setAlive(false);}
                } 
                RobFail = true;
                damageRob = 0;
            } else {
                if(CopFail){
                    console.insertMsg("The cop missed!");
                } else {
                    console.insertMsg("The cop has hit you! You have taken " + damageCop + " damage!");
                    p.setHP(p.getHP()-damageCop);
                    if(p.getHP() <= 0){p.setAlive(false);}
                }
                CopFail = true;
                damageCop = 0;
            }
        }
    }
    public static void getClosestPlayer(){ // Gets the closest player from the Enemy object it is being run on. 
        ArrayList<String> losArr1 = new ArrayList<String>();
        String[] playerLocations = GameController.getPLocations();
        int min = Integer.MAX_VALUE;
        int nPlayerX = 0;
        int nPlayerY = 0;
        int minCharacter = 5;
        for(int i=0; i<playerLocations.length;i++){
            if(!GameRunner.getP(i).getAlive()){ i++; }
                String[] tempArr = playerLocations[i].split(" ");            
                System.out.println(i + " " + playerLocations[i]);
                int x = Integer.parseInt(tempArr[0]);
                int y = Integer.parseInt(tempArr[1]);
                losArr1 = LineOfSight.drawLine(enemyX/112, (enemyY-36)/112, x/112, (y-36)/112);
                if(losArr1.size() < min) {
                    min = losArr1.size();
                    minCharacter = i;
                    nPlayerX = x;
                    nPlayerY = y;
                }
            }
            playerX = nPlayerX;
            playerY = nPlayerY;
        }
        // Puts all of the player locations into an arraylist. Each index in the arraylist is EITHER an X or Y value. 
        //System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
        // int yMin = Integer.MAX_VALUE; // I don't know what this does LMAO.
        // int yIndex = 0;
        // for(int i=1; i<8;i+=2){
        //     if(Math.abs(pLocs.get(i)-enemyY) < yMin){
        //         yMin = Math.abs(pLocs.get(i)-enemyY);
        //         yIndex = i;
        //     }
        // }

        // int xMin = Integer.MAX_VALUE;
        // int xIndex = 0;
        // for(int i=0; i<8;i+=2){
        //     if(Math.abs(pLocs.get(i)-enemyX) < xMin){
        //         xMin = Math.abs(pLocs.get(i)-enemyX);
        //         xIndex = i;
        //     }
        // }

        // if(pLocs.get(xIndex)<pLocs.get(yIndex)){
        //     playerX = pLocs.get(xIndex);
        //     playerY = pLocs.get(xIndex+1);
        // }
        // else if(pLocs.get(yIndex)<pLocs.get(xIndex)){
        //     playerX = pLocs.get(yIndex-1);
        //     playerY = pLocs.get(yIndex);
        // }
        // else if(pLocs.get(yIndex).equals(pLocs.get(xIndex))){
        //     playerX = pLocs.get(xIndex);
        //     playerY = pLocs.get(xIndex+1);
        // }

        // System.out.println(playerX + " " + playerY + " " + enemyX + " " + enemyY);
    public static void attack(){
        copAction(0);
        copAction(1);
        robotAction(2);
        if(Level.getCurrentLevel()==2 || Level.getCurrentLevel()==3) {robotAction(3);}
        if(Level.getCurrentLevel()==3){boomerAction(4);}
    }
}