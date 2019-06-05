import java.util.*;

public class Ship {
    private static int ships;
    private static ArrayList<String> remainingShips = new ArrayList<String>();
    public static int[][] shipPosition = new int[BattleShips.numRows][BattleShips.numCols];

    public Ship() {
        Ship.ships = 0;
    }

    public Ship(int ships) {
        Ship.ships = ships;
    }

    // Getter
    public static int getNumberOfShip() {
        return Ship.ships;
    }
    public static ArrayList<String> getRemainingShips() {
        return Ship.remainingShips;
    }
    public static int[][] getShipPosition() {
        return Ship.shipPosition;
    }

    // Setter
    public static void setNumberOfShip(int newNumberOfShip) {
        Ship.ships = newNumberOfShip;
    }
    public static void setRemainingShips(ArrayList<String> newRemainingShips) {
        Ship.remainingShips = newRemainingShips;
    }
    public static void setShipPosition(int[][] newShipPosition) {
        Ship.shipPosition = newShipPosition;
    }

    public static void deployShips(int numRows, int numCols){
        System.out.println("\nDeploying ships");

        int i = 1;
        while (i <= Ship.ships) {
            boolean result = randomPlacingShip(i, numRows, numCols);
            if(result == true){
                i++;
            }
        }
    }

    public static boolean randomPlacingShip(int shipNo, int numRows, int numCols){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(numRows);
        int y = randomGenerator.nextInt(numCols);
        int length = randomGenerator.nextInt(3) + 3;
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (BattleShips.grid[x][y] == "#") && (Ship.shipPosition[x][y] == 0) && (Trap.trapsPosition[x][y] == 0) && (Potion.potionsPosition[x][y] == 0))
        {
            for(int l = 0; l < length; l++){
                Ship.shipPosition[x][y+l] = shipNo;
                BattleShips.grid[x][y+l] = "c";
            }
            Integer obj = new Integer(shipNo);
            Ship.remainingShips.add(obj.toString());

            return true;
        }else{
            return false;
        }
    }

    public static void removeShip(int shipNo){
        Integer obj = new Integer(shipNo);
        for(int i = 0; i < Ship.shipPosition.length; i++) {
            for (int j = 0; j < Ship.shipPosition[i].length; j++) {
                if (Ship.shipPosition[i][j] == shipNo){
                    BattleShips.grid[i][j] = "!";
                    Ship.shipPosition[i][j] = 0;
                }
                else{
                }
            }
        }
        Ship.remainingShips.remove(obj.toString());
    }

    public static void revealShip(int shipNo){
        Integer obj = new Integer(shipNo);
        for(int i = 0; i < Ship.shipPosition.length; i++) {
            for (int j = 0; j < Ship.shipPosition[i].length; j++) {
                if (Ship.shipPosition[i][j] == shipNo){
                    BattleShips.grid[i][j] = "0";
                }
                else{
                }
            }
        }
    }
}