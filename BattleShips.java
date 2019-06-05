import java.util.*;

public class BattleShips {
    public static int numRows = 20;
    public static int numCols = 60;
    public static int playerDestroyedShips = 0;
    public static int playerLife = 3;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] shipPosition = new int[numRows][numCols];
    public static int[][] trapsPosition = new int[numRows][numCols];
    public static int[][] potionsPosition = new int[numRows][numCols];

    public static void main(String[] args){
        Ship ship = new Ship();
        Trap trap = new Trap();
        Potion potion = new Potion();
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");


        //Step 1 – Set game difficulty
        setDifficulty();

        //Step 2 – Create the ocean map
        createOceanMap();

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
                Ship.setNumberOfShip(80);
                Trap.setNumberOfTrap(10);
                Potion.setNumberOfPotion(18);
            }
            else if(level == 2){
                Ship.setNumberOfShip(50);
                Trap.setNumberOfTrap(20);
                Potion.setNumberOfPotion(18);
            }
            else if(level == 3){
                Ship.setNumberOfShip(20);
                Trap.setNumberOfTrap(30);
                Potion.setNumberOfPotion(18);
            }
            else{
                System.out.println("Please input a number from 1 to 3");
            }
        }while(level != 1 && level != 2 && level != 3);
    }

    public static void createOceanMap(){
        //Header section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "#";
                if (j == 0){
                    if((i+1) < 10){
                        System.out.print(" " + (i+1) + "|" + grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + grid[i][j]);
                    }
                }
                else if (j == grid[i].length - 1){
                    System.out.print(grid[i][j] + "|");
                }
                else{
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    public static void prepareShips(){
        int[][] shipPosition = Ship.getShipPosition();
        int[][] trapsPosition = Trap.getTrapPosition();
        int[][] potionsPosition = Potion.getPotionPosition();
        Ship.deployShips(numRows, numCols, shipPosition, trapsPosition, potionsPosition);
        printOceanMap();
    }

    public static void prepareTraps(){
        Trap.deployTraps(numRows, numCols);
        printOceanMap();
    }

    public static void preparePotions(){
        Potion.deployPotions(numRows, numCols);
        printOceanMap();
    }
    
    public static void Battle(){
        playerTurn();
        printOceanMap();
        System.out.println();
        System.out.println("Destroyed ships: " + BattleShips.playerDestroyedShips);
        System.out.println("Your life: " + BattleShips.playerLife);
        System.out.println();
    }
    


    public static void playerTurn(){
        System.out.println("\nYOUR TURN");
        int x = -1, y = -1;
        int numberOfShips = Ship.getNumberOfShip();
        int numberOfTraps = Trap.getNumberOfTrap();
        int numberOfPotions = Potion.getNumberOfPotion();
        do {
            Random randomGenerator = new Random();
            int[][] shipPosition = Ship.getShipPosition();
            int[][] trapsPosition = Trap.getTrapPosition();
            int[][] potionsPosition = Potion.getPotionPosition();
            ArrayList<String> remainingTraps = Trap.getRemainingTraps();
            ArrayList<String> remainingShips = Ship.getRemainingShips();
            Scanner input = new Scanner(System.in);
            
            System.out.print("Enter X coordinate: ");
            x = input.nextInt() -1;
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt() -1;
            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] != " " && shipPosition[x][y] != 0) //if ship is already there; loses ship
                {
                    for(int i = 1; i <= numberOfShips; i++){
                        if(shipPosition[x][y] == i){
                           Ship.removeShip(i);
                        }  
                    }
                    System.out.println("Boom! You sunk the ship!");
                    ++BattleShips.playerDestroyedShips;
                }
                else if (grid[x][y] != " " && trapsPosition[x][y] != 0)
                {
                    int type = randomGenerator.nextInt(2);
                    if(type == 0){
                        for(int i = 1; i <= numberOfTraps; i++){
                            if(trapsPosition[x][y] == i){
                               Trap.removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a Low Danger Trap :(");
                        --BattleShips.playerLife;
                    }else{
                        for(int i = 1; i <= numberOfTraps; i++){
                            if(trapsPosition[x][y] == i){
                               Trap.removeTrap(i);
                            }  
                        }
                        System.out.println("Oh no, you hit in a High Danger Trap :(");
                        BattleShips.playerLife -= 2;
                    }
                }
                else if (grid[x][y] != " " && potionsPosition[x][y] != 0) 
                {
                    int type = randomGenerator.nextInt(3);
                    //int type = 1;
                    if(type == 0)
                    {
                        for(int i = 1; i <= numberOfPotions; i++){
                            if(potionsPosition[x][y] == i){
                               Potion.revealPotion(i);
                            }  
                        }
                        System.out.println("Life Saver Potion, your health increase by 1");
                        ++BattleShips.playerLife;
                    }
                    else if(type == 1)
                    {
                        for(int i = 1; i <= numberOfPotions; i++){
                            if(potionsPosition[x][y] == i){
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
                            if(potionsPosition[x][y] == i){
                               Potion.revealPotion(i);
                            }  
                        }
                        System.out.println("Ship Reveal Potion");
                        if(remainingShips.size() > 0)
                        {
                            Ship.revealShip(Integer.parseInt(remainingShips.get(0)));
                        }
                        else
                        {
                            System.out.println("No more ship to reveal");
                        }
                    }
                }
                else if (grid[x][y] == "#") 
                {
                    System.out.println("Sorry, you missed");
                    grid[x][y] = " ";
                }
                else if (grid[x][y] == " " || grid[x][y] == "!") 
                {
                    System.out.println("You have chosen that position");
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }
    

    public static void gameOver(){
        if(BattleShips.playerDestroyedShips == 5)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry! You lost the battle :(");
        System.out.println();
    }
    
    
    public static void printOceanMap(){
        System.out.println();

        //First section of Ocean Map
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            int x = (int)(i/10);
            if(i-(x*10) == 0){
                System.out.print(x);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.print("   ");
        for(int i = 1; i <= numCols; i++){
            if(i > 9){
                int x = (int)(i/10);
                System.out.print(i-(x*10));
            }else{
                System.out.print(i);
            }
        }
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0){
                    if((i+1) < 10){

                        System.out.print(" " + (i+1) + "|" + grid[i][j]);
                    }else{
                        System.out.print((i+1) + "|" + grid[i][j]);
                    }
                }
                else if (j == grid[i].length - 1){
                    System.out.print(grid[i][j] + "|");
                }
                else{
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }

        //Last section of Ocean Map
        for(int i = 1; i <= numCols+5; i++){
            System.out.print("-");
        }
        System.out.println();
    }
}