import java.util.*;

public class Ship {
    private int shipNo;
    private int shipLength;
    private int x;
    private int y;
    private static int computerShips;
    private static ArrayList<String> remainingComputerShips = new ArrayList<String>();
    private static int[][] computerShipPosition = new int[20][60];

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
        return computerShips;
    }
    public static ArrayList<String> getRemainingShips() {
        return remainingComputerShips;
    }
    public static int[][] getShipPosition() {
        return computerShipPosition;
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
        computerShips = newNumberOfShip;
    }
    public static void setRemainingShips(ArrayList<String> newRemainingShips) {
        remainingComputerShips = newRemainingShips;
    }
    public static void setShipPosition(int[][] newShipPosition) {
        computerShipPosition = newShipPosition;
    }

    public static void deployComputerShips(int numRows, int numCols){
        System.out.println("\nDeploying ships");

        int i = 1;
        while (i <= computerShips) {
            boolean result = randomPlacingShip(i, numRows, numCols);
            if(result == true){
                i++;
            }
        }
        //printOceanMap();
    }

    public static boolean randomPlacingShip(int shipNo, int numRows, int numCols){
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(numRows);
        int y = randomGenerator.nextInt(numCols);
        int length = randomGenerator.nextInt(3) + 3;
        if((x >= 0 && x < numRows) && (y >= 0 && y < numCols-length) && (BattleShips.grid[x][y] == "#") && (BattleShips.trapsPosition[x][y] == 0) && (BattleShips.potionsPosition[x][y] == 0))
        {
            for(int l = 0; l < length; l++){
                computerShipPosition[x][y+l] = shipNo;
                BattleShips.grid[x][y+l] = "c";
            }
            Integer obj = new Integer(shipNo);
            Ship.remainingComputerShips.add(obj.toString());

            return true;
        }else{
            return false;
        }
    }
}