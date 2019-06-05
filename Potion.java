import java.util.*;

public class Potion {
    private static int potions;
    private static ArrayList<String> remainingPotions = new ArrayList<String>();
    public static int[][] potionsPosition = new int[20][60];

    public Potion() {
        Potion.potions = 0;
    }

    public Potion(int potions) {
        Potion.potions = potions;
    }

    // Getter
    public static int getNumberOfPotion() {
        return Potion.potions;
    }
    public static ArrayList<String> getRemainingPotions() {
        return Potion.remainingPotions;
    }
    public static int[][] getPotionPosition() {
        return Potion.potionsPosition;
    }

    // Setter
    public static void setNumberOfPotion(int newNumberOfPotion) {
        Potion.potions = newNumberOfPotion;
    }
    public static void setRemainingPotions(ArrayList<String> newRemainingPotions) {
        Potion.remainingPotions = newRemainingPotions;
    }
    public static void setPotionsPosition(int[][] newPotionsPosition) {
        Potion.potionsPosition = newPotionsPosition;
    }

    public static void deployPotions(int numRows, int numCols){
        System.out.println("\nDeploying potions");

        int i = 1;
        while (i <= Potion.potions) {
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
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (BattleShips.grid[x][y] == "#") && (Ship.shipPosition[x][y] == 0) && (Trap.trapsPosition[x][y] == 0) && (Potion.potionsPosition[x][y] == 0))
        {
            Potion.potionsPosition[x][y] = potionNo;
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
        for(int i = 0; i < Potion.potionsPosition.length; i++) {
            for (int j = 0; j < Potion.potionsPosition[i].length; j++) {
                if (Potion.potionsPosition[i][j] == potionNo){
                    BattleShips.grid[i][j] = " ";
                    Potion.potionsPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        Potion.remainingPotions.remove(obj.toString());
    }
}