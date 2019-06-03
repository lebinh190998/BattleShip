import java.util.*;

public class Trap {
    private int trapNo;
    private int x;
    private int y;
    private static int traps;
    private static ArrayList<String> remainingTraps = new ArrayList<String>();
    private static int[][] trapsPosition = new int[20][60];

    public Trap() {
        this.trapNo = 0;
        this.x = 0;
        this.y = 0;
    }

    public Trap(int trapNo, int x, int y) {
        this.trapNo = trapNo;
        this.x = x;
        this.y = y;
    }

    // Getter
    public int getTrapNo() {
        return trapNo;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static int getNumberOfTrap() {
        return traps;
    }
    public static ArrayList<String> getRemainingTraps() {
        return getRemainingTraps();
    }
    public static int[][] getTrapPosition() {
        return trapsPosition;
    }

    // Setter
    public void setTrapNo(int newTrapNo) {
        this.trapNo = newTrapNo;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public static void setNumberOfTrap(int newNumberOfTrap) {
        traps = newNumberOfTrap;
    }
    public static void setRemainingTraps(ArrayList<String> newRemainingTraps) {
        remainingTraps = newRemainingTraps;
    }
    public static void setTrapPosition(int[][] newTrapPosition) {
        trapsPosition = newTrapPosition;
    }

    public static void deployTraps(int numRows, int numCols){
        System.out.println("\nDeploying traps");

        int i = 1;
        while (i <= traps) {
            boolean result = randomPlacingTrap(i, numRows, numCols);
            if(result == true){
                i++;
            }
        }
    }

    public static boolean randomPlacingTrap(int trapNo, int numRows, int numCols){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(numRows);
        int y = randomGenerator.nextInt(numCols);
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (BattleShips.grid[x][y] == "#") && (BattleShips.computerShipPosition[x][y] == 0) && (BattleShips.potionsPosition[x][y] == 0))
        {
            trapsPosition[x][y] = trapNo;
            BattleShips.grid[x][y] = "t";
            Integer obj = new Integer(trapNo);
            Trap.remainingTraps.add(obj.toString());

            return true;
        }else{
            return false;
        }
    }
}