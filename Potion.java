import java.util.*;

public class Potion {
    private int potionNo;
    private int x;
    private int y;
    private static int potions;
    private static ArrayList<String> remainingPotions = new ArrayList<String>();
    private static int[][] potionsPosition = new int[20][60];

    public Potion() {
        this.potionNo = 0;
        this.x = 0;
        this.y = 0;
    }

    public Potion(int potionNo, int x, int y) {
        this.potionNo = potionNo;
        this.x = x;
        this.y = y;
    }

    // Getter
    public int getPotionNo() {
        return potionNo;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static int getNumberOfPotion() {
        return potions;
    }
    public static ArrayList<String> getRemainingPotions() {
        return remainingPotions;
    }
    public static int[][] getPotionPosition() {
        return potionsPosition;
    }

    // Setter
    public void setPotionNo(int newPotionNo) {
        this.potionNo = newPotionNo;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public static void setNumberOfPotion(int newNumberOfPotion) {
        potions = newNumberOfPotion;
    }
    public static void setRemainingPotions(ArrayList<String> newRemainingPotions) {
        remainingPotions = newRemainingPotions;
    }
    public static void setPotionsPosition(int[][] newPotionsPosition) {
        potionsPosition = newPotionsPosition;
    }

    public static void deployPotions(int numRows, int numCols){
        System.out.println("\nDeploying potions");

        int i = 1;
        while (i <= potions) {
            boolean result = randomPlacingPotion(i, numRows, numCols);
            if(result == true){
                i++;
            }
        }
    }

    public static boolean randomPlacingPotion(int potionNo, int numRows, int numCols){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(numRows);
        int y = randomGenerator.nextInt(numCols);
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (BattleShips.grid[x][y] == "#") && (BattleShips.shipPosition[x][y] == 0) && (BattleShips.trapsPosition[x][y] == 0))
        {
            potionsPosition[x][y] = potionNo;
            BattleShips.grid[x][y] = "p";
            Integer obj = new Integer(potionNo);
            Potion.remainingPotions.add(obj.toString());

            return true;
        }else{
            return false;
        }
    }

    public static void revealPotion(int potionNo){
        Integer obj = new Integer(potionNo);
        for(int i = 0; i < potionsPosition.length; i++) {
            for (int j = 0; j < potionsPosition[i].length; j++) {
                if (potionsPosition[i][j] == potionNo){
                    BattleShips.grid[i][j] = " ";
                    potionsPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        Potion.remainingPotions.remove(obj.toString());
    }
}