import java.util.*;

public class BattleShips {
    public static int playerDestroyedShips = 0;
    public static int playerLife = 15;

    public static void main(String[] args){
        Game game = new Game();

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Set game difficulty
        setDifficulty();

        //Step 2 – Create the ocean map
        Ocean ocean = Ocean.createOceanMap(20, 60);
        Ocean.printOceanMap(ocean);

        //Step 3 - Deploy ships
        Game.deployShips(ocean);

        //Step 4 - Deploy traps
        Game.deployTraps(ocean);

        //Step 5 - Deploy potions
        Game.deployPotions(ocean);

        //Step 4 Battle
        do {
            Battle(ocean);
        }while(BattleShips.playerDestroyedShips < 5 && BattleShips.playerLife > 0);

        //Step 5 - Game over
        gameOver();
    }


    public static void setDifficulty(){
        int level = -1;
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Choose your level of difficulty: \n1.Beginner \n2.Intermediate \n3.Advance");
            level = input.nextInt();
    
            if(level == 1){
                Game.setNumberOfShip(80);
                Game.setNumberOfTrap(10);
                Game.setNumberOfPotion(18);
            }
            else if(level == 2){
                Game.setNumberOfShip(50);
                Game.setNumberOfTrap(20);
                Game.setNumberOfPotion(18);
            }
            else if(level == 3){
                Game.setNumberOfShip(20);
                Game.setNumberOfTrap(30);
                Game.setNumberOfPotion(18);
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }
    
    public static void Battle(Ocean ocean){
        playGame(ocean);
        Ocean.printOceanMap(ocean);
        System.out.println();
        System.out.println("Ships found: " + BattleShips.playerDestroyedShips);
        System.out.println("Life left: " + BattleShips.playerLife);
        System.out.println();
    }
    


    public static void playGame(Ocean ocean){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        int numRows = ocean.getRows();
        int numCols = ocean.getCols();

        do {
            Scanner input = new Scanner(System.in);
            String[][] checkingGrid = ocean.getCheckingGrid();
            String[][] displayGrid = ocean.getDisplayGrid();
            
            System.out.print("Enter row coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter column coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols))
            {
                if (displayGrid[x][y] != " " && checkingGrid[x][y] == "c")
                {
                    BattleShips.hitShipAction(x, y, ocean);
                }
                else if (displayGrid[x][y] != " " && checkingGrid[x][y] == "t")
                {
                    BattleShips.hitTrapAction(x, y, ocean);
                }
                else if (displayGrid[x][y] != " " && checkingGrid[x][y] == "p") 
                {
                    BattleShips.hitPotionAction(x, y, ocean);
                }
                else if (displayGrid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    displayGrid[x][y] = " ";
                }
                else if (displayGrid[x][y] == " " || displayGrid[x][y] == "!") 
                {
                    System.out.println("You have chosen that position");
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't chose position outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));
    }

    public static void hitShipAction(int x, int y, Ocean ocean){
        ArrayList<Ship> allShips = Game.getAllShips();
        for(Ship ship : allShips){
            int shipX = ship.getX();
            ArrayList<Integer> shipY = ship.getY();
            if(x == shipX && shipY.contains(y)){
                Game.removeShip(ship, ocean);
            }
        }
        System.out.println("Boom! You sunk the ship!");
        ++BattleShips.playerDestroyedShips;
    }

    public static void hitTrapAction(int x, int y, Ocean ocean){
        ArrayList<Trap> allTraps = Game.getAllTraps();
        for(Trap trap : allTraps){
            int trapX = trap.getX();
            int trapY = trap.getY();
            if(x == trapX && y == trapY){
                if(trap instanceof HighTrap){
                    System.out.println("Oh no, you hit in a High Danger Trap :(");
                    HighTrap highTrap = (HighTrap)trap;
                    BattleShips.playerLife -= highTrap.getDamage();
                }else if (trap instanceof LowTrap){
                    System.out.println("Oh no, you hit in a Low Danger Trap :(");
                    LowTrap lowTrap = (LowTrap)trap;
                    BattleShips.playerLife -= lowTrap.getDamage();
                }
                Game.removeTrap(trap,ocean);
            }
        }
    }

    public static void hitPotionAction(int x, int y, Ocean ocean){
        ArrayList<Potion> allPotions = Game.getAllPotions();

        for(Potion potion : allPotions){
            int potionX = potion.getX();
            int potionY = potion.getY();
            if(x == potionX && y == potionY){
                if(potion instanceof LifePotion){
                    System.out.println("Life Saver Potion, your health increase by 1");
                    LifePotion lifePotion = (LifePotion)potion;
                    BattleShips.playerLife += lifePotion.getLife();
                }else if (potion instanceof ShipPotion){
                    System.out.println("Ship Reveal Potion");
                    Game.revealShip(ocean);
                }else if (potion instanceof TrapPotion){
                    System.out.println("Trap Reveal Potion");
                    Game.revealTrap(ocean);
                }
                Game.removePotion(potion, ocean);
            }
        }
    }
    

    public static void gameOver(){
        if(BattleShips.playerDestroyedShips == 5)
        {
            System.out.println("///////");
            System.out.println("|0   0|");
            System.out.println("|  U  |");
            System.out.println("|_____|");
            System.out.println("Hooray! You won the battle!");
        }           
        else
        {
            System.out.println("///////");
            System.out.println("|0   0|");
            System.out.println("|  ^  |");
            System.out.println("|_____|");
            System.out.println("Sorry! You lost the battle ");
        }
        System.out.println();
    }
}