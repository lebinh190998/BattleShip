import java.util.*;

public class Ship {
    private int shipNo;
    private int shipLength;
    private int x;
    private int y;
    private static int ships;
    private static ArrayList<String> remainingShips = new ArrayList<String>();
    private static int[][] shipPosition = new int[BattleShips.numRows][BattleShips.numCols];

    public Ship() {
        this.shipNo = 0;
        this.shipLength = 0;
        this.x = 0;
        this.y = 0;
    }

    public Ship(int shipNo, int shipLength, int x, int y) {
        this.shipNo = shipNo;
        this.shipLength = shipLength;
        this.x = x;
        this.y = y;
    }

    // Getter
    public int getShipNo() {
        return shipNo;
    }
    public int getShipLength() {
        return shipLength;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static int getNumberOfShip() {
        return ships;
    }
    public static ArrayList<String> getRemainingShips() {
        return remainingShips;
    }
    public static int[][] getShipPosition() {
        return shipPosition;
    }

    // Setter
    public void setShipNo(int newShipNo) {
        this.shipNo = newShipNo;
    }
    public void setShipLength(int newShipLength) {
        this.shipLength = newShipLength;
    }
    public void setX(int newX) {
        this.x = newX;
    }
    public void setY(int newY) {
        this.y = newY;
    }
    public static void setNumberOfShip(int newNumberOfShip) {
        ships = newNumberOfShip;
    }
    public static void setRemainingShips(ArrayList<String> newRemainingShips) {
        remainingShips = newRemainingShips;
    }
    public static void setShipPosition(int[][] newShipPosition) {
        shipPosition = newShipPosition;
    }

    public static void deployShips(int numRows, int numCols){
        System.out.println("\nDeploying ships");

        int i = 1;
        while (i <= ships) {
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
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (BattleShips.grid[x][y] == "#") && (BattleShips.trapsPosition[x][y] == 0) && (BattleShips.potionsPosition[x][y] == 0))
        {
            for(int l = 0; l < length; l++){
                shipPosition[x][y+l] = shipNo;
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
        for(int i = 0; i < shipPosition.length; i++) {
            for (int j = 0; j < shipPosition[i].length; j++) {
                if (shipPosition[i][j] == shipNo){
                    BattleShips.grid[i][j] = "!";
                    shipPosition[i][j] = 0;
                    
                }
                else{
                }
            }
        }
        Ship.remainingShips.remove(obj.toString());
    }

    public static void revealShip(int shipNo){
        Integer obj = new Integer(shipNo);
        for(int i = 0; i < shipPosition.length; i++) {
            for (int j = 0; j < shipPosition[i].length; j++) {
                if (shipPosition[i][j] == shipNo){
                    BattleShips.grid[i][j] = "0";
                }
                else{
                }
            }
        }
    }
}