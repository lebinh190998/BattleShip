import java.util.*;

public class BattleShips {
    public static int playerDestroyedShips = 0;
    public static int playerLife = 15;

    public static void main(String[] args){
        Grid grid = new Grid(20, 60);
        //Ship ship = new Ship();
        Trap trap = new Trap();
        Potion potion = new Potion();
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");

        //Step 1 – Set game difficulty
        setDifficulty();

        //Step 2 – Create the ocean map
        Grid.createOceanMap();

        //Step 3 - Deploy ships
        prepareShips();

        //Step 4 - Deploy traps
        prepareTraps();

        //Step 5 - Deploy potions
        preparePotions();

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
                Trap.setNumberOfTrap(10);
                Potion.setNumberOfPotion(18);
            }
            else if(level == 2){
                Grid.setNumberOfShip(50);
                Trap.setNumberOfTrap(20);
                Potion.setNumberOfPotion(18);
            }
            else if(level == 3){
                Grid.setNumberOfShip(20);
                Trap.setNumberOfTrap(30);
                Potion.setNumberOfPotion(18);
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }

    public static void prepareShips(){
        Grid.deployShips();
    }

    public static void prepareTraps(){
        Trap.deployTraps(Grid.numRows, Grid.numCols);
    }

    public static void preparePotions(){
        Potion.deployPotions(Grid.numRows, Grid.numCols);
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
        //int numberOfShips = Grid.getNumberOfShip();
        int numberOfTraps = Trap.getNumberOfTrap();
        int numberOfPotions = Potion.getNumberOfPotion();
        do {
            Random randomGenerator = new Random();
            Scanner input = new Scanner(System.in);
            ArrayList<String> remainingTraps = Trap.getRemainingTraps();
            ArrayList<Ship> remainingShips = Grid.getRemainingShips();
            
            System.out.print("Enter row coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter column coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < Grid.numRows) && (y >= 0 && y < Grid.numCols)) //valid guess
            {
                if (Grid.grid[x][y] != " " && Grid.shipPosition[x][y] != 0) //if ship is already there; loses ship
                {
                    for(Ship ship : remainingShips){
                        if(Grid.shipPosition[x][y] == ship.shipNo){
                           Grid.removeShip(ship);
                        }  
                    }
                    System.out.println("Boom! You sunk the ship!");
                    ++BattleShips.playerDestroyedShips;
                }
                else if (Grid.grid[x][y] != " " && Trap.trapsPosition[x][y] != 0)
                {
                    int type = randomGenerator.nextInt(2);
                    if(type == 0){
                        for(int i = 1; i <= numberOfTraps; i++){
                            if(Trap.trapsPosition[x][y] == i){
                               Trap.removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a Low Danger Trap :(");
                        --BattleShips.playerLife;
                    }else{
                        for(int i = 1; i <= numberOfTraps; i++){
                            if(Trap.trapsPosition[x][y] == i){
                               Trap.removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.playerLife -= 2;
                    }
                }
                else if (Grid.grid[x][y] != " " && Potion.potionsPosition[x][y] != 0) 
                {
                    int type = randomGenerator.nextInt(3);
                    if(type == 0)
                    {
                        for(int i = 1; i <= numberOfPotions; i++){
                            if(Potion.potionsPosition[x][y] == i){
                               Potion.revealPotion(i);
                            }  
                        }
                        System.out.println("Life Saver Potion, your health increase by 1");
                        ++BattleShips.playerLife;
                    }
                    else if(type == 1)
                    {
                        for(int i = 1; i <= numberOfPotions; i++){
                            if(Potion.potionsPosition[x][y] == i){
                               Potion.revealPotion(i);
                            }
                        }
                        System.out.println("Trap Reveal Potion");
                        if(remainingTraps.size() > 0)
                        {
                            Trap.revealTrap(Integer.parseInt(remainingTraps.get(0)));
                        }
                        else
                        {
                            System.out.println("No more trap to reveal");
                        }
                    }
                    else
                    {
                        for(int i = 1; i <= numberOfPotions; i++){
                            if(Potion.potionsPosition[x][y] == i){
                               Potion.revealPotion(i);
                            }  
                        }
                        System.out.println("Ship Reveal Potion");

                           // Grid.revealShip(Integer.parseInt(remainingShips.get(0)));
                    }
                }
                else if (Grid.grid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    Grid.grid[x][y] = " ";
                }
                else if (Grid.grid[x][y] == " " || Grid.grid[x][y] == "!") 
                {
                    System.out.println("You have chosen that position");
                }
            }
            else if ((x < 0 || x >= Grid.numRows) || (y < 0 || y >= Grid.numCols))  //invalid guess
                System.out.println("You can't chose position outside the " + Grid.numRows + " by " + Grid.numCols + " grid");
        }while((x < 0 || x >= Grid.numRows) || (y < 0 || y >= Grid.numCols));  //keep re-prompting till valid guess
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