import java.util.*;

public class Game {
    private int ships;
    private ArrayList<Ship> allShips;

    private int traps;
    private ArrayList<Trap> allTraps;

    private int potions;
    private ArrayList<Potion> allPotions;
    

    public Game() {
        this.ships = 0;
        this.allShips = new ArrayList<Ship>();

        this.traps = 0;
        this.allTraps = new ArrayList<Trap>();

        this.potions = 0;
        this.allPotions = new ArrayList<Potion>();
    }

    // Getter
    public int getShips() {
        return this.ships;
    }
    public ArrayList<Ship> getAllShips() {
        return this.allShips;
    }

    public int getTraps() {
        return this.traps;
    }
    public ArrayList<Trap> getAllTraps() {
        return this.allTraps;
    }

    public int getPotions() {
        return this.potions;
    }
    public ArrayList<Potion> getAllPotions() {
        return this.allPotions;
    }

    // Setter
    public void setShips(int newNumberOfShip) {
        this.ships = newNumberOfShip;
    }
    public void setAllShips(ArrayList<Ship> newAllShips) {
        this.allShips = newAllShips;
    }

    public void setTraps(int newNumberOfTrap) {
        this.traps = newNumberOfTrap;
    }
    public void setAllTraps(ArrayList<Trap> newAllTraps) {
        this.allTraps = newAllTraps;
    }

    public void setPotions(int newNumberOfPotion) {
        this.potions = newNumberOfPotion;
    }
    public void setAllPotions(ArrayList<Potion> newAllPotions) {
        this.allPotions = newAllPotions;
    }

    
    public void deployShips(Game game, Ocean ocean){
        System.out.println("\nDeploying ships");

        int shipNo = 1;
        int ships = game.getShips();
        while (shipNo <= ships) {
            Ship ship = Ship.randomGenerateShip(ocean);
            game.placingShip(ship, ocean);
            addToShipsList(ship, game);
            shipNo++;
        }
    }

    private void addToShipsList(Ship ship, Game game){
        ArrayList<Ship> allShips = game.getAllShips();
        allShips.add(ship);
        game.setAllShips(allShips);
    }
    
    private void placingShip(Ship ship, Ocean ocean){
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

    
    public void removeShip(Ship ship, Ocean ocean){
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
    
    public void revealShip(Ocean ocean, Game game){
        ArrayList<Ship> allShips = game.getAllShips();
        for(Ship ship : allShips){
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
    

    
    public void deployTraps(Game game, Ocean ocean){
        System.out.println("\nDeploying traps");

        int trapNo = 1;
        int traps = game.getTraps();
        while(trapNo <= traps){
            Trap trap = Trap.randomGenerateTrap(ocean);
            game.placingTrap(trap, ocean);
            addToTrapsList(trap, game);
            trapNo++;
        }
    }

    private void addToTrapsList(Trap trap, Game game){
        ArrayList<Trap> allTraps = game.getAllTraps();
        allTraps.add(trap);
        game.setAllTraps(allTraps);
    }

    public void placingTrap(Trap trap, Ocean ocean){
        int x = trap.getX();
        int y = trap.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        displayGrid[x][y] = "t";
        checkingGrid[x][y] = "t";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
        
    }
    
    public void removeTrap(Trap trap, Ocean ocean){
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
    
    public void revealTrap(Ocean ocean, Game game){
        ArrayList<Trap> allTraps = game.getAllTraps();
        for(Trap trap : allTraps){
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

    public void deployPotions(Game game, Ocean ocean){
        System.out.println("\nDeploying potions");

        
        int potionNo = 1;
        int potions = game.getPotions();
        while(potionNo <= potions){
            Potion potion = Potion.randomGeneratePotion(ocean);
            game.placingPotion(potion, ocean);
            addToPotionsList(potion, game);
            potionNo++;
        }
    }

    private void addToPotionsList(Potion potion, Game game){
        ArrayList<Potion> allPotions = game.getAllPotions();
        allPotions.add(potion);
        game.setAllPotions(allPotions);
    }

    public void placingPotion(Potion potion, Ocean ocean){
        int x = potion.getX();
        int y = potion.getY();
        String[][] displayGrid = ocean.getDisplayGrid();
        String[][] checkingGrid = ocean.getDisplayGrid();

        displayGrid[x][y] = "p";
        checkingGrid[x][y] = "p";

        ocean.setDisplayGrid(displayGrid);
        ocean.setheckingGrid(checkingGrid);
    }

    public void removePotion(Potion potion, Ocean ocean){
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