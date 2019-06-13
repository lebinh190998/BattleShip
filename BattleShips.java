import java.util.*;

public class BattleShips {
    public static int playerDestroyedShips = 0;
    public static int playerLife = 15;

    public static void main(String[] args){
        Grid grid = new Grid(20, 60);

        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Set game difficulty
        setDifficulty();

        //Step 2 – Create the ocean map
        Grid.createOceanMap();

        //Step 3 - Deploy ships
        Grid.deployShips();

        //Step 4 - Deploy traps
        Grid.deployTraps();

        //Step 5 - Deploy potions
        Grid.deployPotions(Grid.numRows, Grid.numCols);

        //Step 4 Battle
        do {
            Battle();
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
                Grid.setNumberOfShip(80);
                Grid.setNumberOfTrap(10);
                Grid.setNumberOfPotion(18);
            }
            else if(level == 2){
                Grid.setNumberOfShip(50);
                Grid.setNumberOfTrap(20);
                Grid.setNumberOfPotion(18);
            }
            else if(level == 3){
                Grid.setNumberOfShip(20);
                Grid.setNumberOfTrap(30);
                Grid.setNumberOfPotion(18);
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }
    
    public static void Battle(){
        playGame();
        Grid.printOceanMap();
        System.out.println();
        System.out.println("Ships found: " + BattleShips.playerDestroyedShips);
        System.out.println("Life left: " + BattleShips.playerLife);
        System.out.println();
    }
    


    public static void playGame(){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;

        do {
            Scanner input = new Scanner(System.in);
            
            System.out.print("Enter row coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter column coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols))
            {
                if (Grid.displayGrid[x][y] != " " && Grid.checkingGrid[x][y] == "c")
                {
                    BattleShips.hitShipAction(x, y);
                }
                else if (Grid.displayGrid[x][y] != " " && Grid.checkingGrid[x][y] == "t")
                {
                    BattleShips.hitTrapAction(x, y);
                }
                else if (Grid.displayGrid[x][y] != " " && Grid.potionsPosition[x][y] != 0) 
                {
                    BattleShips.hitPotionAction(x, y);
                }
                else if (Grid.displayGrid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    Grid.displayGrid[x][y] = " ";
                }
                else if (Grid.displayGrid[x][y] == " " || Grid.displayGrid[x][y] == "!") 
                {
                    System.out.println("You have chosen that position");
                }
            }
            else if ((x < 0 || x >= Grid.numRows) || (y < 0 || y >= Grid.numCols))
                System.out.println("You can't chose position outside the " + Grid.numRows + " by " + Grid.numCols + " grid");
        }while((x < 0 || x >= Grid.numRows) || (y < 0 || y >= Grid.numCols));
    }

    public static void hitShipAction(int x, int y){
        ArrayList<Ship> remainingShips = Grid.getRemainingShips();
        for(Ship ship : remainingShips){
            int shipX = ship.getX();
            ArrayList<Integer> shipY = ship.getY();
            if(x == shipX && shipY.contains(y)){
                Grid.removeShip(ship);
            }
        }
        System.out.println("Boom! You sunk the ship!");
        ++BattleShips.playerDestroyedShips;
    }

    public static void hitTrapAction(int x, int y){
        ArrayList<Trap> remainingTraps = Grid.getRemainingTraps();
        for(Trap trap : remainingTraps){
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
                Grid.removeTrap(trap);
            }
        }
    }

    public static void hitPotionAction(int x, int y){
        ArrayList<Ship> remainingShips = Grid.getRemainingShips();
        ArrayList<Trap> remainingTraps = Grid.getRemainingTraps();
        ArrayList<Potion> remainingPotions = Grid.getRemainingPotions();
        Random randomGenerator = new Random();
        int type = randomGenerator.nextInt(3);
        if(type == 0)
        {
            for(Potion potion : remainingPotions){
                if(Grid.potionsPosition[x][y] == potion.potionNo){
                   Grid.revealPotion(potion);
                }  
            }
            System.out.println("Life Saver Potion, your health increase by 1");
            ++BattleShips.playerLife;
        }
        else if(type == 1)
        {
            for(Potion potion : remainingPotions){
                if(Grid.potionsPosition[x][y] == potion.potionNo){
                   Grid.revealPotion(potion);
                }
            }
            System.out.println("Trap Reveal Potion");

            for(Trap trap : remainingTraps){
                //if(Grid.displayGrid[trap.x][trap.y] != "t"){
                    //Grid.revealTrap(trap);
                    //break;
                //}
            }
        }
        else
        {
            for(Potion potion : remainingPotions){
                if(Grid.potionsPosition[x][y] == potion.potionNo){
                   Grid.revealPotion(potion);
                }
            }
            System.out.println("Ship Reveal Potion");

            for(Ship ship : remainingShips){
                /*
                if(Grid.grid[ship.x][ship.y] != "0"){
                    Grid.revealShip(ship);
                    break;
                }
                */
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