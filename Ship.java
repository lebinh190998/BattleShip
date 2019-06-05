import java.util.*;

public class Ship {
    private static int ships;
    private static ArrayList<String> remainingShips = new ArrayList<String>();
    public static int[][] shipPosition;

    public Ship() {
        Ship.ships = 0;
        Ship.shipPosition = new int[Grid.numRows][Grid.numCols];
        Ship.remainingShips = new ArrayList<String>();
    }

    public Ship(int ships) {
        Ship.ships = ships;
        Ship.shipPosition = new int[Grid.numRows][Grid.numCols];
        Ship.remainingShips = new ArrayList<String>();
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

    public static void deployShips(){
        System.out.println("\nDeploying ships");

        int i = 1;
        while (i <= Ship.ships) {
            boolean result = randomPlacingShip(i);
            if(result == true){
                i++;
            }
        }
    }

    public static boolean randomPlacingShip(int shipNo){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(Grid.numRows);
        int y = randomGenerator.nextInt(Grid.numCols);
        int length = randomGenerator.nextInt(3) + 3;
        if((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols-length) && (Grid.grid[x][y] == "#") && (Ship.shipPosition[x][y] == 0) && (Trap.trapsPosition[x][y] == 0) && (Potion.potionsPosition[x][y] == 0))
        {
            for(int l = 0; l < length; l++){
                Ship.shipPosition[x][y+l] = shipNo;
                Grid.grid[x][y+l] = "c";
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
                    Grid.grid[i][j] = "!";
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
                    Grid.grid[i][j] = "0";
                }
                else{
                }
            }
        }
    }
}