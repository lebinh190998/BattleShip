import java.util.*;

public class Trap {
    private static int traps;
    private static ArrayList<String> remainingTraps = new ArrayList<String>();
    public static int[][] trapsPosition = new int[20][60];

    public Trap() {
        Trap.traps = 0;
        Trap.trapsPosition = new int[Grid.numRows][Grid.numCols];
        Trap.remainingTraps = new ArrayList<String>();
    }

    public Trap(int traps) {
        Trap.traps = traps;
        Trap.trapsPosition = new int[Grid.numRows][Grid.numCols];
        Trap.remainingTraps = new ArrayList<String>();
    }

    // Getter
    public static int getNumberOfTrap() {
        return Trap.traps;
    }
    public static ArrayList<String> getRemainingTraps() {
        return Trap.remainingTraps;
    }
    public static int[][] getTrapPosition() {
        return Trap.trapsPosition;
    }

    // Setter
    public static void setNumberOfTrap(int newNumberOfTrap) {
        Trap.traps = newNumberOfTrap;
    }
    public static void setRemainingTraps(ArrayList<String> newRemainingTraps) {
        Trap.remainingTraps = newRemainingTraps;
    }
    public static void setTrapsPosition(int[][] newTrapPosition) {
        Trap.trapsPosition = newTrapPosition;
    }

    public static void deployTraps(int numRows, int numCols){
        System.out.println("\nDeploying traps");

        int i = 1;
        while (i <= Trap.traps) {
            boolean result = randomPlacingTrap(i, numRows, numCols);
            if(result == true){
                i++;
            }
        }
    }

    public static boolean randomPlacingTrap(int trapNo, int numRows, int numCols){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(Grid.numRows);
        int y = randomGenerator.nextInt(Grid.numCols);
        if((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols) && (Grid.grid[x][y] == "#") && (Ship.shipPosition[x][y] == 0) && (Trap.trapsPosition[x][y] == 0) && (Potion.potionsPosition[x][y] == 0))
        {
            Trap.trapsPosition[x][y] = trapNo;
            Grid.grid[x][y] = "t";
            Integer obj = new Integer(trapNo);
            Trap.remainingTraps.add(obj.toString());

            return true;
        }else{
            return false;
        }
    }

    public static void removeTrap(int trapNo){
        Integer obj = new Integer(trapNo);
        for(int i = 0; i < Trap.trapsPosition.length; i++) {
            for (int j = 0; j < Trap.trapsPosition[i].length; j++) {
                if (Trap.trapsPosition[i][j] == trapNo){
                    Grid.grid[i][j] = " ";
                    Trap.trapsPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        Trap.remainingTraps.remove(obj.toString());
    }

    public static void revealTrap(int trapNo){
        Integer obj = new Integer(trapNo);
        for(int i = 0; i < Trap.trapsPosition.length; i++) {
            for (int j = 0; j < Trap.trapsPosition[i].length; j++) {
                if (Trap.trapsPosition[i][j] == trapNo){
                    Grid.grid[i][j] = "/";
                }
                else{
                }
            }
        }
    }
}