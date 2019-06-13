import java.util.*;

public class Grid {
    public static int numRows;
    public static int numCols;
    public static String[][] displayGrid;
    public static String[][] checkingGrid;

    private static int ships;
    private static ArrayList<Ship> remainingShips;
    public static int[][] shipPosition;

    private static int traps;
    private static ArrayList<Trap> remainingTraps;
    public static int[][] trapsPosition;

    private static int potions;
    private static ArrayList<Potion> remainingPotions;
    public static int[][] potionsPosition;
    

    public Grid(int numRows, int numCols) {
        Grid.numRows = numRows;
        Grid.numCols = numCols;
        Grid.displayGrid = new String[numRows][numCols];
        Grid.checkingGrid = new String[numRows][numCols];

        Grid.ships = 0;
        Grid.shipPosition = new int[numRows][numCols];
        Grid.remainingShips = new ArrayList<Ship>();

        Grid.traps = 0;
        Grid.trapsPosition = new int[numRows][numCols];
        Grid.remainingTraps = new ArrayList<Trap>();

        Grid.potions = 0;
        Grid.potionsPosition = new int[numRows][numCols];
        Grid.remainingPotions = new ArrayList<Potion>();
    }

    // Getter
    public static int getRows() {
        return numRows;
    }
    public static int getCols() {
        return numCols;
    }
    public static String[][] getDisplayGrid() {
        return displayGrid;
    }
    public static String[][] getCheckingGrid() {
        return checkingGrid;
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

    public static int getNumberOfTrap() {
        return Grid.traps;
    }
    public static ArrayList<Trap> getRemainingTraps() {
        return Grid.remainingTraps;
    }
    public static int[][] getTrapPosition() {
        return Grid.trapsPosition;
    }

    public static int getNumberOfPotion() {
        return Grid.potions;
    }
    public static ArrayList<Potion> getRemainingPotions() {
        return Grid.remainingPotions;
    }
    public static int[][] getPotionPosition() {
        return Grid.potionsPosition;
    }

    // Setter
    public static void setRows(int newRows) {
        Grid.numRows = newRows;
    }
    public static void setCols(int newCols) {
        Grid.numCols = newCols;
    }
    public static void setDisplayGrid(String[][] newGrid) {
        Grid.displayGrid = newGrid;
    }
    public static void setheckingGrid(String[][] newGrid) {
        Grid.checkingGrid = newGrid;
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

    public static void setNumberOfTrap(int newNumberOfTrap) {
        Grid.traps = newNumberOfTrap;
    }
    public static void setRemainingTraps(ArrayList<Trap> newRemainingTraps) {
        Grid.remainingTraps = newRemainingTraps;
    }
    public static void setTrapsPosition(int[][] newTrapPosition) {
        Grid.trapsPosition = newTrapPosition;
    }

    public static void setNumberOfPotion(int newNumberOfPotion) {
        Grid.potions = newNumberOfPotion;
    }
    public static void setRemainingPotions(ArrayList<Potion> newRemainingPotions) {
        Grid.remainingPotions = newRemainingPotions;
    }
    public static void setPotionsPosition(int[][] newPotionsPosition) {
        Grid.potionsPosition = newPotionsPosition;
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
        for(int i = 0; i < Grid.displayGrid.length; i++) {
            for (int j = 0; j < Grid.displayGrid[i].length; j++) {
                Grid.displayGrid[i][j] = "#";
                if (j == 0){
                    if((i+1) < 10){
                        System.out.print(" " + (i+1) + "|" + Grid.displayGrid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.displayGrid[i][j]);
                    }
                }
                else if (j == Grid.displayGrid[i].length - 1){
                    System.out.print(Grid.displayGrid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.displayGrid[i][j]);
                }
            }
            System.out.println();
        }
        //Create checking Map
        for(int i = 0; i < Grid.checkingGrid.length; i++) {
            for (int j = 0; j < Grid.checkingGrid[i].length; j++) {
                Grid.checkingGrid[i][j] = "#";
            }
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
        for(int i = 0; i < Grid.displayGrid.length; i++) {
            for (int j = 0; j < Grid.displayGrid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + Grid.displayGrid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + Grid.displayGrid[i][j]);
                    }
                }
                else if (j == Grid.displayGrid[i].length - 1){
                    System.out.print(Grid.displayGrid[i][j] + "|");
                }
                else{
                    System.out.print(Grid.displayGrid[i][j]);
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

        int shipNo = 1;
        while (shipNo <= Grid.ships) {
            Ship ship = Ship.randomGenerateShip(Grid.checkingGrid, Grid.numRows, Grid.numCols);
            Grid.placingShip(ship);
            Grid.remainingShips.add(ship);
            shipNo++;
        }
    }

    public static void placingShip(Ship ship){
        int x = ship.getX();
        ArrayList<Integer> ys = ship.getY();
        for(int y : ys){
            Grid.displayGrid[x][y] = "0";
            Grid.checkingGrid[x][y] = "c";
        }
    }

    
    public static void removeShip(Ship ship){
        int x = ship.getX();
        ship.setIsSunk(true);
        ArrayList<Integer> shipY = ship.getY();
        for(int y : shipY) {
            Grid.displayGrid[x][y] = "!";
            Grid.checkingGrid[x][y] = "!";
        }
    }

    public static void revealShip(Ship ship){
        int x = ship.getX();
        ArrayList<Integer> shipY = ship.getY();
        for(int y : shipY) {
            Grid.displayGrid[x][y] = "0";
            Grid.checkingGrid[x][y] = "0";
        }
    }

    
    public static void deployTraps(){
        System.out.println("\nDeploying traps");

        int trapNo = 1;
        while(trapNo <= Grid.traps){
            Trap trap = Trap.randomGenerateTrap(Grid.checkingGrid, Grid.numRows, Grid.numCols);
            Grid.placingTrap(trap);
            Grid.remainingTraps.add(trap);
            trapNo++;
        }
    }

    public static void placingTrap(Trap trap){
        int x = trap.getX();
        int y = trap.getY();

        Grid.displayGrid[x][y] = "t";
        Grid.checkingGrid[x][y] = "t";
        
    }
    
    public static void removeTrap(Trap trap){
        int x = trap.getX();
        int y = trap.getY();

        Grid.displayGrid[x][y] = " ";
        Grid.checkingGrid[x][y] = " ";
        
        //Grid.remainingTraps.remove(trap);
    }
    /*
    public static void revealTrap(Trap trap){
        for(int i = 0; i < Grid.trapsPosition.length; i++) {
            for (int j = 0; j < Grid.trapsPosition[i].length; j++) {
                if (Grid.trapsPosition[i][j] == trap.trapNo){
                    Grid.displayGrid[i][j] = "/";
                }
                else{
                }
            }
        }
    }
    */

    public static void deployPotions(int numRows, int numCols){
        System.out.println("\nDeploying potions");

        
        int potionNo = 1;
        while(potionNo <= Grid.potions){
            Potion potion = randomGeneratePotion(potionNo);
            Grid.placingPotion(potion);
            potionNo++;
        }
    }

    public static Potion randomGeneratePotion(int potionNo){
        Random randomGenerator = new Random();
        Potion potion;
        while (true) {
            int x = randomGenerator.nextInt(Grid.numRows);
            int y = randomGenerator.nextInt(Grid.numCols);
            if((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols) && (Grid.displayGrid[x][y] == "#") && (Grid.shipPosition[x][y] == 0) && (Grid.trapsPosition[x][y] == 0) && (Grid.potionsPosition[x][y] == 0))
            {
                potion = new Potion(potionNo, x, y);
                Grid.remainingPotions.add(potion);

                break;
            }
        }
        return potion;
    }

    public static void placingPotion(Potion potion){
        Grid.potionsPosition[potion.x][potion.y] = potion.potionNo;
        Grid.checkingGrid[potion.x][potion.y] = "p";
    }

    public static void revealPotion(Potion potion){
        for(int i = 0; i < Grid.potionsPosition.length; i++) {
            for (int j = 0; j < Grid.potionsPosition[i].length; j++) {
                if (Grid.potionsPosition[i][j] == potion.potionNo){
                    Grid.displayGrid[i][j] = " ";
                    Grid.potionsPosition[i][j] = 0;
                }
                else{
                }
            }
        }
    }
}