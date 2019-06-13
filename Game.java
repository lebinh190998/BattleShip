import java.util.*;

public class Game {
    private static int ships;
    private static ArrayList<Ship> allShips;

    private static int traps;
    private static ArrayList<Trap> allTraps;

    private static int potions;
    private static ArrayList<Potion> allPotions;
    

    public Game() {
        Game.ships = 0;
        Game.allShips = new ArrayList<Ship>();

        Game.traps = 0;
        Game.allTraps = new ArrayList<Trap>();

        Game.potions = 0;
        Game.allPotions = new ArrayList<Potion>();
    }

    // Getter
    public static int getNumberOfShip() {
        return Game.ships;
    }
    public static ArrayList<Ship> getAllShips() {
        return Game.allShips;
    }

    public static int getNumberOfTrap() {
        return Game.traps;
    }
    public static ArrayList<Trap> getAllTraps() {
        return Game.allTraps;
    }

    public static int getNumberOfPotion() {
        return Game.potions;
    }
    public static ArrayList<Potion> getAllPotions() {
        return Game.allPotions;
    }

    // Setter
    public static void setNumberOfShip(int newNumberOfShip) {
        Game.ships = newNumberOfShip;
    }
    public static void setAllShips(ArrayList<Ship> newAllShips) {
        Game.allShips = newAllShips;
    }

    public static void setNumberOfTrap(int newNumberOfTrap) {
        Game.traps = newNumberOfTrap;
    }
    public static void setAllTraps(ArrayList<Trap> newAllTraps) {
        Game.allTraps = newAllTraps;
    }

    public static void setNumberOfPotion(int newNumberOfPotion) {
        Game.potions = newNumberOfPotion;
    }
    public static void setAllPotions(ArrayList<Potion> newAllPotions) {
        Game.allPotions = newAllPotions;
    }

    
    public static void deployShips(Ocean ocean){
        System.out.println("\nDeploying ships");

        int shipNo = 1;
        while (shipNo <= Game.ships) {
            Ship ship = Ship.randomGenerateShip(ocean);
            Game.placingShip(ship, ocean);
            Game.allShips.add(ship);
            shipNo++;
        }
    }
    
    public static void placingShip(Ship ship, Ocean ocean){
        int x = ship.getX();
        ArrayList<Integer> ys = ship.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();
        for(int y : ys){
            displayGrid[x][y] = "c";
            checkingGrid[x][y] = "c";
        }
        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }

    
    public static void removeShip(Ship ship, Ocean ocean){
        int x = ship.getX();
        ArrayList<Integer> shipY = ship.getY();
        String[][] checkingGrid = ocean.getCheckingGrid();
        String[][] displayGrid = ocean.getDisplayGrid();

        ship.setIsSunk(true);
        for(int y : shipY) {
            displayGrid[x][y] = "!";
            checkingGrid[x][y] = "!";
        }

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }
    
    public static void revealShip(Ocean ocean){
        for(Ship ship : Game.allShips){
            boolean isSunk = ship.getIsSunk();
            boolean isReveal = ship.getIsReveal();
            if(isSunk == false && isReveal == false){
                ship.setIsReveal(true);
                int x = ship.getX();
                ArrayList<Integer> shipY = ship.getY();
                String[][] checkingGrid = ocean.getCheckingGrid();
                String[][] displayGrid = ocean.getDisplayGrid();
                for(int y : shipY) {
                    displayGrid[x][y] = "0";
                    checkingGrid[x][y] = "0";
                }
                ocean.setDisplayGrid(displayGrid);
                ocean.setheckingGrid(checkingGrid);
                break;
            }
        }
    }
    

    
    public static void deployTraps(Ocean ocean){
        System.out.println("\nDeploying traps");

        int trapNo = 1;
        while(trapNo <= Game.traps){
            Trap trap = Trap.randomGenerateTrap(ocean);
            Game.placingTrap(trap, ocean);
            Game.allTraps.add(trap);
            trapNo++;
        }
    }

    public static void placingTrap(Trap trap, Ocean ocean){
        int x = trap.getX();
        int y = trap.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        displayGrid[x][y] = "t";
        checkingGrid[x][y] = "t";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
        
    }
    
    public static void removeTrap(Trap trap, Ocean ocean){
        int x = trap.getX();
        int y = trap.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        trap.setIsRemove(true);
        displayGrid[x][y] = " ";
        checkingGrid[x][y] = " ";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }
    
    public static void revealTrap(Ocean ocean){
        for(Trap trap : Game.allTraps){
            boolean isRemove = trap.getIsRemove();
            boolean isReveal = trap.getIsReveal();
            if(isRemove== false && isReveal == false){
                trap.setIsReveal(true);
                int x = trap.getX();
                int y = trap.getY();
                String[][] checkingGrid = ocean.getCheckingGrid();
                String[][] displayGrid = ocean.getDisplayGrid();

                displayGrid[x][y] = "/";
                checkingGrid[x][y] = "/";

                ocean.setDisplayGrid(displayGrid);
                ocean.setheckingGrid(checkingGrid);
                
                break;
            }
        }
    }

    public static void deployPotions(Ocean ocean){
        System.out.println("\nDeploying potions");

        
        int potionNo = 1;
        while(potionNo <= Game.potions){
            Potion potion = Potion.randomGeneratePotion(ocean);
            Game.placingPotion(potion, ocean);
            Game.allPotions.add(potion);
            potionNo++;
        }
    }

    public static void placingPotion(Potion potion, Ocean ocean){
        int x = potion.getX();
        int y = potion.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        displayGrid[x][y] = "p";
        checkingGrid[x][y] = "p";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }

    public static void removePotion(Potion potion, Ocean ocean){
        int x = potion.getX();
        int y = potion.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        displayGrid[x][y] = " ";
        checkingGrid[x][y] = " ";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }
}