import java.util.*;

public class Grid {
    public static int numRows;
    public static int numCols;
    public static String[][] grid;

    private static int ships;
    private static ArrayList<Ship> remainingShips = new ArrayList<Ship>();
    public static int[][] shipPosition;
    

    public Grid(int numRows, int numCols) {
        Grid.numRows = numRows;
        Grid.numCols = numCols;
        Grid.grid = new String[numRows][numCols];
        Grid.ships = 0;
        Grid.shipPosition = new int[numRows][numCols];
        Grid.remainingShips = new ArrayList<Ship>();
    }

    // Getter
    public static int getRows() {
        return numRows;
    }
    public static int getCols() {
        return numCols;
    }
    public static String[][] getGrid() {
        return grid;
    }

    public static int getNumberOfShip() {
        return Grid.ships;
    }
    public static ArrayList<Ship> getRemainingShips() {
        return Grid.remainingShips;
    }
    public static int[][] getShipPosition() {
        return Grid.shipPosition;
    }

    // Setter
    public static void setRows(int newRows) {
        Grid.numRows = newRows;
    }
    public static void setCols(int newCols) {
        Grid.numCols = newCols;
    }
    public static void setGrid(String[][] newGrid) {
        Grid.grid = newGrid;
    }

    public static void setNumberOfShip(int newNumberOfShip) {
        Grid.ships = newNumberOfShip;
    }
    public static void setRemainingShips(ArrayList<Ship> newRemainingShips) {
        Grid.remainingShips = newRemainingShips;
    }
    public static void setShipPosition(int[][] newShipPosition) {
        Grid.shipPosition = newShipPosition;
    }

    public static void createOceanMap(){
        //Header section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < Grid.grid.length; i++) {
            for (int j = 0; j < Grid.grid[i].length; j++) {
                Grid.grid[i][j] = "#";
                if (j == 0){
                    if((i+1) < 10){
                        System.out.print(" " + (i+1) + "|" + Grid.grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.grid[i][j]);
                    }
                }
                else if (j == Grid.grid[i].length - 1){
                    System.out.print(Grid.grid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= Grid.numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printOceanMap(){
        System.out.println();

        //First section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= Grid.numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < Grid.grid.length; i++) {
            for (int j = 0; j < Grid.grid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + Grid.grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.grid[i][j]);
                    }
                }
                else if (j == Grid.grid[i].length - 1){
                    System.out.print(Grid.grid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= Grid.numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void deployShips(){
        System.out.println("\nDeploying ships");

        randomGenerateShips();

        for (Ship ship : Grid.remainingShips) {
            Grid.placingShip(ship);
        }
    }

    public static void randomGenerateShips(){
        Random randomGenerator = new Random();
        int shipNo = 1;
        while (shipNo <= Grid.ships) {
            int x = randomGenerator.nextInt(Grid.numRows);
            int y = randomGenerator.nextInt(Grid.numCols);
            int length = randomGenerator.nextInt(3) + 3;
            if((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols-length) && (Grid.grid[x][y] == "#") && (Grid.shipPosition[x][y] == 0) && (Trap.trapsPosition[x][y] == 0) && (Potion.potionsPosition[x][y] == 0))
            {
                Ship ship = new Ship(shipNo, x, y, length);
                Grid.remainingShips.add(ship);

                shipNo++;
            }
        }
    }

    public static void placingShip(Ship ship){
        for(int l = 0; l < ship.length; l++){
            Grid.shipPosition[ship.x][ship.y+l] = ship.shipNo;
            Grid.grid[ship.x][ship.y+l] = "c";
        }
    }

    
    public static void removeShip(Ship ship){
        for(int i = 0; i < Grid.shipPosition.length; i++) {
            for (int j = 0; j < Grid.shipPosition[i].length; j++) {
                if (Grid.shipPosition[i][j] == ship.shipNo){
                    Grid.grid[i][j] = "!";
                    Grid.shipPosition[i][j] = 0;
                }
                else{
                }
            }
        }
    }

    public static void revealShip(Ship ship){
        for(int i = 0; i < Grid.shipPosition.length; i++) {
            for (int j = 0; j < Grid.shipPosition[i].length; j++) {
                if (Grid.shipPosition[i][j] == ship.shipNo){
                    Grid.grid[i][j] = "0";
                }
                else{
                }
            }
        }
    }
}